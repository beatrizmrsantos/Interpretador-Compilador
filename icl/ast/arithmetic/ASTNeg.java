package ast.arithmetic;
import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VInt;

public class ASTNeg implements ASTNode {

    public IType type;

    ASTNode lhs;

    public ASTNeg(ASTNode l)
    {
        lhs = l;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
                return	new	VInt(- ((VInt)v1).get() );
            }

        throw new Error("Illegal types to neg operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeInt) {
            type = t1;
            return type;
        }

        throw new Error("Illegal types to neg operator");
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        lhs.compile(c, e);
        c.emit("ineg");
    }

    @Override
    public IType getType() {
        return type;
    }
}
