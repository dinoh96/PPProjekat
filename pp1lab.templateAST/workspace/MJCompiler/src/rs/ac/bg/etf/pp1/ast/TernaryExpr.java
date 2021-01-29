// generated with ast extension for cup
// version 0.8
// 29/0/2021 0:23:42


package rs.ac.bg.etf.pp1.ast;

public class TernaryExpr extends Expr {

    private TernaryExpression TernaryExpression;

    public TernaryExpr (TernaryExpression TernaryExpression) {
        this.TernaryExpression=TernaryExpression;
        if(TernaryExpression!=null) TernaryExpression.setParent(this);
    }

    public TernaryExpression getTernaryExpression() {
        return TernaryExpression;
    }

    public void setTernaryExpression(TernaryExpression TernaryExpression) {
        this.TernaryExpression=TernaryExpression;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TernaryExpression!=null) TernaryExpression.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TernaryExpression!=null) TernaryExpression.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TernaryExpression!=null) TernaryExpression.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TernaryExpr(\n");

        if(TernaryExpression!=null)
            buffer.append(TernaryExpression.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TernaryExpr]");
        return buffer.toString();
    }
}
