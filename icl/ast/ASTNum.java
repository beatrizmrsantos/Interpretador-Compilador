package ast;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VInt;

public class ASTNum implements ASTNode {

    VInt val;

//    public int eval(EnvironmentInterpreter e) {
//        return val;
//    }

    public ASTNum(int n) {
        val = new VInt(n);
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return val;
    }

    public void compile(CodeBlock c, EnvironmentCompiler e)	{
        c.emit("sipush " + val);
    }

}

