package utils.values;

import environment.Environment;

public class VInt implements IValue{

    int	v;

    public VInt(int v0) {
        v =	v0;
    }

    public int get() {
        return v;
    }

    @Override
    public String toStr() {
        return String.valueOf(v);
    }

}
