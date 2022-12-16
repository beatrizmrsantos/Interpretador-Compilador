package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeRef;
import utils.values.IValue;
import utils.values.VCell;

public class ASTNew implements ASTNode {

    private ASTNode	expr;

    public ASTNew(ASTNode value){
        expr = value;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = expr.eval(e);
        return new VCell(v1);
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = expr.typecheck(e);

        if (t1.equals(new Error())) {
            throw new Error("illegal arguments to new operator");
        }

        return new TypeRef(t1);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        IType t1 = expr.typecheck(t);

        c.emit("putfieldnew ref_of_" + t1.getName());
        c.emit("dup");
        c.emit("invokespecial ref_of_" + t1.getName() + "/<init>()V");
        c.emit("dup");
        expr.compile(c, e, t);
        c.emit("putfield ref_of_" + t1.getName() + "/v " + t1.getName());
    }
}
