// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class YesAndCondFact extends AndCondFact {

    private CondFact CondFact;
    private AndCondFact AndCondFact;

    public YesAndCondFact (CondFact CondFact, AndCondFact AndCondFact) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.AndCondFact=AndCondFact;
        if(AndCondFact!=null) AndCondFact.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public AndCondFact getAndCondFact() {
        return AndCondFact;
    }

    public void setAndCondFact(AndCondFact AndCondFact) {
        this.AndCondFact=AndCondFact;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(AndCondFact!=null) AndCondFact.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(AndCondFact!=null) AndCondFact.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(AndCondFact!=null) AndCondFact.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("YesAndCondFact(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AndCondFact!=null)
            buffer.append(AndCondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [YesAndCondFact]");
        return buffer.toString();
    }
}
