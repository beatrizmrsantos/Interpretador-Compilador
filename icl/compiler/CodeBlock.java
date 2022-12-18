package compiler;

import types.IType;
import types.TypeBool;
import types.TypeInt;
import types.TypeRef;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CodeBlock {
    private LinkedList<String> code;
    private LinkedList<String> classHeader;
    private Map<Integer,Integer> frames;
    private Map<IType, CellReference> references;

    private int currLabel;

    public CodeBlock(){
        code = new LinkedList<>();
        classHeader = new LinkedList<>();
        frames = new HashMap<>();
        currLabel = 1;
        references = new HashMap<>();
    }

    public int getLabel() {
        return currLabel++;
    }

    public void emit(String opcode){
        code.addLast("\t\t" + opcode + "\n");
    }

    public void addFrameClass(int frame, int numberVar){
        frames.put(frame, numberVar);
    }
    public void dump(String filename) throws IOException {
        int numberFrames = frames.size();

        dumpMainFile(filename);
        dumpFrameRef();

        if(numberFrames > 0) {
            for (int i = 0; i < numberFrames; i++) {
                dumpFrame(i);
            }
        }

    }

    //faz o ficheiro com a compilacao do codigo
    private void dumpMainFile(String filename) throws IOException {
        PrintStream fileJ = new PrintStream( filename + ".j");

        fileJ.print(beforeExp + convertListToString(code) + afterExp);

        code.clear();
    }

    //faz as classes de cada enviroment
    private void dumpFrame(int numberframe) throws FileNotFoundException {
        PrintStream file = new PrintStream("frame_" + numberframe + ".j");

        String s1 = ".class public frame_" + numberframe + "\n" +
                    ".super java/lang/Object\n";

        if(numberframe == 0){
            s1 += ".field public sl Ljava/lang/Object;\n";
        } else {
            s1 += ".field public sl Lframe_" + (numberframe - 1) + ";\n";
        }
        classHeader.addLast(s1);

        addVarAndConstructor(numberframe);

        file.print(convertListToString(classHeader));
        classHeader.clear();
    }

    private void addVarAndConstructor(int numberframe){
        int numVariables = frames.get(numberframe);

        for(int i = 0; i < numVariables; i++){
            String s2 = ".field public v" + i + " Ljava/lang/Object;\n";
            classHeader.addLast(s2);
        }

        constructor();
    }

    private void constructor(){
        String s3 = "\n.method public <init>()V\n" +
                "aload_0\n" +
                "invokenonvirtual java/lang/Object/<init>()V\n" +
                "return\n\n" +
                ".end method\n\n";

        classHeader.addLast(s3);

    }

    //criacao dos ficheiros de cada referencia existente (int, bool ou cell)
    private void dumpFrameRef() throws FileNotFoundException {

        for (Map.Entry<IType, CellReference> entry : references.entrySet()) {
            CellReference reference = entry.getValue();
            String className = reference.className();

            //criacao do ficheiro
            String filename = className + ".j";
            PrintStream file = new PrintStream(filename);

            String s1 = ".class public " + className + "\n" +
                    ".super java/lang/Object\n" + ".field public value " + reference.getType() + ";\n";
            classHeader.addLast(s1);

            constructor();

            file.print(convertListToString(classHeader));
            classHeader.clear();
        }

    }

    private String convertListToString(LinkedList<String> list){
        String res = "";

        for (String s: list) {
            res += s;
        }

        return res;
    }

    public CellReference putAndGetReference(IType type){

        if(type instanceof TypeRef) {
            CellReference ref = references.get(((TypeRef) type).getRefType());
            if (ref == null) {
                ref = new CellReference(this, ((TypeRef) type).getRefType());
                references.put(((TypeRef) type).getRefType(), ref);
            }
            return ref;
        }

        CellReference ref = references.get(type);
        if (ref == null) {
            if (type instanceof TypeInt) {
                ref = new CellReference(this, new TypeInt());
                references.put(new TypeInt(), ref);
            }

            if (type instanceof TypeBool) {
                ref = new CellReference(this, new TypeBool());
                references.put(new TypeBool(), ref);
            }
        }

        return ref;

    }

    private String beforeExp = ".class public Header\n" +
            ".super java/lang/Object\n\n;\n" +
            "; standard initializer\n" +
            ".method public <init>()V\n" +
            "   aload_0\n" +
            "   invokenonvirtual java/lang/Object/<init>()V\n" +
            "   return\n" +
            ".end method\n\n" +
            ".method public static main([Ljava/lang/String;)V\n\n" +
            "       ; set limits used by this method\n" +
            "       .limit locals  1 \n" +
            "       .limit stack 256\n\n" +
            "       ; setup local variables:\n\n" +
            "       ;    1 - the PrintStream object held in java.lang.System.out\n\n" +
            "       getstatic java/lang/System/out Ljava/io/PrintStream;\n\n" +
            "       ; place bytecodes here\n\n" +
            "       ; START   \n\n";

    private String afterExp = "\n\t\t; END \n\n\n" +
            "       ; convert to String;\n" +
            "       invokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n\n" +
            "       ; call println \n" +
            "       invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n\n" +
            "       return\n\n" +
            ".end method\n";

}
