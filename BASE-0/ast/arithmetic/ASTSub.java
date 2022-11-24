package ast.arithmetic;
import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VInt;

public class ASTSub implements ASTNode {

    ASTNode lhs, rhs;

//    public int eval(EnvironmentInterpreter e)
//    {
//        int v1 = lhs.eval(e);
//        int v2 = rhs.eval(e);
//        return v1-v2;
//    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
            IValue v2 = rhs.eval(e);

            if	(v1	instanceof VInt) {
                return	new	VInt(((VInt)v1).get()-((VInt)v2).get());
            }
        }

        throw new Error("Illegal types to - operator");
    }

    public ASTSub(ASTNode l, ASTNode r)
    {
        lhs = l; rhs = r;
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("isub");
    }
}
