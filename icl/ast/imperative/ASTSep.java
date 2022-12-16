package ast.imperative;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
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

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if (t1.equals(new Error())) {
            throw new Error("illegal arguments to ; operator");
        }

        return rhs.typecheck(e);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        lhs.compile(c, e, t);
        rhs.compile(c, e, t);
    }
}
