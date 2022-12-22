package ast;

import compiler.CodeBlock;
import environment.EnvironmentCompiler;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import utils.values.IValue;
import utils.values.VArg;

public class ASTArg implements ASTNode{
    private final String name;
    private final IType type;

    public ASTArg(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public IValue eval(EnvironmentValue e){
        System.out.println(type instanceof TypeBool);
        System.out.println(type instanceof TypeInt);
        return new VArg(name);
    }

    @Override
    public IType typecheck(EnvironmentType e){
        e.assoc(name, type);
        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {

    }

    public String getName() {
        return name;
    }

    public IType getType() {
        return type;
    }
}
