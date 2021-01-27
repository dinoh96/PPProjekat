// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class DoWhile extends Matched {

    private DoKeyWord DoKeyWord;
    private Statement Statement;
    private WhileKeyWord WhileKeyWord;
    private Condition Condition;

    public DoWhile (DoKeyWord DoKeyWord, Statement Statement, WhileKeyWord WhileKeyWord, Condition Condition) {
        this.DoKeyWord=DoKeyWord;
        if(DoKeyWord!=null) DoKeyWord.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.WhileKeyWord=WhileKeyWord;
        if(WhileKeyWord!=null) WhileKeyWord.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
    }

    public DoKeyWord getDoKeyWord() {
        return DoKeyWord;
    }

    public void setDoKeyWord(DoKeyWord DoKeyWord) {
        this.DoKeyWord=DoKeyWord;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public WhileKeyWord getWhileKeyWord() {
        return WhileKeyWord;
    }

    public void setWhileKeyWord(WhileKeyWord WhileKeyWord) {
        this.WhileKeyWord=WhileKeyWord;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DoKeyWord!=null) DoKeyWord.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(WhileKeyWord!=null) WhileKeyWord.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DoKeyWord!=null) DoKeyWord.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(WhileKeyWord!=null) WhileKeyWord.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DoKeyWord!=null) DoKeyWord.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(WhileKeyWord!=null) WhileKeyWord.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoWhile(\n");

        if(DoKeyWord!=null)
            buffer.append(DoKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WhileKeyWord!=null)
            buffer.append(WhileKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DoWhile]");
        return buffer.toString();
    }
}
