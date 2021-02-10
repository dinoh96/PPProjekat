package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
//import rs.ac.bg.etf.pp1.ast.AddExpr;
//import rs.ac.bg.etf.pp1.ast.Assignment;
//import rs.ac.bg.etf.pp1.ast.Const;
//import rs.ac.bg.etf.pp1.ast.Designator;
//import rs.ac.bg.etf.pp1.ast.FormalParamDecl;
//import rs.ac.bg.etf.pp1.ast.FuncCall;
//import rs.ac.bg.etf.pp1.ast.MethodDecl;
//import rs.ac.bg.etf.pp1.ast.MethodTypeName;
//import rs.ac.bg.etf.pp1.ast.PrintStmt;
//import rs.ac.bg.etf.pp1.ast.ReturnExpr;
//import rs.ac.bg.etf.pp1.ast.ReturnNoExpr;
//import rs.ac.bg.etf.pp1.ast.SyntaxNode;
//import rs.ac.bg.etf.pp1.ast.VarDecl;
//import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private class LoopHelper{
		public int topAddress;
		public int bottomAddress;
		public List<Integer> breaks = new ArrayList<Integer>();
		public List<Integer> continues = new ArrayList<Integer>();
		
		public LoopHelper(int topAddress) {
			this.topAddress = topAddress;
		}
		
		public void AddBreak() {
			breaks.add(Code.pc-2);
		}
		
		public void AddContinue() {
			continues.add(Code.pc-2);
		}
		
		public void FixBreaks() {
			breaks.forEach((adr) -> Code.fixup(adr));
			breaks.clear();
		}
		public void FixContinues() {
			continues.forEach((adr) -> Code.fixup(adr));
			continues.clear();
		}
	}
	
	private class ConditionHelper{
		public boolean isDoWhile;
		public int thenAddress;	// if isDoWhile == true, this is used for start of the loop
		public int elseAddress;	// if isDoWhile == true, this is used for end of the loop
		public int endAddress;
		public List<Integer> condFactAddress = new ArrayList<Integer>();
		public List<Integer> condTermAddress = new ArrayList<Integer>();
		
		public ConditionHelper(boolean isDoWhile) {
			this.isDoWhile = isDoWhile;
		}
		
		public void SetFalseJumpElse() {
			elseAddress = Code.pc - 2;
		}
		
		public void SetFalseJumpEnd() {
			endAddress = Code.pc - 2;
		}
		
		public void AddCondFact() {
			condFactAddress.add(Code.pc-2);
		}
		
		public void AddCondTerm() {
			condTermAddress.add(Code.pc-2);
		}
		
		public void FixCondFact() {
			condFactAddress.forEach((adr) -> Code.fixup(adr));
			condFactAddress.clear();
		}
		
		public void FixCondTerm() {
			condTermAddress.forEach((adr) -> Code.fixup(adr));
			condTermAddress.clear();
		}
		
		public void FixElse() {
			Code.fixup(elseAddress);
		}
		
		public void FixEnd() {
			Code.fixup(endAddress);
		}
	}
	
	private Stack<LoopHelper> loops = new Stack<LoopHelper>();
	private Stack<ConditionHelper> conditions = new Stack<ConditionHelper>();
	
	private boolean isTernary = false;
	
	public static boolean checkParent(SyntaxNode node, Object parentClass) {
		SyntaxNode parent = node.getParent();
		while(parent != null) {
			if (parent.getClass() == parentClass) 
				return true;
			parent = parent.getParent();
		}
		return false;
	}
	
	private int varCount;
	
	private int paramCnt;
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	@Override
	public void visit(ProgName progName) {
		// char chr(int i)
		Tab.chrObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Obj i = Tab.chrObj.getLocalSymbols().iterator().next();
		Code.load(i);
		Code.put(Code.exit);
		Code.put(Code.return_);
		// int ord(char ch)
		Tab.ordObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Obj ch = Tab.ordObj.getLocalSymbols().iterator().next();
		Code.load(ch);
		Code.put(Code.exit);
		Code.put(Code.return_);
		// int len(void arr[])
		Tab.lenObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Obj arr = Tab.lenObj.getLocalSymbols().iterator().next();
		Code.load(arr);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(VoidMethodTypeName voidMethodTypeName) {
		if ("main".equalsIgnoreCase(voidMethodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		voidMethodTypeName.obj.setAdr(Code.pc);
		
		// Generate the entry.
		int formParsCnt = voidMethodTypeName.obj.getLevel();
		int localArgsCnt = voidMethodTypeName.obj.getLocalSymbols().size();// - formParsCnt; //izlgeda da ovde ide i broj lokalnih parametara i formalnih
		
		Code.put(Code.enter);
		Code.put(formParsCnt);
		Code.put(localArgsCnt);
	}
	
	@Override
	public void visit(NonVoidMethodTypeName nonVoidMethodTypeName) {
		nonVoidMethodTypeName.obj.setAdr(Code.pc);
		
		// Generate the entry.
		int formParsCnt = nonVoidMethodTypeName.obj.getLevel();
		int localArgsCnt = nonVoidMethodTypeName.obj.getLocalSymbols().size() - formParsCnt;
		
		Code.put(Code.enter);
		Code.put(formParsCnt);
		Code.put(localArgsCnt);
	}
	
	@Override
	public void visit(MethodDecl MethodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(VarDecl VarDecl) {
		varCount++;
	}

	@Override
	public void visit(FormalParamDecl FormalParam) {
		paramCnt++;
	}	
	
	@Override
	public void visit(ReturnStmt returnStmt) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	@Override
	public void visit(ReadStmt readStmt) {
		Obj obj = readStmt.getDesignator().obj;
		if (obj.getType() == Tab.intType) {
			Code.put(Code.read);
		}else {
			// ucitava se char
			Code.put(Code.bread);
		}
		// read na steku ostavlja ucitanu vrednost
		Code.store(obj);
	}
	
	@Override
	public void visit(DesignatorAssignment designatorAssignment) {
		Code.store(designatorAssignment.getDesignator().obj);
	}
	
//	public void visit()
	
	@Override
	public void visit(ConstFactors constFactors) {
		int intValue = 0;
		
		if (constFactors.getConstantVars() instanceof IntVar) {
			// int const
			intValue = ((IntVar)constFactors.getConstantVars()).getValue();
		}else if (constFactors.getConstantVars() instanceof CharVar) {
			// char const
			intValue = (int)(((CharVar)constFactors.getConstantVars()).getValue().charValue());
		}else if (constFactors.getConstantVars() instanceof BoolVar) {
			// boolean const
			intValue = ((BoolVar)constFactors.getConstantVars()).getValue() ? 1 : 0;
		}	
		
		Code.load(new Obj(Obj.Con, "$", constFactors.struct, intValue, 0));

	}
	
	@Override
	public void visit(DesignatorIncrement designatorIncrement){
		Obj obj = designatorIncrement.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			
		}
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.add);

		Code.store(obj);
	}
	
	@Override
	public void visit(NewObjectVar newObjectVar) {
		Code.put(Code.newarray);
		if (newObjectVar.getType().struct == Tab.intType) {
			Code.put(1);
		} else {
			Code.put(0);
		}
	}
	
	@Override
	public void visit(DesignatorDecrement designatorDecrement){
		Obj obj = designatorDecrement.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			
		}
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.sub);

		Code.store(obj);
	}
	
	@Override
	public void visit(DesignatorNonArray designatorNonArray) {
		SyntaxNode parent = designatorNonArray.getParent();
		if (!(parent instanceof DesignatorStatement) && !(parent instanceof ReadStmt) && !(parent instanceof FuncCall)) {
			//Promenljiva koja ucestvuje u izrazima
			Code.load(designatorNonArray.obj);
		}
	}
	
	@Override
	public void visit(DesignatorArray designatorArray) {
		SyntaxNode parent = designatorArray.getParent();
		if (!(parent instanceof DesignatorStatement) && !(parent instanceof ReadStmt) && !(parent instanceof FuncCall)) {
			//Promenljiva koja ucestvuje u izrazima
			Code.load(designatorArray.obj);
		}
	}
	
	@Override
	public void visit(DesignatorArrayName designatorArrayName) {
		Code.load(designatorArrayName.obj);
	}
	
	@Override
	public void visit(FuncCall FuncCall) {
		Obj functionObj = FuncCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; 
		Code.put(Code.call);
		Code.put2(offset);
	}
	
	@Override
	public void visit(ProcCall procCall) {
		Obj functionObj = procCall.getDesignator().obj;
		int offset = functionObj.getAdr() - Code.pc; 
		Code.put(Code.call);
		Code.put2(offset);
		if(procCall.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop);
		}
	}
	
	@Override
	public void visit(PrintStmt PrintStmt) {
		if (PrintStmt.getExpr().struct.equals(Tab.intType) || PrintStmt.getExpr().struct.equals(SemanticAnalyzer.Boolean) ) {
			Code.put(Code.const_5);
			Code.put(Code.print);
		}else {
			//char
			Code.put(Code.const_1);
			Code.put(Code.bprint);
		}
		
	}
	
	@Override
	public void visit(AddExpr addExpr) {
		if(addExpr.getAddop().getClass().equals(Addition.class))
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}
	
	public void visit(NegativeExpr negativeExpr) {
		Code.put(Code.neg);
	}
	
	public void visit(YesMulopFactor yesMulopFactor) {
		if (yesMulopFactor.getMulop().getClass().equals(Multiplication.class))
			Code.put(Code.mul);
		else 
			if (yesMulopFactor.getMulop().getClass().equals(Division.class))
				Code.put(Code.div);
			else 
				Code.put(Code.rem);
	}
		
	public void visit(IfKeyWord ifKeyWord) {
		conditions.push(new ConditionHelper(false));
	}

	public void visit(MultiCondFact multiCondFact) {
		if (!isTernary) {
			if (checkParent(multiCondFact, TernaryExpression.class)) {
				isTernary = true;
				conditions.push(new ConditionHelper(false));
			}
		}
		
		int op = 0;
		
		Relop relop = multiCondFact.getRelop();
		
		op = 1;
		
		if (relop.getClass().equals(Equals.class)) {
			op = Code.eq;
		}
		if (relop.getClass().equals(NotEquals.class)) {
			op = Code.ne;
		}
		if (relop.getClass().equals(LesserThan.class)) {
			op = Code.lt;
		}
		if (relop.getClass().equals(LesserEqual.class)) {
			op = Code.le;
		}
		if (relop.getClass().equals(GreaterThan.class)) {
			op = Code.gt;
		}
		if (relop.getClass().equals(GreaterEqual.class)) {
			op = Code.ge;
		}
		
		Code.putFalseJump(op, 0);
		conditions.peek().AddCondFact();
	}
	
	public void visit(SingleCondFact singleCondFact) {
		if (!isTernary) {
			if (checkParent(singleCondFact, TernaryExpression.class)) {
				isTernary = true;
				conditions.push(new ConditionHelper(false));
			}
		}
		Code.loadConst(1);
		Code.putFalseJump(Code.eq, 0);
		conditions.peek().AddCondFact();
	}
	
	public void visit(CondTerm condTerm) {		
		if (conditions.peek().isDoWhile) {
			Code.putJump(loops.peek().topAddress);
		}else {
			Code.putJump(0);
			conditions.peek().AddCondTerm();
		}	
		conditions.peek().FixCondFact();	
	}
	
	public void visit(Condition condition) {
		// put jmp to else/end
		Code.putJump(0);
		conditions.peek().SetFalseJumpElse();
		conditions.peek().SetFalseJumpEnd();
		
		conditions.peek().FixCondTerm();
	}
	
	public void visit(UnmatchedIf unmatchedIf) {
		conditions.pop().FixEnd();
	}
	
	public void visit(ElseKeyWord elseKeyWord) {
		Code.putJump(0);
		conditions.peek().SetFalseJumpEnd();
		conditions.peek().FixElse();
	}
	
	public void visit(MatchedIf matchedIf) {
		conditions.pop().FixEnd();
	}
	public void visit(UnmatchedIfElse unmatchedIfElse) {
		conditions.pop().FixEnd();
	}
	
	public void visit(DoKeyWord doKeyWord) {
		loops.push(new LoopHelper(Code.pc));
	}
	
	public void visit(WhileKeyWord whileKeyWord) {
		conditions.push(new ConditionHelper(true));
		loops.peek().FixContinues();
	}
	
	public void visit(DoWhile doWhile) {
		Code.putJump(loops.peek().topAddress);
		conditions.pop().FixEnd();
		loops.pop().FixBreaks();
		
	}
	
	public void visit(BreakStmt breakStmt) {
		Code.putJump(0);
		loops.peek().AddBreak();
	}
	
	public void visit(ContinueStmt continueStmt) {
		Code.putJump(0);
		loops.peek().AddContinue();
	}
	
	public void visit(QMark qMark) {

	}
	
	public void visit(Colon colon) {
		Code.putJump(0);
		conditions.peek().SetFalseJumpEnd();
		conditions.peek().FixElse();
	}
	
	public void visit(TernaryExpression ternaryExpression) {
		isTernary = false;
		conditions.peek().FixEnd();
	}
	
	
	
	
	
	
	
	
}
