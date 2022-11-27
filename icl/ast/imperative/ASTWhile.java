package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
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

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
