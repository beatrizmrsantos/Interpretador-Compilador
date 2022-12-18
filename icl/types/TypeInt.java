package types;

import compiler.CodeBlock;

public class TypeInt implements IType {

	public TypeInt() {

	}

	public String getName(CodeBlock c) {
		return "I";
	}

	public String getNameClasse(CodeBlock c) {
		return "int";
	}
}
