package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;

//esta classe e para o ; que separa expressoes
public class ASTSep implements ASTNode {

    private ASTNode lhs, rhs;

    public ASTSep(ASTNode l, ASTNode r) {
        lhs = l; rhs = r;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);
        return v2;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
