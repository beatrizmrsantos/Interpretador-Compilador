package ast.imperative;

import ast.ASTNode;
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

    ASTNode value;

    public ASTDeref(ASTNode l) {
        value = l;
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
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        value.compile(c, e, t);

        IType t1 = value.typecheck(t);
        c.emit("getfield ref_of_" + t1.getName() + "/v " + t1.getName());
    }
}
