package parser;

import ast.*;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VNull;

import java.io.*;
import java.util.stream.Collectors;

public class ICLInterpreter {

    public static void main(String args[]) throws IOException {

//        InputStream in = new FileInputStream("Expression.icl");
//
//        String result = new BufferedReader(new InputStreamReader(in))
//                .lines().collect(Collectors.joining("\n"));
//
//        System.out.println(result);

        Parser parser = new Parser(System.in);
        ASTNode exp;
        //EnvironmentInterpreter e = new EnvironmentInterpreter(null);
        EnvironmentValue e = new EnvironmentValue(null);

        while (true) {
            try {
                System.out.print("> ");
                exp = parser.Start();

                IValue n = exp.eval(e);
                if(! (n instanceof VNull) ){
                    System.out.println( n.toStr() );
                }

                e.endScope();

            } catch (ParseException pe) {
                System.out.println ("Syntax Error: " + pe);
                parser.ReInit(System.in);

            } catch (Exception ex) {
                System.out.println ("crash" + ex);
                parser.ReInit(System.in);
            }
        }
    }
}
