package environment;

import utils.Coordinates;

import java.util.HashMap;

public class EnvironmentCompiler implements Environment<Coordinates>{

    private HashMap<String, Coordinates> var;

    private EnvironmentCompiler ancestor;
    private int depth;

    public EnvironmentCompiler(EnvironmentCompiler last){
        ancestor = last;
        var = new HashMap<>();

        if(ancestor == null) depth = 1;
        else depth = ancestor.depth() + 1;
    }

    public EnvironmentCompiler beginScope(){
        EnvironmentCompiler novo = new EnvironmentCompiler(this);

        return novo;
    }

    public EnvironmentCompiler endScope(){
        return ancestor;
    }

    public void assoc(String id, Coordinates c){
        var.put(id, c);
    }

    public int depth(){
        return depth;
    }

    public Coordinates find(String id){
        Coordinates res = null;

        if( !var.containsKey(id) ){
            if(ancestor != null){
                res = ancestor.find(id);}
        } else {
            res = var.get(id);
        }

        return res;
    }
}
