package ast.logical;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTNot implements ASTNode {

    ASTNode lhs;

    public ASTNot(ASTNode l) {
        lhs = l;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VBool) {
            return	new	VBool( !((VBool)v1).get() );
        }

        throw new Error("Illegal types to ~ operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
