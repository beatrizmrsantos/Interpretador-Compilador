package environment;

import java.util.HashMap;

public class EnvironmentInterpreter implements Environment<Integer>{

    private HashMap<String, Integer> var;

    private EnvironmentInterpreter ancestor;

    public EnvironmentInterpreter(EnvironmentInterpreter last){
        ancestor = last;
        var = new HashMap<>();
    }
    public EnvironmentInterpreter beginScope(){
        EnvironmentInterpreter novo = new EnvironmentInterpreter(this);

        return novo;
    }
    public EnvironmentInterpreter endScope(){
        return ancestor;
    }

    public void assoc(String id, Integer val){
        if(var.containsKey(id)){
            throw new Error("IDDeclaredTwice");
        } else {
            var.put(id, val);
        }
    }

    public Integer find(String id){
        int res = 0;

        if( !var.containsKey(id) ){
            if(ancestor != null){
                res = ancestor.find(id);}
        } else {
            res = var.get(id);}
        return res;
    }

}
