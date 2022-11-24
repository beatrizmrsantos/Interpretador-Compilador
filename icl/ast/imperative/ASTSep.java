package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;

//esta classe e para o ; que separa expressoes
public class ASTSep implements ASTNode {
//    @Override
//    public int eval(EnvironmentInterpreter e) {
//        return 0;
//    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return null;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
