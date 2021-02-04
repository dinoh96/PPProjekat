// generated with ast extension for cup
// version 0.8
// 3/1/2021 22:30:28


package rs.ac.bg.etf.pp1.ast;

public abstract class VisitorAdaptor implements Visitor { 

    public void visit(Unmatched Unmatched) { }
    public void visit(Mulop Mulop) { }
    public void visit(Matched Matched) { }
    public void visit(Relop Relop) { }
    public void visit(FormalParamDecl FormalParamDecl) { }
    public void visit(StatementList StatementList) { }
    public void visit(MulopFactor MulopFactor) { }
    public void visit(Addop Addop) { }
    public void visit(OrCondTerm OrCondTerm) { }
    public void visit(Factor Factor) { }
    public void visit(DesignatorActPars DesignatorActPars) { }
    public void visit(Designator Designator) { }
    public void visit(ConstantVars ConstantVars) { }
    public void visit(ActParsList ActParsList) { }
    public void visit(IfCondition IfCondition) { }
    public void visit(ConstNode ConstNode) { }
    public void visit(ListOfVariables ListOfVariables) { }
    public void visit(FactorActPars FactorActPars) { }
    public void visit(VarOrConstDeclList VarOrConstDeclList) { }
    public void visit(VarDeclList VarDeclList) { }
    public void visit(FormalParamList FormalParamList) { }
    public void visit(Expr Expr) { }
    public void visit(MethodTypeName MethodTypeName) { }
    public void visit(DesignatorStatement DesignatorStatement) { }
    public void visit(AndCondFact AndCondFact) { }
    public void visit(ArrayVarNode ArrayVarNode) { }
    public void visit(Statement Statement) { }
    public void visit(Expression Expression) { }
    public void visit(ReturnExpr ReturnExpr) { }
    public void visit(NonTernaryExpression NonTernaryExpression) { }
    public void visit(CondFact CondFact) { }
    public void visit(PrintAddition PrintAddition) { }
    public void visit(MethodDeclList MethodDeclList) { }
    public void visit(GlobalVarDeclList GlobalVarDeclList) { }
    public void visit(FormPars FormPars) { }
    public void visit(ListOfConstants ListOfConstants) { }
    public void visit(SingleCondFact SingleCondFact) { visit(); }
    public void visit(MultiCondFact MultiCondFact) { visit(); }
    public void visit(NoAndCondFact NoAndCondFact) { visit(); }
    public void visit(YesAndCondFact YesAndCondFact) { visit(); }
    public void visit(CondTerm CondTerm) { visit(); }
    public void visit(NoOrCondTerm NoOrCondTerm) { visit(); }
    public void visit(YesOrCondTerm YesOrCondTerm) { visit(); }
    public void visit(Condition Condition) { visit(); }
    public void visit(NoMulopFactor NoMulopFactor) { visit(); }
    public void visit(YesMulopFactor YesMulopFactor) { visit(); }
    public void visit(Term Term) { visit(); }
    public void visit(NoActParsList NoActParsList) { visit(); }
    public void visit(YesActParsList YesActParsList) { visit(); }
    public void visit(ActPars ActPars) { visit(); }
    public void visit(NoFactorActPars NoFactorActPars) { visit(); }
    public void visit(YesFactorActPars YesFactorActPars) { visit(); }
    public void visit(BoolVar BoolVar) { visit(); }
    public void visit(CharVar CharVar) { visit(); }
    public void visit(IntVar IntVar) { visit(); }
    public void visit(FuncCall FuncCall) { visit(); }
    public void visit(NewObjectVar NewObjectVar) { visit(); }
    public void visit(ExpressionVar ExpressionVar) { visit(); }
    public void visit(ConstFactors ConstFactors) { visit(); }
    public void visit(Variable Variable) { visit(); }
    public void visit(TermExpr TermExpr) { visit(); }
    public void visit(AddExpr AddExpr) { visit(); }
    public void visit(PositiveExpr PositiveExpr) { visit(); }
    public void visit(NegativeExpr NegativeExpr) { visit(); }
    public void visit(Colon Colon) { visit(); }
    public void visit(QMark QMark) { visit(); }
    public void visit(TernaryExpression TernaryExpression) { visit(); }
    public void visit(TernaryExpr TernaryExpr) { visit(); }
    public void visit(NonTernaryExpr NonTernaryExpr) { visit(); }
    public void visit(NoDesignatorActPars NoDesignatorActPars) { visit(); }
    public void visit(YesDesignatorActPars YesDesignatorActPars) { visit(); }
    public void visit(ProcCall ProcCall) { visit(); }
    public void visit(DesignatorDecrement DesignatorDecrement) { visit(); }
    public void visit(DesignatorIncrement DesignatorIncrement) { visit(); }
    public void visit(DesignatorAssignment DesignatorAssignment) { visit(); }
    public void visit(GreaterEqual GreaterEqual) { visit(); }
    public void visit(GreaterThan GreaterThan) { visit(); }
    public void visit(LesserEqual LesserEqual) { visit(); }
    public void visit(LesserThan LesserThan) { visit(); }
    public void visit(NotEquals NotEquals) { visit(); }
    public void visit(Equals Equals) { visit(); }
    public void visit(Modulus Modulus) { visit(); }
    public void visit(Division Division) { visit(); }
    public void visit(Multiplication Multiplication) { visit(); }
    public void visit(Subtraction Subtraction) { visit(); }
    public void visit(Addition Addition) { visit(); }
    public void visit(DesignatorArrayName DesignatorArrayName) { visit(); }
    public void visit(DesignatorArray DesignatorArray) { visit(); }
    public void visit(DesignatorNonArray DesignatorNonArray) { visit(); }
    public void visit(NoPrintAddition NoPrintAddition) { visit(); }
    public void visit(YesPrintAddition YesPrintAddition) { visit(); }
    public void visit(NoReturnExpr NoReturnExpr) { visit(); }
    public void visit(YesReturnExpr YesReturnExpr) { visit(); }
    public void visit(BlockStatement BlockStatement) { visit(); }
    public void visit(ErrorStmt ErrorStmt) { visit(); }
    public void visit(DoWhile DoWhile) { visit(); }
    public void visit(MatchedIf MatchedIf) { visit(); }
    public void visit(ContinueStmt ContinueStmt) { visit(); }
    public void visit(BreakStmt BreakStmt) { visit(); }
    public void visit(ReturnStmt ReturnStmt) { visit(); }
    public void visit(PrintStmt PrintStmt) { visit(); }
    public void visit(ReadStmt ReadStmt) { visit(); }
    public void visit(DesignatorStmt DesignatorStmt) { visit(); }
    public void visit(IfConditionError IfConditionError) { visit(); }
    public void visit(IfCond IfCond) { visit(); }
    public void visit(UnmatchedIfElse UnmatchedIfElse) { visit(); }
    public void visit(UnmatchedIf UnmatchedIf) { visit(); }
    public void visit(WhileKeyWord WhileKeyWord) { visit(); }
    public void visit(DoKeyWord DoKeyWord) { visit(); }
    public void visit(ElseKeyWord ElseKeyWord) { visit(); }
    public void visit(IfKeyWord IfKeyWord) { visit(); }
    public void visit(UnmachedStmt UnmachedStmt) { visit(); }
    public void visit(MatchedStmt MatchedStmt) { visit(); }
    public void visit(NoStmt NoStmt) { visit(); }
    public void visit(Statements Statements) { visit(); }
    public void visit(FormalParamDeclError FormalParamDeclError) { visit(); }
    public void visit(FormalParameterDecl FormalParameterDecl) { visit(); }
    public void visit(SingleFormalParamDecl SingleFormalParamDecl) { visit(); }
    public void visit(FormalParamDecls FormalParamDecls) { visit(); }
    public void visit(NoFormParam NoFormParam) { visit(); }
    public void visit(FormParams FormParams) { visit(); }
    public void visit(VoidMethodTypeName VoidMethodTypeName) { visit(); }
    public void visit(NonVoidMethodTypeName NonVoidMethodTypeName) { visit(); }
    public void visit(MethodDecl MethodDecl) { visit(); }
    public void visit(NoMethodDecl NoMethodDecl) { visit(); }
    public void visit(MethodDeclarations MethodDeclarations) { visit(); }
    public void visit(Type Type) { visit(); }
    public void visit(NoArrayDecl NoArrayDecl) { visit(); }
    public void visit(ArrayDecl ArrayDecl) { visit(); }
    public void visit(VarNode VarNode) { visit(); }
    public void visit(SingleVariable SingleVariable) { visit(); }
    public void visit(MultipleVariables MultipleVariables) { visit(); }
    public void visit(ErrorConstNodeComma ErrorConstNodeComma) { visit(); }
    public void visit(ConstantNode ConstantNode) { visit(); }
    public void visit(SingleConstant SingleConstant) { visit(); }
    public void visit(MultipleConstants MultipleConstants) { visit(); }
    public void visit(VarDecl VarDecl) { visit(); }
    public void visit(ConstDecl ConstDecl) { visit(); }
    public void visit(NoVarDeclarations NoVarDeclarations) { visit(); }
    public void visit(VarDeclarations VarDeclarations) { visit(); }
    public void visit(ConstDeclarationList ConstDeclarationList) { visit(); }
    public void visit(VariablesDeclarationList VariablesDeclarationList) { visit(); }
    public void visit(NoGlobalVarDeclarationList NoGlobalVarDeclarationList) { visit(); }
    public void visit(GlobalVarDeclarationList GlobalVarDeclarationList) { visit(); }
    public void visit(ProgName ProgName) { visit(); }
    public void visit(Program Program) { visit(); }


    public void visit() { }
}
