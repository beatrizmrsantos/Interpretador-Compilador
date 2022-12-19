package ast.relational;

import ast.ASTNode;
import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VBool;
import utils.values.VInt;

public class ASTSmallerEqual implements ASTNode {

    public IType type;
    ASTNode lhs, rhs;

    public ASTSmallerEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);

        if	(v1	instanceof VInt) {
            IValue v2 = rhs.eval(e);

            if	(v2	instanceof VInt) {
                return	new VBool(((VInt)v1).get() <= ((VInt)v2).get());
            }
        }

        throw new Error("Illegal types to <= operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);

        if	(t1	instanceof TypeInt) {
            IType t2 = rhs.typecheck(e);

            if	(t2	instanceof TypeInt) {
                type = new TypeBool();
                return type;
            }
        }

        throw new Error("Illegal types to <= operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        String l1 = "L" + c.getLabel();
        String l2 = "L" + c.getLabel();

        lhs.compile(c, e);
        rhs.compile(c, e);

        c.emit("if_icmple " + l1);
        c.emit("iconst_0");
        c.emit("goto " + l2);
        c.emit(l1 + ":");
        c.emit("iconst_1");
        c.emit(l2 + ":");
    }

    @Override
    public IType getType() {
        return type;
    }
}
