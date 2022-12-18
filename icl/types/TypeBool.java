package types;

import compiler.CodeBlock;

public class TypeBool implements IType {

	public TypeBool() {

	}

	public String getName(CodeBlock c) {
		c.putAndGetReference(this);
		return "Z";
	}
}
