package utils.values;

public class VNull implements IValue{

    Object	v;

    public VNull(Object v0) {
        v =	v0;
    }

    public Object get() {
        return null;
    }

    @Override
    public String toStr() {
        return "";
    }

}
