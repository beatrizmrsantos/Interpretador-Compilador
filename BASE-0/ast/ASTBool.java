package ast;

import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VBool;

public class ASTBool implements ASTNode{

    VBool v;

    public ASTBool(boolean n) {
        VBool v = new VBool(n);
    }

//    @Override
//    public int eval(EnvironmentInterpreter e) {
//        return 0;
//    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return v;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
