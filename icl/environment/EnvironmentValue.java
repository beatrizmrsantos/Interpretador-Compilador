package environment;

import utils.values.IValue;

import java.util.HashMap;

public class EnvironmentValue implements Environment<IValue>{

    private HashMap<String, IValue> var;

    private EnvironmentValue ancestor;

    public EnvironmentValue(EnvironmentValue last){
        ancestor = last;
        var = new HashMap<>();
    }

    @Override
    public EnvironmentValue beginScope() {
        EnvironmentValue novo = new EnvironmentValue(this);

        return novo;
    }

    @Override
    public EnvironmentValue endScope() {
        return ancestor;
    }

    @Override
    public void assoc(String id, IValue val){
        if(var.containsKey(id)){
            throw new Error("IDDeclaredTwice");
        } else {
            var.put(id, val);
        }
    }

    @Override
    public IValue find(String id) {

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
