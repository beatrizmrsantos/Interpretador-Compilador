package ast.imperative;

import ast.ASTNode;
import compiler.CellReference;
import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeRef;
import utils.values.IValue;
import utils.values.VCell;

public class ASTDeref implements ASTNode {

    public IType type;
    ASTNode value;

    public ASTDeref(ASTNode l) {
        value = l;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = value.eval(e);

        if (v1 instanceof VCell) {
            IValue v = ((VCell) v1).get();
            return v;
        }

        throw new Error("illegal arguments to ! operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = value.typecheck(e);

        if (t1 instanceof TypeRef) {
            return ((TypeRef) t1).getRefType();
        }

        throw new Error("illegal arguments to ! operator");

    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        value.compile(c, e);
        CellReference ref = c.putAndGetReference(value.getType());

        c.emit("checkcast " + ref.className());
        c.emit("getfield ref_of_" + ref.className() + "/v " + ref.getType());
    }

    @Override
    public IType getType() {
        return type;
    }
}
