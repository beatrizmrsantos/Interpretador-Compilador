package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VBool;

public class ASTIf implements ASTNode {

    private ASTNode cond;
    private ASTNode f;
    private ASTNode s;

    public ASTIf(ASTNode cond, ASTNode f, ASTNode s) {
        this.cond = cond;
        this.f = f;
        this.s = s;
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

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
