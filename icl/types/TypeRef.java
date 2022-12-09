package types;

import compiler.CodeBlock;

public class TypeRef implements IType {

	private String type;

	public TypeRef(String type) {
		this.type = type.getName();
	}

//	public String getRefType() {
//		return type;
//	}

	public String getName() {
		return "REF [" + type + "]";
	}

}