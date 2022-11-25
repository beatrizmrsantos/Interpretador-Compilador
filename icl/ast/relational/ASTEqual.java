package ast.relational;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTEqual implements ASTNode {

    ASTNode lhs, rhs;

    public ASTEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
            IValue v2 = rhs.eval(e);

            if	(v1	instanceof VInt) {
                return	new VBool(((VInt)v1).get() == ((VInt)v2).get());
            }
        }

        throw new Error("Illegal types to == operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }
}
