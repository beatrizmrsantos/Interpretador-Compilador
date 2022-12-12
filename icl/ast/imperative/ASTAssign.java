package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeInt;
import types.TypeRef;
import utils.values.IValue;
import utils.values.VCell;

public class ASTAssign implements ASTNode {

    private ASTNode lhs, rhs;

    public ASTAssign(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VCell) {
            IValue v2 = rhs.eval(e);

            ((VCell) v1).set(v2);
            return v2;
        }

        throw new Error("illegal arguments to := operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);
        IType t2 = rhs.typecheck(e);

        if	(t1 instanceof TypeRef && ((TypeRef) t1).getRefType().getClass().getSimpleName().equals(t2.getClass().getSimpleName())) {
            return t2;
        }

        throw new Error("Illegal types to := operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {

    }
}
