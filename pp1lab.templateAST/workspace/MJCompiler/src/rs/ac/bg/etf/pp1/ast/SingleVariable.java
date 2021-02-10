// generated with ast extension for cup
// version 0.8
// 10/1/2021 1:50:1


package rs.ac.bg.etf.pp1.ast;

public class SingleVariable extends ListOfVariables {

    private VarNode VarNode;

    public SingleVariable (VarNode VarNode) {
        this.VarNode=VarNode;
        if(VarNode!=null) VarNode.setParent(this);
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
        if(VarNode!=null) VarNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarNode!=null) VarNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarNode!=null) VarNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVariable(\n");

        if(VarNode!=null)
            buffer.append(VarNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVariable]");
        return buffer.toString();
    }
}
