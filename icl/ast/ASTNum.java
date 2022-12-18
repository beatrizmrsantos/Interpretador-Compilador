package ast;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VInt;

public class ASTNum implements ASTNode {

    public IType type;

    int val;

    public ASTNum(int n) {
        val = n;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return new VInt(val);
    }

    public IType typecheck(EnvironmentType e) {
        type = new TypeInt();
        return type;
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        c.emit("sipush " + val);
    }

    @Override
    public IType getType() {
        return type;
    }
}

