package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VCell;

public class ASTAssign implements ASTNode {

    private ASTNode lhs, rhs;

    public ASTAssign(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if (v1 instanceof VCell) {
            IValue v2 = rhs.eval(e);

            ((VCell) v1).set(v2);
            return v2;
        }

        throw new Error("illegal arguments to := operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
