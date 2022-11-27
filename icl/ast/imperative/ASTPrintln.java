package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;

public class ASTPrintln implements ASTNode {

    private ASTNode	arg;

    public ASTPrintln(ASTNode value){
        arg = value;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = arg.eval(e);

        System.out.println(v1.toStr());

        return v1;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
