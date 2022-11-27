package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
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

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
