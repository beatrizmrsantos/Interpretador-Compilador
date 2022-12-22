package utils.values;

public class VArg implements IValue {
    private final String name;

    public VArg(String name) {
        this.name = name;
    }

    @Override
    public String toStr() {
        return String.valueOf(name);
    }
}
