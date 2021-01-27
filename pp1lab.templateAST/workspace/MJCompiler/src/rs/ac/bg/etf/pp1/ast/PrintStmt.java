// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Matched {

    private Expr Expr;
    private PrintAddition PrintAddition;

    public PrintStmt (Expr Expr, PrintAddition PrintAddition) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.PrintAddition=PrintAddition;
        if(PrintAddition!=null) PrintAddition.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public PrintAddition getPrintAddition() {
        return PrintAddition;
    }

    public void setPrintAddition(PrintAddition PrintAddition) {
        this.PrintAddition=PrintAddition;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(PrintAddition!=null) PrintAddition.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(PrintAddition!=null) PrintAddition.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(PrintAddition!=null) PrintAddition.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(PrintAddition!=null)
            buffer.append(PrintAddition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}