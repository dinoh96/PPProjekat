// generated with ast extension for cup
// version 0.8
// 3/1/2021 22:30:28


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstants extends ListOfConstants {

    private ListOfConstants ListOfConstants;
    private ConstNode ConstNode;

    public MultipleConstants (ListOfConstants ListOfConstants, ConstNode ConstNode) {
        this.ListOfConstants=ListOfConstants;
        if(ListOfConstants!=null) ListOfConstants.setParent(this);
        this.ConstNode=ConstNode;
        if(ConstNode!=null) ConstNode.setParent(this);
    }

    public ListOfConstants getListOfConstants() {
        return ListOfConstants;
    }

    public void setListOfConstants(ListOfConstants ListOfConstants) {
        this.ListOfConstants=ListOfConstants;
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
        if(ListOfConstants!=null) ListOfConstants.accept(visitor);
        if(ConstNode!=null) ConstNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfConstants!=null) ListOfConstants.traverseTopDown(visitor);
        if(ConstNode!=null) ConstNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfConstants!=null) ListOfConstants.traverseBottomUp(visitor);
        if(ConstNode!=null) ConstNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstants(\n");

        if(ListOfConstants!=null)
            buffer.append(ListOfConstants.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstNode!=null)
            buffer.append(ConstNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstants]");
        return buffer.toString();
    }
}
