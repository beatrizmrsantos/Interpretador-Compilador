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

    private boolean v;

    public ASTBool(boolean n) {
        v = n;
    }

    @Override
    public IValue eval(EnvironmentValue e) {
        return new VBool(v);
    }

    public IType typecheck(EnvironmentType e) {
        return new TypeBool();
    }

    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        if (v) {
            c.emit("goto TL");
        } else {
            c.emit("goto FL");
        }
    }
}
