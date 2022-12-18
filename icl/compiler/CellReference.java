package compiler;

import types.IType;
import types.TypeBool;
import types.TypeInt;

public class CellReference {

    private String type;
    private String typeJ;

    public CellReference(CodeBlock c, IType type) {
        this.type = type.getName(c);
        getTypeJ(c, type);
    }

    private void getTypeJ(CodeBlock c, IType type) {
        if (type instanceof TypeInt || type instanceof TypeBool) {
            typeJ = type.getName(c);
        } else {
            typeJ = "L" + type.getName(c);
        }
    }

    public String getType() {
        return type;
    }

    public String className() {
        return "ref_of_" + type;
    }

}
