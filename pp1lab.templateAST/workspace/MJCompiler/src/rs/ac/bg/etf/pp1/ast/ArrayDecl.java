// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class ArrayDecl extends ArrayVarNode {

    public ArrayDecl () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ArrayDecl(\n");

        buffer.append(tab);
        buffer.append(") [ArrayDecl]");
        return buffer.toString();
    }
}
