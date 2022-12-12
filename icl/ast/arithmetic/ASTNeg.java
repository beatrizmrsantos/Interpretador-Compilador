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

    ASTNode lhs;

    public ASTNeg(ASTNode l)
    {
        lhs = l;
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
            return t1;
        }

        throw new Error("Illegal types to neg operator");
    }

    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t)	{
        lhs.compile(c, e, t);
        c.emit("ineg");
    }
}
