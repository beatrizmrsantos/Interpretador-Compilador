package parser;

import ast.*;
import environment.EnvironmentValue;

public class ICLInterpreter {

    public static void main(String args[]) {

        Parser parser = new Parser(System.in);
        ASTNode exp;
        //EnvironmentInterpreter e = new EnvironmentInterpreter(null);
        EnvironmentValue e = new EnvironmentValue(null);

        while (true) {
            try {
                System.out.print("> ");
                exp = parser.Start();

                System.out.println( exp.eval(e) );

                e.endScope();

            } catch (ParseException pe) {
                System.out.println ("Syntax Error");
                parser.ReInit(System.in);

            } catch (Exception ex) {
                System.out.println ("crash" + ex);
                parser.ReInit(System.in);
            }
        }
    }
}
