package ast;

import compiler.CodeBlock;
import compiler.Memory;
import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import utils.values.IValue;
import utils.values.VBool;

public class ASTBool implements ASTNode{

    public IType type;
    private boolean v;

    public ASTBool(boolean n) {
        v = n;
        type = null;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return new VBool(v);
    }

    public IType typecheck(EnvironmentType e) {
        type = new TypeBool();
        return type;
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        if (v) {
            c.emit("iconst_1");
        } else {
            c.emit("iconst_0");
        }
    }

    @Override
    public IType getType() {
        return type;
    }
}
