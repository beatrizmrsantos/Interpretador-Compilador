/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
package parser;
import ast.*;
import ast.arithmetic.*;
import ast.relational.*;
import ast.imperative.*;
import ast.logical.*;
import types.*;
import utils.values.*;
import utils.Pair;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


/** ID lister.*/
public class Parser implements ParserConstants {

  static final public ASTNode Start() throws ParseException {ASTNode t;
    t = Sep();
    jj_consume_token(TERM);
{if ("" != null) return t;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Sep() throws ParseException {ASTNode t1, t2;
    t1 = Assign();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SEMICOLON:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(SEMICOLON);
      t2 = Assign();
t1 = new ASTSep(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Assign() throws ParseException {ASTNode t1, t2;
    t1 = Conj();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASSIGN:{
        ;
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(ASSIGN);
      t2 = Conj();
t1 = new ASTAssign(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Conj() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Disj();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      op = jj_consume_token(OR);
      t2 = Disj();
t1 = new ASTOr(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Disj() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Logic();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_4;
      }
      op = jj_consume_token(AND);
      t2 = Logic();
t1 = new ASTAnd(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Logic() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Exp();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQUAL:
    case NOTEQUAL:
    case BIG:
    case BIGEQUAL:
    case SMALLEQUAL:
    case SMALL:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQUAL:{
        op = jj_consume_token(EQUAL);
        break;
        }
      case NOTEQUAL:{
        op = jj_consume_token(NOTEQUAL);
        break;
        }
      case BIG:{
        op = jj_consume_token(BIG);
        break;
        }
      case SMALL:{
        op = jj_consume_token(SMALL);
        break;
        }
      case BIGEQUAL:{
        op = jj_consume_token(BIGEQUAL);
        break;
        }
      case SMALLEQUAL:{
        op = jj_consume_token(SMALLEQUAL);
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Exp();
switch(op.kind) {
     case EQUAL:
         t1 = new ASTEqual(t1,t2);
         break;
            case NOTEQUAL:
             t1 = new ASTNotEqual(t1,t2);
             break;
                case BIG:
                t1 = new ASTBigger(t1,t2);
                break;
                    case SMALL:
                    t1 = new ASTSmaller(t1,t2);
                    break;
                        case BIGEQUAL:
                            t1 = new ASTBiggerEqual(t1,t2);
                           break;
                                case SMALLEQUAL:
                                    t1 = new ASTSmallerEqual(t1,t2);
                                    break;
       default:
     }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public IType Type() throws ParseException {IType t1; List<IType> l = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INTTYPE:{
      jj_consume_token(INTTYPE);
{if ("" != null) return new TypeInt();}
      break;
      }
    case BOOLTYPE:{
      jj_consume_token(BOOLTYPE);
{if ("" != null) return new TypeBool();}
      break;
      }
    case REFTYPE:{
      jj_consume_token(REFTYPE);
      jj_consume_token(LPAR);
      t1 = Type();
      jj_consume_token(RPAR);
{if ("" != null) return new TypeRef(t1);}
      break;
      }
    case FUNTYPE:{
      jj_consume_token(FUNTYPE);
      jj_consume_token(LPAR);
      t1 = Type();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[6] = jj_gen;
          break label_5;
        }
        jj_consume_token(COMMA);
l.add(t1);
        t1 = Type();
      }
      jj_consume_token(RPAR);
{if ("" != null) return new TypeFun(l, t1);}
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Call() throws ParseException {ASTNode e; List<ASTNode> l = null;
    e = Fact();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LPAR:{
        ;
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        break label_6;
      }
      jj_consume_token(LPAR);
      l = ExpList();
      jj_consume_token(RPAR);
e = new ASTCall(l, e);
    }
{if ("" != null) return e;}
    throw new Error("Missing return statement in function");
}

  static final public List<ASTNode> ExpList() throws ParseException {ASTNode e1, e2; List<ASTNode> l = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Num:
    case MINUS:
    case LPAR:
    case RBR:
    case BOOL:
    case NOT:
    case EXCL:
    case NEW:
    case WHILE:
    case IF:
    case PRINTLN:
    case FUN:
    case Id:{
      e1 = Sep();
l.add(e1);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          break label_7;
        }
        jj_consume_token(COMMA);
        e2 = Sep();
l.add(e2);
      }
      break;
      }
    default:
      jj_la1[10] = jj_gen;
      ;
    }
{if ("" != null) return l;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Exp() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Term();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:{
        op = jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        op = jj_consume_token(MINUS);
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Term();
if (op.kind == PLUS)
        t1 = new ASTPlus(t1,t2);
  else  t1 = new ASTSub(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Term() throws ParseException {Token op;
  ASTNode t1, t2;
    t1 = Fact();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:
      case DIV:{
        ;
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:{
        op = jj_consume_token(TIMES);
        break;
        }
      case DIV:{
        op = jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      t2 = Fact();
if (op.kind == TIMES)
        t1 = new ASTMult(t1,t2);
    else  t1 = new ASTDiv(t1,t2);
    }
{if ("" != null) return t1;}
    throw new Error("Missing return statement in function");
}

  static final public ASTNode Fact() throws ParseException {Token tk, tk2;
ASTNode n, n2, n3;
IType t1, t2;
List<Pair> listPairs = new LinkedList();
List<ASTArg> listArgs = new LinkedList();
boolean b = false;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case Num:
    case MINUS:
    case LPAR:
    case RBR:
    case BOOL:
    case NOT:
    case EXCL:
    case NEW:
    case WHILE:
    case IF:
    case PRINTLN:
    case Id:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case Num:{
        tk = jj_consume_token(Num);
n = new ASTNum(Integer.parseInt(tk.image));
        break;
        }
      case PRINTLN:{
        jj_consume_token(PRINTLN);
        n = Fact();
n = new ASTPrintln(n);
        break;
        }
      case Id:{
        tk = jj_consume_token(Id);
n = new ASTId(tk.image);
        break;
        }
      case BOOL:{
        tk = jj_consume_token(BOOL);
n = new ASTBool(Boolean.parseBoolean(tk.image));
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        n = Fact();
n = new ASTNeg(n);
        break;
        }
      case NOT:{
        jj_consume_token(NOT);
        n = Fact();
n = new ASTNot(n);
        break;
        }
      case EXCL:{
        jj_consume_token(EXCL);
        n = Fact();
n = new ASTDeref(n);
        break;
        }
      case LPAR:{
        jj_consume_token(LPAR);
        n = Sep();
        jj_consume_token(RPAR);
        break;
        }
      case RBR:{
        jj_consume_token(RBR);
listPairs = new LinkedList<>();
        label_10:
        while (true) {
          jj_consume_token(LET);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case MUT:{
            jj_consume_token(MUT);
b=true;
            break;
            }
          default:
            jj_la1[15] = jj_gen;
            ;
          }
          tk = jj_consume_token(Id);
          jj_consume_token(EQ);
          n = Assign();
          jj_consume_token(SEMICOLON);
if(b){ n=new ASTNew(n); b=false;}
                                                                                listPairs.add(new Pair(tk.image,n));
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case LET:{
            ;
            break;
            }
          default:
            jj_la1[16] = jj_gen;
            break label_10;
          }
        }
        n = Sep();
        jj_consume_token(LBR);
n = new ASTDef(listPairs,n);
        break;
        }
      case NEW:{
        jj_consume_token(NEW);
        n = Fact();
n = new ASTNew(n);
        break;
        }
      case WHILE:{
        jj_consume_token(WHILE);
        n = Sep();
        jj_consume_token(RBR);
        n2 = Sep();
        jj_consume_token(LBR);
n = new ASTWhile(n,n2);
        break;
        }
      case IF:{
        jj_consume_token(IF);
        n = Sep();
        jj_consume_token(RBR);
        n2 = Sep();
        jj_consume_token(LBR);
        jj_consume_token(RBR);
        n3 = Sep();
        jj_consume_token(LBR);
n = new ASTIf(n,n2,n3);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
{if ("" != null) return n;}
      break;
      }
    case FUN:{
      jj_consume_token(FUN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case Id:{
        tk = jj_consume_token(Id);
        jj_consume_token(COLON);
        t1 = Type();
listArgs.add(new ASTArg(tk.image, t1));
        label_11:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case COMMA:{
            ;
            break;
            }
          default:
            jj_la1[18] = jj_gen;
            break label_11;
          }
          jj_consume_token(COMMA);
          tk2 = jj_consume_token(Id);
          jj_consume_token(COLON);
          t2 = Type();
listArgs.add(new ASTArg(tk2.image, t2));
        }
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        ;
      }
      jj_consume_token(ARROW);
      n = Sep();
      jj_consume_token(END);
{if ("" != null) return new ASTFun(n, listArgs);}
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x8000,0x80000000,0x200000,0x400000,0x3f000000,0x3f000000,0x10000,0x0,0x400,0x10000,0x9014a0,0xc0,0xc0,0x300,0x300,0x0,0x0,0x9014a0,0x10000,0x0,0x9014a0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xf00,0x0,0x0,0x10f9,0x0,0x0,0x0,0x0,0x4,0x2,0x1079,0x0,0x1000,0x10f9,};
	}

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[45];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 21; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 45; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
