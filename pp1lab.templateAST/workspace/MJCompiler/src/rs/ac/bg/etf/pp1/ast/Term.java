// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class Term implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private Factor Factor;
    private MulopFactor MulopFactor;

    public Term (Factor Factor, MulopFactor MulopFactor) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MulopFactor=MulopFactor;
        if(MulopFactor!=null) MulopFactor.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MulopFactor getMulopFactor() {
        return MulopFactor;
    }

    public void setMulopFactor(MulopFactor MulopFactor) {
        this.MulopFactor=MulopFactor;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(MulopFactor!=null) MulopFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MulopFactor!=null) MulopFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MulopFactor!=null) MulopFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulopFactor!=null)
            buffer.append(MulopFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term]");
        return buffer.toString();
    }
}
