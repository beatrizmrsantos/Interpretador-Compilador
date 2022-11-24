package utils.values;

import environment.Environment;

public class VBool implements IValue{

    boolean v;

    public VBool(boolean v0) {
        v = v0;
    }

    public boolean get() {
        return v;
    }

    @Override
    public String toStr() {
        return String.valueOf(v);
    }


}
