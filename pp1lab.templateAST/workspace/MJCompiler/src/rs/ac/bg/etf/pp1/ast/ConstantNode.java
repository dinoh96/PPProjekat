// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class ConstantNode extends ConstNode {

    private String constName;
    private ConstantVars ConstantVars;

    public ConstantNode (String constName, ConstantVars ConstantVars) {
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
        buffer.append("ConstantNode(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstantVars!=null)
            buffer.append(ConstantVars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstantNode]");
        return buffer.toString();
    }
}
