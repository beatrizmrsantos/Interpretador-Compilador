package compiler;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CodeBlock {
    private LinkedList<String> code;
    private LinkedList<String> classHeader;
    private Map<Integer,Integer> frames;

    public CodeBlock(){
        code = new LinkedList<>();
        classHeader = new LinkedList<>();
        frames = new HashMap<>();
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
        PrintStream file = new PrintStream("compiler" + numberframe + ".j");

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
            String s2 = ".field public v" + i + " I\n";
            classHeader.addLast(s2);
        }

        String s3 = "\n.method public <init>()V\n" +
                "aload_0\n" +
                "invokenonvirtual java/lang/Object/<init>()V\n" +
                "return\n\n" +
                ".end method\n\n";

        classHeader.addLast(s3);
    }

    private String convertListToString(LinkedList<String> list){
        String res = "";

        for (String s: list) {
            res += s;
        }

        return res;
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
