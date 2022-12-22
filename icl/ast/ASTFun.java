package ast;

import compiler.CodeBlock;
import compiler.Memory;
import environment.Environment;
import environment.EnvironmentCompiler;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeFun;
import utils.values.IValue;
import utils.values.VFun;

import java.util.ArrayList;
import java.util.List;

public class ASTFun implements ASTNode{
    private ASTNode body;
    private List<ASTArg> params;
    public IType type;

    public ASTFun(ASTNode body, List<ASTArg> params) {
        this.body = body;
        this.params = params;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue env){
        List<String> argumentNames = new ArrayList<>();
        Environment<IValue> e = env.beginScope();

        for (int i = 0; i < params.size(); i++){
            ASTArg arg = params.get(i);
            argumentNames.add((arg.eval((EnvironmentValue) e)).toStr());
        }

        IValue ret = new VFun(argumentNames, body, e);
        e.endScope();
        return ret;
    }

    @Override
    public IType typecheck(EnvironmentType env) {

        Environment<IType> e = env.beginScope();
        List<IType> argTypes = new ArrayList<>();

        for (int i = 0; i < params.size(); i++){
            ASTArg arg = params.get(i);
            argTypes.add(arg.typecheck((EnvironmentType) e));
            e.assoc(arg.getName(), arg.getType());
        }

        IType t1 = body.typecheck((EnvironmentType) e);
        e.endScope();

        TypeFun type = new TypeFun(argTypes, t1);

        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }

    @Override
    public IType getType() {
        return this.type;
    }
}