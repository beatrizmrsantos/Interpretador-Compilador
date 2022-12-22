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

public class ASTEqual implements ASTNode {

    public IType type;

    ASTNode lhs, rhs;

    public ASTEqual(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = lhs.eval(e);
        IValue v2 = rhs.eval(e);

        if (v1 instanceof VInt && v2 instanceof VInt) {
            return new VBool(((VInt) v1).get()
                    == ((VInt) v2).get());
        } else if (v1 instanceof VBool && v2 instanceof VBool) {
            return new VBool(((VBool) v1).get()
                    == ((VBool) v2).get());
        }

        throw new Error("Illegal types to == operator");
    }

    public IType typecheck(EnvironmentType e) {
        IType t1 = lhs.typecheck(e);
        IType t2 = rhs.typecheck(e);

        if	((t1 instanceof TypeBool && t2 instanceof TypeBool)
                || (t1 instanceof TypeInt && t2 instanceof TypeInt)) {
            type = new TypeBool();
            return type;
        }

        throw new Error("Illegal types to == operator");
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        String l1 = "L" + c.getLabel();
        String l2 = "L" + c.getLabel();

        lhs.compile(c, e);
        rhs.compile(c, e);

        c.emit("if_icmpeq " + l1);
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
