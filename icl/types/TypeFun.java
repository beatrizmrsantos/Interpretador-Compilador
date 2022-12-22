package types;

import java.util.List;

import ast.ASTNode;
import compiler.CodeBlock;
import compiler.Memory;
import environment.Environment;
import environment.EnvironmentType;

public class TypeFun implements IType {
    private final List<IType> params;
    private final IType type;

    public TypeFun(List<IType> params, IType type) {
        this.params = params;
        this.type = type;
    }

    public IType getType() {
        return type;
    }

    @Override
    public String getName(CodeBlock c) {
        return null;
    }

    @Override
    public String getNameClasse(CodeBlock c) {
        return null;
    }

    public void checkArgs(List<ASTNode> args, EnvironmentType e) {
        if (args.size() != params.size()) {
            throw new Error();
        }

        for (int i = 0; i < params.size(); i++) {
            if (!args.get(i).typecheck(e).equals(params.get(i))) {
                throw new Error();
            }
        }
    }
}
