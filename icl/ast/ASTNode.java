package ast;
import compiler.CodeBlock;
import environment.Environment;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;

public interface ASTNode {

    //int eval(EnvironmentInterpreter e);

    IValue eval(EnvironmentValue e);

    void compile(CodeBlock c, EnvironmentCompiler e);

}

