package environment;

import types.IType;
import utils.values.IValue;

import java.util.HashMap;

public class EnvironmentType implements Environment<IType>{

    private HashMap<String, IType> var;

    private EnvironmentType ancestor;

    public EnvironmentType(EnvironmentType last){
        ancestor = last;
        var = new HashMap<>();
    }

    @Override
    public EnvironmentType beginScope() {
        EnvironmentType novo = new EnvironmentType(this);

        return novo;
    }

    @Override
    public EnvironmentType endScope() {
        return ancestor;
    }

    @Override
    public void assoc(String id, IType val){
        if(var.containsKey(id)){
            throw new Error("IDDeclaredTwice");
        } else {
            var.put(id, val);
        }
    }

    @Override
    public IType find(String id) {

        if (!var.containsKey(id)) {
            if (ancestor != null) {
                return ancestor.find(id);
            }
        } else {
            return var.get(id);
        }

        return null;
    }
}
