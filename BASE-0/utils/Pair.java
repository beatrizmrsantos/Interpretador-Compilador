package utils;

import ast.ASTNode;

public class Pair {

    String id;
    ASTNode exp;

    public Pair(String id, ASTNode node){
        this.id = id;
        this.exp = node;
    }

    public ASTNode getExp() {
        return exp;
    }

    public String getId() {
        return id;
    }

}

