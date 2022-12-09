package ast;
import compiler.CodeBlock;
import environment.*;
import types.IType;
import utils.values.IValue;

public interface ASTNode {

    IValue eval(EnvironmentValue e);

    void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t);

    IType typecheck(EnvironmentType e);

}

