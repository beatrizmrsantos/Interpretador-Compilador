package compiler;

import types.IType;
import types.TypeBool;
import types.TypeInt;

public class CellReference {

    private String name;
    private String typeJ;

    public CellReference(CodeBlock c, IType type) {
        this.typeJ = type.getName(c);
        this.name = type.getNameClasse(c);
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
        return typeJ;
    }

    public String className() {
        return "ref_of_" + name;
    }


}
