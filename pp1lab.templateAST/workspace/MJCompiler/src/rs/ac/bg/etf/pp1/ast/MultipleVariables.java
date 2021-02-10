// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class MultipleVariables extends ListOfVariables {

    private ListOfVariables ListOfVariables;
    private VarNode VarNode;

    public MultipleVariables (ListOfVariables ListOfVariables, VarNode VarNode) {
        this.ListOfVariables=ListOfVariables;
        if(ListOfVariables!=null) ListOfVariables.setParent(this);
        this.VarNode=VarNode;
        if(VarNode!=null) VarNode.setParent(this);
    }

    public ListOfVariables getListOfVariables() {
        return ListOfVariables;
    }

    public void setListOfVariables(ListOfVariables ListOfVariables) {
        this.ListOfVariables=ListOfVariables;
    }

    public VarNode getVarNode() {
        return VarNode;
    }

    public void setVarNode(VarNode VarNode) {
        this.VarNode=VarNode;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ListOfVariables!=null) ListOfVariables.accept(visitor);
        if(VarNode!=null) VarNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ListOfVariables!=null) ListOfVariables.traverseTopDown(visitor);
        if(VarNode!=null) VarNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ListOfVariables!=null) ListOfVariables.traverseBottomUp(visitor);
        if(VarNode!=null) VarNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVariables(\n");

        if(ListOfVariables!=null)
            buffer.append(ListOfVariables.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarNode!=null)
            buffer.append(VarNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVariables]");
        return buffer.toString();
    }
}
