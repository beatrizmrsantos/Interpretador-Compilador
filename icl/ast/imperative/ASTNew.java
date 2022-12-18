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
import types.TypeBool;
import types.TypeRef;
import utils.values.IValue;
import utils.values.VCell;

public class ASTNew implements ASTNode {

    public IType type;

    private ASTNode	expr;

    public ASTNew(ASTNode value){
        expr = value;
        type = null;
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

        type = new TypeRef(t1);

        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        CellReference ref = c.putAndGetReference(this.getType());

        c.emit("new " + ref.className());
        c.emit("dup");
        c.emit("invokespecial " + ref.className() + "/<init>()V");
        c.emit("dup");
        expr.compile(c, e);
        c.emit("putfield " + ref.className() + "/v " + ref.getType());
    }

    @Override
    public IType getType() {
        return type;
    }
}
