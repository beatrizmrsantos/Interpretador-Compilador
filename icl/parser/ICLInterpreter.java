package parser;

import ast.*;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import utils.values.IValue;
import utils.values.VNull;

import java.io.*;
import java.util.stream.Collectors;

public class ICLInterpreter {

    public static void main(String args[]) throws IOException {

        Parser parser = new Parser(System.in);
        ASTNode exp;

        EnvironmentValue e = new EnvironmentValue(null);
        //EnvironmentType e = new EnvironmentType(null);

        while (true) {
            try {
                System.out.print("> ");
                exp = parser.Start();

                IValue n = exp.eval(e);
                if(! (n instanceof VNull) ){
                    System.out.println( n.toStr() );
                }

//                IType n = exp.typecheck(e);
//                System.out.println(n);

                e.endScope();

            } catch (ParseException pe) {
                System.out.println ("Syntax Error: " + pe);
                parser.ReInit(System.in);

            } catch (Exception ex) {
                System.out.println ("crash" + ex);
                parser.ReInit(System.in);

            }catch (Error error) {
                System.out.println(error);
                parser.ReInit(System.in);
            }
        }
    }
}
