package types;

import compiler.CodeBlock;

public class TypeRef implements IType {

	private IType type;

	public TypeRef(IType type) {
		this.type = type;
	}

	public IType getRefType() {
		return type;
	}

	public String getName() {
		return "REF [" + type.getName() + "]";
	}

}