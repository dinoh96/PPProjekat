// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class ConstNode implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String constName;
    private ConstantVars ConstantVars;

    public ConstNode (String constName, ConstantVars ConstantVars) {
        this.constName=constName;
        this.ConstantVars=ConstantVars;
        if(ConstantVars!=null) ConstantVars.setParent(this);
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstantVars getConstantVars() {
        return ConstantVars;
    }

    public void setConstantVars(ConstantVars ConstantVars) {
        this.ConstantVars=ConstantVars;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstantVars!=null) ConstantVars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstantVars!=null) ConstantVars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstantVars!=null) ConstantVars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstNode(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstantVars!=null)
            buffer.append(ConstantVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstNode]");
        return buffer.toString();
    }
}
