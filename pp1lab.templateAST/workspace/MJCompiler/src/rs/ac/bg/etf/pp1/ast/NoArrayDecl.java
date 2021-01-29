// generated with ast extension for cup
// version 0.8
// 29/0/2021 0:23:42


package rs.ac.bg.etf.pp1.ast;

public class NoArrayDecl extends ArrayVarNode {

    public NoArrayDecl () {
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
        buffer.append("NoArrayDecl(\n");

        buffer.append(tab);
        buffer.append(") [NoArrayDecl]");
        return buffer.toString();
    }
}
