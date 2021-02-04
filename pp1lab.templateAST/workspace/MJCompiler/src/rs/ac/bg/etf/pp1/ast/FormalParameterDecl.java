// generated with ast extension for cup
// version 0.8
// 3/1/2021 22:30:28


package rs.ac.bg.etf.pp1.ast;

public class FormalParameterDecl extends FormalParamDecl {

    private Type Type;
    private String name;
    private ArrayVarNode ArrayVarNode;

    public FormalParameterDecl (Type Type, String name, ArrayVarNode ArrayVarNode) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.name=name;
        this.ArrayVarNode=ArrayVarNode;
        if(ArrayVarNode!=null) ArrayVarNode.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public ArrayVarNode getArrayVarNode() {
        return ArrayVarNode;
    }

    public void setArrayVarNode(ArrayVarNode ArrayVarNode) {
        this.ArrayVarNode=ArrayVarNode;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ArrayVarNode!=null) ArrayVarNode.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayVarNode!=null) ArrayVarNode.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayVarNode!=null) ArrayVarNode.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParameterDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(ArrayVarNode!=null)
            buffer.append(ArrayVarNode.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParameterDecl]");
        return buffer.toString();
    }
}
