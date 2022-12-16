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

    private ASTNode cond, body;

    public ASTWhile(ASTNode cond, ASTNode body) {
        this.cond = cond;
        this.body = body;
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

        return new TypeBool();
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        c.emit("LStart: ");
        cond.compile(c, e, t);
        c.emit("TL: ");
        body.compile(c, e, t);
        c.emit("pop ");
        c.emit("goto Start ");
        c.emit("FL: ");

    }
}
