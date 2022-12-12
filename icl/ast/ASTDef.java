package ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import environment.EnvironmentCompiler;
import environment.EnvironmentType;
import environment.EnvironmentValue;
import types.IType;
import types.TypeBool;
import types.TypeInt;
import types.TypeRef;
import utils.Coordinates;
import utils.Pair;
import compiler.CodeBlock;
import utils.Types;
import utils.values.IValue;

public class ASTDef implements ASTNode {

    private List<Pair> list;
    private ASTNode	body;

    private Map<IType, Types> types;

    public ASTDef (List<Pair> init, ASTNode	body){
        list = init;
        this.body = body;

        types = new HashMap<>();
        types.put(new TypeInt(), null);
        types.put(new TypeBool(), null);
        //types.put(new TypeRef(), null);
    }


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

    public IType typecheck(EnvironmentType e) {
        EnvironmentType novo = e.beginScope();
        IType val;

        for(Pair p: list){
            val = p.getExp().typecheck(novo);

            if (val.equals(new Error())) {
                throw new Error("illegal arguments to let operator");
            }

            novo.assoc(p.getId(), val);
        }

        val = body.typecheck(novo);
        novo.endScope();

        return val;
    }


    @Override
    public void compile(CodeBlock c, EnvironmentCompiler e, EnvironmentType t) {
        EnvironmentCompiler novo = e.beginScope();
        EnvironmentType tnovo = t.beginScope();
        IType val;

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
            p.getExp().compile(c, novo, tnovo);
            val = p.getExp().typecheck(t);

           // types.put(val, new Types(p.getId(), ...) );

            c.emit("putfield frame_" + numberFrame + "/v" + numberVar + " I;");

            String varName = "v" + numberVar;
            novo.assoc(p.getId(), new Coordinates(novo.depth(), varName));

            numberVar++;
        }

        body.compile(c, novo, tnovo);

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
