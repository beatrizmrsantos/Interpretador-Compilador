package ast;
import compiler.CodeBlock;
import environment.*;
import types.IType;
import utils.values.IValue;

public interface ASTNode {

    IValue eval(EnvironmentValue e);

    IType typecheck(EnvironmentType e);

    void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t);

}

