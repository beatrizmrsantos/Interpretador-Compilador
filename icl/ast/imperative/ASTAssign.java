package ast.imperative;

import ast.ASTNode;
import compiler.CellReference;
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

    public IType type;

    private ASTNode lhs, rhs;

    public ASTAssign(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
        type = null;
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

        if	(t1 instanceof TypeRef
                && ((TypeRef) t1).getRefType().getClass().
                getSimpleName().equals(t2.getClass().getSimpleName())) {
            type = t2;
            return t2;
        }

        throw new Error("Illegal types to := operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        CellReference ref = c.putAndGetReference(rhs.getType());

        rhs.compile(c, e);
        c.emit("dup");

        lhs.compile(c, e);

        c.emit("checkcast " + ref.className());
        c.emit("swap");
        c.emit("putfield " + ref.className() + "/v " + ref.getType());
    }

    @Override
    public IType getType() {
        return type;
    }
}
