// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class Semi extends CommaOrSemi {

    public Semi () {
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
        buffer.append("Semi(\n");

        buffer.append(tab);
        buffer.append(") [Semi]");
        return buffer.toString();
    }
}
