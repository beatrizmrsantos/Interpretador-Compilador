import ast.ASTNode;
import environment.EnvironmentCompiler;

import java.io.*;
import compiler.CodeBlock;

public class ICLCompiler {

    public static void main(String	args[]) throws IOException {

        //le da linha de comando
        String filenameInput = args[0];
        String filename = filenameInput.split("\\.")[0];

        //vai buscar o file e le o comando a compilar
        InputStream in = new FileInputStream(filenameInput);

        //inicializa o parser, codeblock e environment
        Parser parser = new Parser(in);
        CodeBlock code = new CodeBlock();
        EnvironmentCompiler e = new EnvironmentCompiler(null);

        //String exp = readCommand(in) + "\n\n";


            try	{
                ASTNode ast	= parser.Start();
                ast.compile(code, e);
                code.dump(filename);

                e.endScope();
                in.close();

                //TO-DO Exceptions clas
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
