package ast;

import java.util.ArrayList;
import java.util.List;

import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeFun;
import utils.values.IValue;
import utils.values.VFun;

public class ASTCall implements ASTNode {

    private IType type;
    private ASTNode fun;
    private final List<ASTNode> args;

    public ASTCall(List<ASTNode> args, ASTNode fun) {
        this.fun = fun;
        this.args = args;
        this.type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e){
        VFun f = (VFun) fun.eval(e);
        List<IValue> argEvals = new ArrayList<>();
        for (ASTNode arg : args) {
            argEvals.add(arg.eval(e));
        }
        return f.call(argEvals);
    }

    @Override
    public IType typecheck(EnvironmentType e){
        IType idType = fun.typecheck(e);
        if (idType instanceof TypeFun) {
            TypeFun fType = (TypeFun) idType;
            fType.checkArgs(args, e);
            this.type = fType.getType();
            return type;
        } else {
            throw new Error(); //TODO
        }
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }

    @Override
    public IType getType() {
        return type;
    }
}