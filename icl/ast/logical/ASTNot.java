package ast.logical;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTNot implements ASTNode {

    public IType type;
    ASTNode lhs;

    public ASTNot(ASTNode l) {
        lhs = l;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VBool) {
            return	new	VBool( !((VBool)v1).get() );
        }

        throw new Error("Illegal types to ~ operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeBool) {
            type = t1;
            return type;
        }

        throw new Error("Illegal types to ~ operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        lhs.compile(c, e);
        c.emit("ineg");
    }

    @Override
    public IType getType() {
        return type;
    }
}
