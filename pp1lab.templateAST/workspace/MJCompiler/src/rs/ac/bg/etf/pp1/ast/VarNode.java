// generated with ast extension for cup
// version 0.8
// 3/1/2021 22:30:28


package rs.ac.bg.etf.pp1.ast;

public class VarNode implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private String varName;
    private ArrayVarNode ArrayVarNode;

    public VarNode (String varName, ArrayVarNode ArrayVarNode) {
        this.varName=varName;
        this.ArrayVarNode=ArrayVarNode;
        if(ArrayVarNode!=null) ArrayVarNode.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ArrayVarNode getArrayVarNode() {
        return ArrayVarNode;
    }

    public void setArrayVarNode(ArrayVarNode ArrayVarNode) {
        this.ArrayVarNode=ArrayVarNode;
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
        if(ArrayVarNode!=null) ArrayVarNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ArrayVarNode!=null) ArrayVarNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ArrayVarNode!=null) ArrayVarNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarNode(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ArrayVarNode!=null)
            buffer.append(ArrayVarNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarNode]");
        return buffer.toString();
    }
}
