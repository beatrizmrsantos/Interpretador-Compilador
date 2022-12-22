package utils.values;

import java.util.List;

import ast.ASTNode;

import compiler.Memory;
import environment.Environment;
import environment.EnvironmentValue;

public class VFun implements IValue {
    private final List<String> argumentNames;
    private final ASTNode body;
    private Environment<IValue> callEnv;

    public VFun(List<String> argNames, ASTNode body, Environment<IValue> callEnv) {
        this.argumentNames = argNames;
        this.body = body;
        this.callEnv = callEnv;
    }

    public IValue call(List<IValue> args) {
        callEnv = callEnv.beginScope();
        for (int i = 0; i < argumentNames.size(); i++) {
            callEnv.assoc(argumentNames.get(i), args.get(i));
        }
        IValue result = body.eval((EnvironmentValue) callEnv);
        callEnv = callEnv.endScope();
        return result;
    }

    @Override
    public String toStr() {
        return null;
    }
}
