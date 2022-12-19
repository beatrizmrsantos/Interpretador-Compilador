package parser;

import ast.*;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import utils.values.IValue;
import utils.values.VNull;

import java.io.*;

public class ICLInterpreter {

    public static void main(String args[]) throws IOException {

        Parser parser = new Parser(System.in);
        ASTNode exp;

        EnvironmentValue e = new EnvironmentValue(null);

        while (true) {
            try {
                System.out.print("> ");
                exp = parser.Start();
                exp.eval(e);
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
