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
import utils.values.IValue;
import utils.values.VBool;

public class ASTWhile implements ASTNode {

    public IType type;
    private ASTNode cond, body;

    public ASTWhile(ASTNode cond, ASTNode body) {
        this.cond = cond;
        this.body = body;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue condEval = cond.eval(e);

        if ((condEval instanceof VBool)) {
            if (((VBool) condEval).get()) {
                body.eval(e);
                return eval(e);
            } else {
                return condEval;
            }
        }

        throw new Error("illegal arguments to while operator");
    }

    public IType typecheck(EnvironmentType e){
        IType t1 = cond.typecheck(e);
        IType t2 = body.typecheck(e);

        if (!(t1 instanceof TypeBool) || t2.equals(new Error())) {
            throw new Error("illegal arguments to while operator");
        }

        type = new TypeBool();
        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        int l1 = c.getLabel();
        int l2 = c.getLabel();

        c.emit("L" + l1 + ":");
        cond.compile(c, e);
        c.emit("ifeq L" + l2);
        body.compile(c, e);
        c.emit("pop");
        c.emit("goto L" + l1);
        c.emit("L" + l2 + ":");

    }

    @Override
    public IType getType() {
        return type;
    }
}
