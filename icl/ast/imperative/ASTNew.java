package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VCell;

public class ASTNew implements ASTNode {

    private ASTNode	expr;

    public ASTNew(ASTNode value){
        expr = value;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = expr.eval(e);
        return new VCell(v1);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
