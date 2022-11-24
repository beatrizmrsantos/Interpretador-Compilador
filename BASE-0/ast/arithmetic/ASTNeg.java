package ast.arithmetic;
import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VInt;

public class ASTNeg implements ASTNode {

    ASTNode lhs;

    public ASTNeg(ASTNode l)
    {
        lhs = l;
    }

//    public int eval(EnvironmentInterpreter e)
//    {
//        int v1 = lhs.eval(e);
//        return -v1;
//    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
                return	new	VInt(- ((VInt)v1).get() );
            }

        throw new Error("Illegal types to neg operator");
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        lhs.compile(c, e);
        c.emit("ineg");
    }
}
