// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class ProcCall extends DesignatorStatement {

    private Designator Designator;
    private DesignatorActPars DesignatorActPars;

    public ProcCall (Designator Designator, DesignatorActPars DesignatorActPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.DesignatorActPars=DesignatorActPars;
        if(DesignatorActPars!=null) DesignatorActPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public DesignatorActPars getDesignatorActPars() {
        return DesignatorActPars;
    }

    public void setDesignatorActPars(DesignatorActPars DesignatorActPars) {
        this.DesignatorActPars=DesignatorActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(DesignatorActPars!=null) DesignatorActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(DesignatorActPars!=null) DesignatorActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(DesignatorActPars!=null) DesignatorActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ProcCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorActPars!=null)
            buffer.append(DesignatorActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ProcCall]");
        return buffer.toString();
    }
}
