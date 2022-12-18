package ast;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import utils.Coordinates;
import utils.values.IValue;

public class ASTId implements ASTNode{

    public IType type;
    String	id;

    public ASTId(String id){
        this.id = id;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        if(e.find(id) == null){
            throw new Error("Undeclared Identifier");
        }
        return e.find(id);
    }

    public IType typecheck(EnvironmentType e) {
        if(e.find(id) == null){
            throw new Error("Undeclared Identifier");
        }

        type = e.find(id);
        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        c.emit("aload_3");

        Coordinates cord = e.find(id);
        for(int i = e.depth(); i > cord.getDepth(); i--){
            c.emit("getfield frame_" + (i-2) + "/sl Lframe_" + (i-3) + ";");
        }

        c.emit("getfield frame_" + (cord.getDepth()-2) + "/" + cord.getVar() + " Ljava/lang/Object;");
    }

    @Override
    public IType getType() {
        return type;
    }
}
