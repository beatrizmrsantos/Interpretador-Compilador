package ast.arithmetic;
import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Memory;
import environment.*;
import types.IType;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VInt;

public class ASTPlus implements ASTNode {

    ASTNode lhs, rhs;

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
            IValue v2 = rhs.eval(e);

            if	(v2	instanceof VInt) {
                return	new	VInt(((VInt)v1).get()+((VInt)v2).get());
            }
        }

        throw new Error("Illegal types to + operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeInt) {
            IType t2 = rhs.typecheck(e);

            if	(t2	instanceof TypeInt) {
                return t1;
            }
        }

        throw new Error("Illegal types to + operator");
    }

    public ASTPlus(ASTNode l, ASTNode r) {
		lhs = l; rhs = r;
    }

    public	void	compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t)	{
        lhs.compile(c, e, t);
        rhs.compile(c, e, t);
        c.emit( "iadd" );
    }
}

