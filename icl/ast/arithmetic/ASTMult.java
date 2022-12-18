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

public class ASTMult implements ASTNode {

    public IType type;

    ASTNode lhs, rhs;

    public ASTMult(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
            IValue v2 = rhs.eval(e);

            if	(v2	instanceof VInt) {
                return	new	VInt(((VInt)v1).get()*((VInt)v2).get());
            }
        }

        throw new Error("Illegal types to * operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeInt) {
            IType t2 = rhs.typecheck(e);

            if	(t2	instanceof TypeInt) {
                type = t1;
                return type;
            }
        }

        throw new Error("Illegal types to * operator");
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("imul");
    }

    @Override
    public IType getType() {
        return type;
    }
}
