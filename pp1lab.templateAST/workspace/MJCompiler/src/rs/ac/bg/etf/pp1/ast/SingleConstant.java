// generated with ast extension for cup
// version 0.8
// 27/0/2021 1:7:20


package rs.ac.bg.etf.pp1.ast;

public class SingleConstant extends ListOfConstants {

    private ConstNode ConstNode;

    public SingleConstant (ConstNode ConstNode) {
        this.ConstNode=ConstNode;
        if(ConstNode!=null) ConstNode.setParent(this);
    }

    public ConstNode getConstNode() {
        return ConstNode;
    }

    public void setConstNode(ConstNode ConstNode) {
        this.ConstNode=ConstNode;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstNode!=null) ConstNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstNode!=null) ConstNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstNode!=null) ConstNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstant(\n");

        if(ConstNode!=null)
            buffer.append(ConstNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstant]");
        return buffer.toString();
    }
}
