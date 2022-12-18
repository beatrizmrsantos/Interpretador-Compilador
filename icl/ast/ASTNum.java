package ast;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VInt;

public class ASTNum implements ASTNode {

    public IType type;

    VInt val;

    public ASTNum(int n) {
        val = new VInt(n);
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return val;
    }

    public IType typecheck(EnvironmentType e) {
        return new TypeInt();
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        c.emit("sipush " + val);
    }

    @Override
    public IType getType() {
        return type;
    }
}

