// generated with ast extension for cup
// version 0.8
// 29/0/2021 0:23:42


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarDeclarationList extends GlobalVarDeclList {

    private GlobalVarDeclList GlobalVarDeclList;
    private VarOrConstDeclList VarOrConstDeclList;

    public GlobalVarDeclarationList (GlobalVarDeclList GlobalVarDeclList, VarOrConstDeclList VarOrConstDeclList) {
        this.GlobalVarDeclList=GlobalVarDeclList;
        if(GlobalVarDeclList!=null) GlobalVarDeclList.setParent(this);
        this.VarOrConstDeclList=VarOrConstDeclList;
        if(VarOrConstDeclList!=null) VarOrConstDeclList.setParent(this);
    }

    public GlobalVarDeclList getGlobalVarDeclList() {
        return GlobalVarDeclList;
    }

    public void setGlobalVarDeclList(GlobalVarDeclList GlobalVarDeclList) {
        this.GlobalVarDeclList=GlobalVarDeclList;
    }

    public VarOrConstDeclList getVarOrConstDeclList() {
        return VarOrConstDeclList;
    }

    public void setVarOrConstDeclList(VarOrConstDeclList VarOrConstDeclList) {
        this.VarOrConstDeclList=VarOrConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarDeclList!=null) GlobalVarDeclList.accept(visitor);
        if(VarOrConstDeclList!=null) VarOrConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarDeclList!=null) GlobalVarDeclList.traverseTopDown(visitor);
        if(VarOrConstDeclList!=null) VarOrConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarDeclList!=null) GlobalVarDeclList.traverseBottomUp(visitor);
        if(VarOrConstDeclList!=null) VarOrConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarDeclarationList(\n");

        if(GlobalVarDeclList!=null)
            buffer.append(GlobalVarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarOrConstDeclList!=null)
            buffer.append(VarOrConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarDeclarationList]");
        return buffer.toString();
    }
}
