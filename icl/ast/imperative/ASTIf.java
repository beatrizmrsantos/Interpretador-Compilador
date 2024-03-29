package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import types.TypeRef;
import utils.values.IValue;
import utils.values.VBool;

public class ASTIf implements ASTNode {

    public IType type;
    private ASTNode cond;
    private ASTNode f;
    private ASTNode s;

    public ASTIf(ASTNode cond, ASTNode f, ASTNode s) {
        this.cond = cond;
        this.f = f;
        this.s = s;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue condEval = cond.eval(e);

        if ((condEval instanceof VBool)) {
            if (((VBool) condEval).get()) {
                return f.eval(e);
            } else {
                return s.eval(e);
            }
        }

        throw new Error("illegal arguments to if operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = cond.typecheck(e);

        if	(t1 instanceof TypeBool) {
            IType t2 = f.typecheck(e);
            IType t3 = s.typecheck(e);

            if (!t2.getClass().getName().equals(t3.getClass().getName())
            || t2.equals(new Error()) || t3.equals(new Error())) {
                new Error("Illegal types to if else operators");
            }
            type = t2;
            return t2;
        }

        throw new Error("Illegal types to if operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        String falseLabel = "L" + c.getLabel();
        String trueLabel = "L" + c.getLabel();

        cond.compile(c, e);
        c.emit("ifeq " + falseLabel);
        f.compile(c, e);
        c.emit("goto " + trueLabel);
        c.emit(falseLabel + ":");
        s.compile(c, e);
        c.emit(trueLabel + ":");
    }

    @Override
    public IType getType() {
        return type;
    }
}
