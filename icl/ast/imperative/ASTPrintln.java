package ast.imperative;

import ast.ASTNode;
import compiler.CellReference;
import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import utils.values.IValue;
import utils.values.VNull;

public class ASTPrintln implements ASTNode {

    public IType type;

    private ASTNode	arg;

    public ASTPrintln(ASTNode value){
        arg = value;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = arg.eval(e);

        System.out.println(v1.toStr());

        return new VNull(null);
    }

    public IType typecheck(EnvironmentType e) {
        type = arg.typecheck(e);
        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        CellReference ref = c.putAndGetReference(arg.getType());

        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
        arg.compile(c, e);
        c.emit("invokestatic java/lang/String/valueOf(" + ref.getType() + ")Ljava/lang/String;");
        c.emit("invokevirtual java/io/PrintStream/println(" + ref.getType() + ")V");
    }

    @Override
    public IType getType() {
        return type;
    }
}
