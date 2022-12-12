package ast.relational;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTEqual implements ASTNode {

    ASTNode lhs, rhs;

    public ASTEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).get() == ((VInt) v2).get());
        } else if (v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).get() == ((VBool) v2).get());
        }

        throw new Error("Illegal types to == operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);
        IType t2 = rhs.typecheck(e);

        if	((t1 instanceof TypeBool && t2 instanceof TypeBool) || (t1 instanceof TypeInt && t2 instanceof TypeInt)) {
            return new TypeBool();
        }

        throw new Error("Illegal types to == operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {

    }
}
