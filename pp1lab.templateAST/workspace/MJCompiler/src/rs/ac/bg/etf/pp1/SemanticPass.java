package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
/*
import rs.ac.bg.etf.pp1.ast.AddExpr;
import rs.ac.bg.etf.pp1.ast.Assignment;
import rs.ac.bg.etf.pp1.ast.Const;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignment;
import rs.ac.bg.etf.pp1.ast.FuncCall;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.ProcCall;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ReturnExpr;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.TermExpr;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
*/
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticPass extends VisitorAdaptor {

	boolean errorDetected = false;
	int printCallCount = 0;
	Obj currentMethod = null;
	boolean returnFound = false;
	int nVars;
	
	// My humble contribution to this charade 
	
	//mikrojava_2020_2021_jan.pdf -> A.5 Implementaciona ogranicenja (strana 8 od 15)
	public static final int MAX_NUMBER_OF_LOCAL_VARS = 256;
	public static final int MAX_NUMBER_OF_GLOBAL_VARS = 65536;
	//
	
	boolean mainFunctionFound = false;
	boolean voidMethod = false;
	Struct currentType = Tab.noType;
	public static final Struct Boolean = new Struct(Struct.Bool);
		
	public void report_error_v2(String message, SyntaxNode info) {
		errorDetected = true;
		int line = (info == null) ? 0: info.getLine();
		StringBuilder msg = new StringBuilder();
		if (line != 0)
			msg.append ("Semanticka greska na liniji ").append(line).append(" : ");
		
		msg.append(message);
		
		log.error(msg.toString());
	}

	// End of my humble contribution
	
	Logger log = Logger.getLogger(getClass());
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
	public void visit(Program program) {		
		nVars = Tab.currentScope.getnVars();
		if (!mainFunctionFound) {
			report_error_v2("Nije pronadjena 'main' funkcija", program);
		}
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}

	public void visit(ProgName progName) {
		Tab.insert(Obj.Type, "bool", Boolean);
		progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
		Tab.openScope();     	
	}
	
	public void visit(GlobalVarDeclList globalVarDeclList) {
		if (Tab.currentScope().getnVars() >= MAX_NUMBER_OF_GLOBAL_VARS) {
			report_error_v2("Deklarisan je broj globalnih promenljivih veci od dozvoljenog (" + MAX_NUMBER_OF_GLOBAL_VARS + ")", globalVarDeclList);
		}
	}

	public void visit(VarDecl varDecl) {
		currentType = Tab.noType;
	}
	
	public void visit(ConstDecl constDecl) {
		currentType = Tab.noType;
	}
	
	public void visit(VarNode varNode) {
		if (Tab.currentScope().findSymbol(varNode.getVarName()) != null) {
			report_error("Deklarisan je vec simbol sa imenom "+ varNode.getVarName(), varNode);
			return;
		}
		if (currentType.equals(Tab.noType)) {
			report_error_v2("Nije definisan tip za promenljivu " + varNode.getVarName(), varNode);
		}else {
			report_info("Deklarisana promenljiva "+ varNode.getVarName(), varNode);
		}
		
		if (varNode.getArrayVarNode() instanceof ArrayDecl) {
			// array declaration
			Tab.insert(Obj.Var, varNode.getVarName(), new Struct(Struct.Array, currentType));
		}else {
			// variable declaration
			Tab.insert(Obj.Var, varNode.getVarName(), currentType);
		}
	}
		
	public void visit(ConstNode constNode) {
		int val = -1;
		Struct constType = Tab.noType;
		if (Tab.currentScope().findSymbol(constNode.getConstName()) != null) {
			report_error("Deklarisan je vec simbol sa imenom "+ constNode.getConstName(), constNode);
			return;
		}
		if (currentType.equals(Tab.noType)) {
			report_error_v2("Nije definisan tip za konstantu " + constNode.getConstName(), constNode);
		}else {
			ConstantVars constValue = constNode.getConstantVars();
			if (constValue instanceof IntVar) {
				// int const
				int intVal = ((IntVar)constValue).getValue();
				val = intVal;
				constType = Tab.intType;
				report_info("Deklarisana konstanta " + constNode.getConstName() + " sa vrednoscu " + intVal, constNode);
			}else if (constValue instanceof CharVar) {
				// char const
				char charVal = ((CharVar)constValue).getValue();
				val = Character.getNumericValue(charVal);
				constType = Tab.charType;
				report_info("Deklarisana konstanta " + constNode.getConstName() + " sa vrednoscu " + charVal, constNode);
			}else if (constValue instanceof BoolVar) {
				// boolean const
				boolean boolVal = ((BoolVar)constValue).getValue();
				val = boolVal ? 1 : 0;
				constType = Boolean;
				report_info("Deklarisana konstanta " + constNode.getConstName() + " sa vrednoscu " + boolVal, constNode);
			}
		}
		Obj constObj = Tab.insert(Obj.Con, constNode.getConstName(), currentType);
		constObj.setAdr(val);

		
	}

	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
			type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				type.struct = typeNode.getType();
			} 
			else {
				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
				type.struct = Tab.noType;
			}
		}  
		
		currentType = type.struct;
	}

	public void visit(MethodDecl methodDecl) {
		if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funcija " + currentMethod.getName() + " nema return iskaz!", null);
		}
		
		if (Tab.currentScope().getnVars() >= MAX_NUMBER_OF_LOCAL_VARS) {
			report_error_v2("Deklarisan je broj lokalnih promenljivih veci od dozvoljenog (" + MAX_NUMBER_OF_LOCAL_VARS + ")", methodDecl);
		}
		
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		
		if (methodDecl.getMethodTypeName().obj.getName().equalsIgnoreCase("main") && currentMethod.getLevel() > 0) {
			report_error_v2("'main' funkcija ne sme imati formalne parametre", methodDecl);	
		}
		
		returnFound = false;
		voidMethod = false;
		currentMethod = null;
	}
	
	public void visit(NonVoidMethodTypeName nonVoidMethodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, nonVoidMethodTypeName.getMethName(), nonVoidMethodTypeName.getType().struct);
		nonVoidMethodTypeName.obj = currentMethod;
		if (nonVoidMethodTypeName.getMethName().equalsIgnoreCase("main")) {
			report_error_v2("Tip povratne vrednosti 'main' funkcije mora biti tipa 'void'", nonVoidMethodTypeName);
		}
		Tab.openScope();
		report_info("Obradjuje se funkcija " + nonVoidMethodTypeName.getMethName(), nonVoidMethodTypeName);
	}
	
	public void visit(VoidMethodTypeName voidMethodTypeName) {
		currentMethod = Tab.insert(Obj.Meth, voidMethodTypeName.getMethName(), Tab.noType);
		voidMethod = true;
		if (voidMethodTypeName.getMethName().equalsIgnoreCase("main")) {
			mainFunctionFound = true;
		}
		voidMethodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija " + voidMethodTypeName.getMethName(), voidMethodTypeName);
	}
	
	public void visit(FormalParamDecl formalParamDecl) {
		Struct type = formalParamDecl.getType().struct;
		String varName = formalParamDecl.getName(); 
		
		if (Tab.currentScope().findSymbol(varName) != null) {
			report_error("Deklarisan je vec formalni parametar sa imenom "+ varName, formalParamDecl);
			return;
		}		
		Obj var;
		
		if (formalParamDecl.getArrayVarNode() instanceof ArrayDecl) {
			// array declaration
			var = Tab.insert(Obj.Var, varName, new Struct(Struct.Array, currentType));
		}else {
			// variable declaration
			var = Tab.insert(Obj.Var, varName, currentType);
		}
		
		int level = currentMethod.getLevel() + 1;
		currentMethod.setLevel(level);
		var.setFpPos(level);

	}
	
	public void visit(PrintStmt printStmt){
		printCallCount++;    	
	}

	public void visit(YesReturnExpr returnExpr){
		returnFound = true;
		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}			 
	}   

	public void visit(Term term) {
		term.struct = term.getFactor().struct;    	
	}
	
	public void visit(Variable variable) {
		variable.struct = variable.getDesignator().obj.getType();
	}
	
	public void visit(ConstFactors constFactors) {
		Struct constType = Tab.noType;
		if (constFactors.getConstantVars() instanceof IntVar) {
			// int const
			constType = Tab.intType;
		}else if (constFactors.getConstantVars() instanceof CharVar) {
			// char const
			constType = Tab.charType;
		}else if (constFactors.getConstantVars() instanceof BoolVar) {
			// boolean const
			constType = Boolean;
		}
		constFactors.struct = constType;
	}
	
	public void visit(ExpressionVar expressionVar) {
		expressionVar.struct = expressionVar.getExpr().struct;
	}
	
	public void visit(NewObjectVar newObjectVar) {
		Struct size = newObjectVar.getExpr().struct;
		if(!size.equals(Tab.intType)) {
			report_error_v2("Izraz za velicinu niza mora biti tipa 'int'", newObjectVar);
			newObjectVar.struct = Tab.noType;
			return;
		}
		newObjectVar.struct = new Struct(Struct.Array, currentType);
		
		//da li treba ovde resetovati currentType?
		currentType = Tab.noType;
	}

	public void visit(FuncCall funcCall){
		Obj func = funcCall.getDesignator().obj;
		if (Obj.Meth == func.getKind()) { 
			if (func.getType() == Tab.noType) {
				report_error_v2("Ne moze se koristiti u izrazima jer nema povratnu vrednost", funcCall);
				funcCall.struct = Tab.noType;
				return;
			}
			report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
			funcCall.struct = func.getType();
			
			ArrayList<Expr> listActualParameters = new ArrayList<Expr>();
			
			if (funcCall.getFactorActPars() instanceof YesFactorActPars) {
				// ima parametera i ubacujemo ih u listu onim redom koji se pojavljuju
				ExprWithActPars actPars = (ExprWithActPars)((YesFactorActPars) funcCall.getFactorActPars()).getActPars();
				listActualParameters.add(actPars.getExpr());
				
				ActParsList actParsList = actPars.getActParsList();
				while(actParsList instanceof YesActParsList) {
					YesActParsList yesActParsList = (YesActParsList) actParsList;
					listActualParameters.add(yesActParsList.getExpr());
					actParsList = yesActParsList.getActParsList();
				}
			}
			
			int formParCnt = func.getLevel();
			int actParCnt = listActualParameters.size();
			
			if (formParCnt != actParCnt) {
				report_error_v2("Neodgovarajuc broj aktuelnih parametera (" + listActualParameters.size() + ") i formalnih parametara (" + func.getLevel() + ") za funkciju " + func.getName(), funcCall);
				funcCall.struct = Tab.noType;
				return;
			}
			
			
			func.getLocalSymbols().forEach(t -> {
				// fpPos = 0 -> lokalna promenljiva
				// fpPos > 0 -> formalni parametar
				int index = t.getFpPos();
				if (index > 0) {
					Expr act = listActualParameters.get(index-1);	// index - 1 jer se prvom formalnom parametru postavlja fp na 1, a nizovi krecu od 0
					if(!t.getType().assignableTo(act.struct)) {
						report_error_v2("Ne poklapaju se tipovi parametara na poziciji " + index, funcCall);
					}
				}
			});
			
		} 
		else {
			report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
			funcCall.struct = Tab.noType;
		}
	}
	
	public void visit(ProcCall procCall){
		Obj proc = procCall.getDesignator().obj;
		if (Obj.Meth == proc.getKind()) { 
			report_info("Pronadjen poziv procedure " + proc.getName() + " na liniji " + procCall.getLine(), null);
			
			ArrayList<Expr> listActualParameters = new ArrayList<Expr>();
			
			if (procCall.getDesignatorActPars() instanceof YesDesignatorActPars) {
				// ima parametera i ubacujemo ih u listu onim redom koji se pojavljuju
				ExprWithActPars actPars = (ExprWithActPars)((YesDesignatorActPars) procCall.getDesignatorActPars()).getActPars();
				listActualParameters.add(actPars.getExpr());
				
				ActParsList actParsList = actPars.getActParsList();
				while(actParsList instanceof YesActParsList) {
					YesActParsList yesActParsList = (YesActParsList) actParsList;
					listActualParameters.add(yesActParsList.getExpr());
					actParsList = yesActParsList.getActParsList();
				}
			}
			
			int formParCnt = proc.getLevel();
			int actParCnt = listActualParameters.size();
			
			if (formParCnt != actParCnt) {
				report_error_v2("Neodgovarajuc broj aktuelnih parametera (" + listActualParameters.size() + ") i formalnih parametara (" + proc.getLevel() + ") za funkciju " + proc.getName(), procCall);
				return;
			}
			
			
			proc.getLocalSymbols().forEach(t -> {
				// fpPos = 0 -> lokalna promenljiva
				// fpPos > 0 -> formalni parametar
				int index = t.getFpPos();
				if (index > 0) {
					Expr act = listActualParameters.get(index-1);	// index - 1 jer se prvom formalnom parametru postavlja fp na 1, a nizovi krecu od 0
					if(!t.getType().assignableTo(act.struct)) {
						report_error_v2("Ne poklapaju se tipovi parametara na poziciji " + index, procCall);
					}
				}
			});
		} 
		else {
			report_error("Greska na liniji " + procCall.getLine()+" : ime " + proc.getName() + " nije funkcija!", null);
			//RESULT = Tab.noType;
		}     	
	} 
		
	public void visit(DesignatorNonArray designatorNonArray) {
		Obj obj = Tab.find(designatorNonArray.getName());
		if (obj == Tab.noObj) { 
			report_error_v2("Ime "+designatorNonArray.getName()+" nije deklarisano! ", designatorNonArray);
		}
		
		designatorNonArray.obj = obj;
	}
	
	public void visit(DesignatorArray designatorArray) {
		Obj obj = Tab.find(designatorArray.getDesignatorArrayName().getName());
		if (obj.getType() != Tab.noType) {
			if (designatorArray.getExpr().struct != Tab.intType) {
				report_error("Indeks mora biti tipa 'int'", designatorArray);
			}
		}
		designatorArray.obj = new Obj(Obj.Elem, obj.getName() + "$elem", obj.getType().getElemType());
	}
	
	public void visit(DesignatorArrayName designatorArrayName) {
		Obj obj = Tab.find(designatorArrayName.getName());
		if (obj.getType().getKind() != Struct.Array) {
			report_error_v2("Promenljiva " + obj.getName() + " nije nizovnog tipa", designatorArrayName);
			designatorArrayName.obj = Tab.noObj;
			return;
		}
		designatorArrayName.obj = obj;
	}
	
	public void visit(NonTernaryExpr nonTernaryExpr) {
		nonTernaryExpr.struct = nonTernaryExpr.getNonTernaryExpression().struct;
	}

	public void visit(NegativeExpr negativeExpr) {
		if(negativeExpr.getExpression().struct != Tab.intType) {
			report_error_v2("NegativeExpr mora biti tipa Int", negativeExpr);
			negativeExpr.struct = Tab.noType;
			return;
		}
		negativeExpr.struct = negativeExpr.getExpression().struct;
		
	}

	public void visit(PositiveExpr positiveExpr) {
		positiveExpr.struct = positiveExpr.getExpression().struct;
	}

	public void visit(AddExpr addExpr) {
		Struct te = addExpr.getExpression().struct;
		Struct t = addExpr.getTerm().struct;
		if (te.equals(t) && te == Tab.intType) {
			addExpr.struct = te;
			return;
		}
		report_error_v2("Nekompatibilni tipovi u izrazu za sabiranje.", addExpr);
		addExpr.struct = Tab.noType;
	}

	public void visit(TermExpr termExpr) {
		termExpr.struct = termExpr.getTerm().struct;
	}
	
	public void visit(TernaryExpr ternaryExpr) {
		ternaryExpr.struct = ternaryExpr.getTernaryExpression().struct;
	}

	public void visit(TernaryExpression ternaryExpression) {
		Struct t1 = ternaryExpression.getExpr().struct;
		Struct t2 = ternaryExpression.getExpr1().struct;
		
		if (t1 != t2) {
			report_error_v2("Tipovi u ternarnom operatoru se moraju poklapati", ternaryExpression);
			ternaryExpression.struct = Tab.noType;
			return;	
		}
		if (t1.equals(Tab.noType)) {
			report_error_v2("Clanovi ternarnog operatora moraju imati tip", ternaryExpression);
			ternaryExpression.struct = Tab.noType;
			return;
		}
		ternaryExpression.struct = t1;	
	}

	public void visit(MultiCondFact multiCondFact) {
		Struct t1 = multiCondFact.getNonTernaryExpression().struct;
		Struct t2 = multiCondFact.getNonTernaryExpression1().struct;

		if (!t1.equals(t2) || t1.equals(Tab.noType)) {
			report_error_v2("Tipovi u operacionom clanu se moraju poklapati", multiCondFact);
			multiCondFact.struct = Tab.noType;
			return;
		}
		multiCondFact.struct = SemanticPass.Boolean;
	}

	public void visit(SingleCondFact singleCondFact) {
		if (singleCondFact.getNonTernaryExpression().struct != SemanticPass.Boolean) {
			report_error_v2("Uslov mora biti logickog tipa", singleCondFact);
			singleCondFact.struct = Tab.noType;
			return;
		}
		singleCondFact.struct = SemanticPass.Boolean;
	}
	
	public void visit(DesignatorAssignment designatorAssignment) {
		Designator var = designatorAssignment.getDesignator();
		Expr value = designatorAssignment.getExpr();
		int kind = var.obj.getKind();
		if (!(kind == Obj.Var || kind == Obj.Elem || kind == Obj.Fld)) {
			report_error_v2("Vrednost se moze dodeliti samo promenljivama, clanovima niza ili polju klase", designatorAssignment);
			return;
		}
		
		if (!value.struct.assignableTo(var.obj.getType()))
			report_error("Greska na liniji " + designatorAssignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
	}
	
	public void visit(DesignatorIncrement designatorIncrement) {
		Designator var = designatorIncrement.getDesignator();
		if (!(var.obj.getType() == Tab.intType)) {
			report_error_v2("Moze se inkrementirati samo promenljiva tipa 'int'", designatorIncrement);
			return;
		}
	}
	
	public void visit(DesignatorDecrement designatorDecrement) {
		Designator var = designatorDecrement.getDesignator();
		if (!(var.obj.getType() == Tab.intType)) {
			report_error_v2("Moze se dekrementirati samo promenljiva tipa 'int'", designatorDecrement);
			return;
		}
	}
	

}

