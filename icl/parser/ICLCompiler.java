package parser;


import ast.*;
import environment.EnvironmentCompiler;

import java.io.*;
import compiler.CodeBlock;
import environment.EnvironmentType;
import types.IType;

public class ICLCompiler {

    public static void main(String	args[]) throws IOException {

        //le da linha de comando
        String filenameInput = args[0];
        String[] file = filenameInput.split("\\.");
        String filename = file[0];

        if (!file[1].equals(".icl")) {
            System.out.println("Warning: Code file does not have a .icl extension.");
        }

        InputStream in = null;
        try {
            //vai buscar o file e le o comando a compilar
            in = new FileInputStream(filenameInput);
        } catch (IOException e) {
            System.out.println("File " + filename + " not found.");
        }

        //inicializa o parser, codeblock e environment
        Parser parser = new Parser(in);
        CodeBlock code = new CodeBlock();
        EnvironmentCompiler e = new EnvironmentCompiler(null);
        EnvironmentType t = new EnvironmentType(null);

            try	{
                ASTNode ast	= parser.Start();
                ast.typecheck(t);
                ast.compile(code, e);
                code.dump(filename);

                e.endScope();
                in.close();

            } catch (ParseException pe) {
                System.out.println ("Syntax Error: " + pe);
                parser.ReInit(System.in);

            } catch (Exception exception) {
                System.out.println ("crash" + exception);
                parser.ReInit(System.in);

            } catch (TokenMgrError error){
                System.out.println (error);
            }

    }

}
