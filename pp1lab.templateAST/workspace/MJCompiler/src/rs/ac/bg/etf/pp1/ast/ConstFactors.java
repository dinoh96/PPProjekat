// generated with ast extension for cup
// version 0.8
// 29/0/2021 0:23:42


package rs.ac.bg.etf.pp1.ast;

public class ConstFactors extends Factor {

    private ConstantVars ConstantVars;

    public ConstFactors (ConstantVars ConstantVars) {
        this.ConstantVars=ConstantVars;
        if(ConstantVars!=null) ConstantVars.setParent(this);
    }

    public ConstantVars getConstantVars() {
        return ConstantVars;
    }

    public void setConstantVars(ConstantVars ConstantVars) {
        this.ConstantVars=ConstantVars;
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
        buffer.append("ConstFactors(\n");

        if(ConstantVars!=null)
            buffer.append(ConstantVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstFactors]");
        return buffer.toString();
    }
}
