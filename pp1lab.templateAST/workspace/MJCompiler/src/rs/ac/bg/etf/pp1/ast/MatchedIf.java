// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class MatchedIf extends Matched {

    private IfKeyWord IfKeyWord;
    private IfCondition IfCondition;
    private Matched Matched;
    private ElseKeyWord ElseKeyWord;
    private Matched Matched1;

    public MatchedIf (IfKeyWord IfKeyWord, IfCondition IfCondition, Matched Matched, ElseKeyWord ElseKeyWord, Matched Matched1) {
        this.IfKeyWord=IfKeyWord;
        if(IfKeyWord!=null) IfKeyWord.setParent(this);
        this.IfCondition=IfCondition;
        if(IfCondition!=null) IfCondition.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ElseKeyWord=ElseKeyWord;
        if(ElseKeyWord!=null) ElseKeyWord.setParent(this);
        this.Matched1=Matched1;
        if(Matched1!=null) Matched1.setParent(this);
    }

    public IfKeyWord getIfKeyWord() {
        return IfKeyWord;
    }

    public void setIfKeyWord(IfKeyWord IfKeyWord) {
        this.IfKeyWord=IfKeyWord;
    }

    public IfCondition getIfCondition() {
        return IfCondition;
    }

    public void setIfCondition(IfCondition IfCondition) {
        this.IfCondition=IfCondition;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public ElseKeyWord getElseKeyWord() {
        return ElseKeyWord;
    }

    public void setElseKeyWord(ElseKeyWord ElseKeyWord) {
        this.ElseKeyWord=ElseKeyWord;
    }

    public Matched getMatched1() {
        return Matched1;
    }

    public void setMatched1(Matched Matched1) {
        this.Matched1=Matched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfKeyWord!=null) IfKeyWord.accept(visitor);
        if(IfCondition!=null) IfCondition.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.accept(visitor);
        if(Matched1!=null) Matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfKeyWord!=null) IfKeyWord.traverseTopDown(visitor);
        if(IfCondition!=null) IfCondition.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.traverseTopDown(visitor);
        if(Matched1!=null) Matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfKeyWord!=null) IfKeyWord.traverseBottomUp(visitor);
        if(IfCondition!=null) IfCondition.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ElseKeyWord!=null) ElseKeyWord.traverseBottomUp(visitor);
        if(Matched1!=null) Matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MatchedIf(\n");

        if(IfKeyWord!=null)
            buffer.append(IfKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfCondition!=null)
            buffer.append(IfCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseKeyWord!=null)
            buffer.append(ElseKeyWord.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched1!=null)
            buffer.append(Matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MatchedIf]");
        return buffer.toString();
    }
}
