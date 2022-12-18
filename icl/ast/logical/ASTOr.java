package ast.logical;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTOr implements ASTNode {

    public IType type;
    ASTNode lhs, rhs;

    public ASTOr(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VBool) {
            IValue v2 = rhs.eval(e);

            if	(v2	instanceof VBool) {
                return	new	VBool(((VBool)v1).get() || ((VBool)v2).get());
            }
        }

        throw new Error("Illegal types to || operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeBool) {
            IType t2 = rhs.typecheck(e);

            if	(t2	instanceof TypeBool) {
                type = t1;
                return type;
            }
        }

        throw new Error("Illegal types to || operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        lhs.compile(c, e);
        rhs.compile(c, e);
        c.emit("ior");
    }

    @Override
    public IType getType() {
        return type;
    }
}
