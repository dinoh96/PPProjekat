// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class Multiplication extends Mulop {

    public Multiplication () {
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
        buffer.append("Multiplication(\n");

        buffer.append(tab);
        buffer.append(") [Multiplication]");
        return buffer.toString();
    }
}