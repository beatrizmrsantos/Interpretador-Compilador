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
import utils.values.VNull;

public class ASTPrintln implements ASTNode {

    private ASTNode	arg;

    public ASTPrintln(ASTNode value){
        arg = value;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        IValue v1 = arg.eval(e);

        System.out.println(v1.toStr());

        return new VNull(null);
    }

    public IType typecheck(EnvironmentType e) {

        return arg.typecheck(e);
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        c.emit("getstatic java/lang/System/out Ljava/io/PrintStream;");
        arg.compile(c, e, t);
        c.emit("invokestatic java/lang/String/valueOf(I)Ljava/lang/String;");
        c.emit("invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V");
    }
}
