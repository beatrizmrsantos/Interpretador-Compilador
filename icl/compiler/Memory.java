package compiler;

import utils.values.IValue;
import utils.values.VCell;

public class Memory {

    public Memory() {
    }

    public VCell newCell(IValue val) {
        return new VCell(val);
    }

    public IValue get(VCell cell) {
        return cell.get();
    }

    public void set(VCell cell, IValue val) {
        cell.set(val);
    }

}
