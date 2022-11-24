package ast;

import java.util.List;

import environment.EnvironmentCompiler;
import environment.EnvironmentInterpreter;
import environment.EnvironmentValue;
import utils.Coordinates;
import utils.Pair;
import compiler.CodeBlock;
import utils.values.IValue;
import utils.values.VInt;

public class ASTDef implements ASTNode{

    private List<Pair> list;
    private ASTNode	body;

    public ASTDef (List<Pair> init, ASTNode	body){
        list = init;
        this.body = body;
    }

//    @Override
//    public int eval(EnvironmentInterpreter e) {
//        EnvironmentInterpreter novo = e.beginScope();
//        int val;
//
//        for(Pair p: list){
//            val = p.getExp().eval(novo);
//
//            novo.assoc(p.getId(), val);
//        }
//
//        val = body.eval(novo);
//        novo.endScope();
//
//        return val;
//    }

    @Override
    public IValue eval(EnvironmentValue e) {
        EnvironmentValue novo = e.beginScope();
        IValue val;

        for(Pair p: list){
            val = p.getExp().eval(novo);

            novo.assoc(p.getId(), val);
        }

        val = body.eval(novo);
        novo.endScope();

        return val;
    }


    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e) {
        EnvironmentCompiler novo = e.beginScope();

        //ja comeca com 1 enviroment, por isso o primeiro def sera a frame 0
        int numberFrame = e.depth() - 1;

        //colocar frame com o numero de variaveis que tem no codeBlock
        c.addFrameClass(numberFrame, list.size());

        c.emit(frameInit(numberFrame));

        int numberVar = 0;
        for(Pair p: list){

            if(p.getExp() instanceof ASTNum){
                c.emit("aload_3");
            }
            p.getExp().compile(c, novo);

            c.emit("putfield frame_" + numberFrame + "/v" + numberVar + " I;");

            String varName = "v" + numberVar;
            novo.assoc(p.getId(), new Coordinates(novo.depth(), varName));

            numberVar++;
        }

        body.compile(c, novo);

        c.emit(framePopOf(numberFrame));
        novo.endScope();

    }

    private String frameInit(int numberFrame){

        String s = "new frame_" + numberFrame + "\n" +
                "\t\tdup\n" +
                "\t\tinvokespecial frame_" + numberFrame + "/<init>()V\n" +
                "\t\tdup\n" +
                "\t\taload_3\n";

        if(numberFrame == 0){
            s += "\t\tputfield frame_" + numberFrame + "/sl Ljava/lang/Object;\n";
        } else {
            s += "\t\tputfield frame_" + numberFrame + "/sl Lframe_" + (numberFrame-1) + ";\n";
        }

        s += "\t\tastore_3";

        return s;
    }

    private String framePopOf(int numberFrame){

        String s = "aload_3\n";

        if(numberFrame == 0){
            s += "\t\tgetfield frame_" + numberFrame + "/sl Ljava/lang/Object;\n";
        } else {
            s += "\t\tgetfield frame_" + numberFrame + "/sl Lframe_" + (numberFrame-1) + ";\n";
        }

        s += "\t\tastore_3";

        return s;
    }

}
