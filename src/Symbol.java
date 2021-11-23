import java.util.HashMap;

public class Symbol {
    public static final int UNDEFINED_POSITION = -1;
    public static final Object NO_VALUE = null;

    private final LexicalUnit type;
    private Object value;
    private final int line, column;

    public String getValue() {
        return this.value.toString();
    }

    public void setValue(Object value) {
        this.value = value.toString();
    }

    private final HashMap<LexicalUnit, String> lexicalUnitToTerminal = new HashMap<LexicalUnit, String>(){{
        put(LexicalUnit.VARNAME, "[VarName]");
        put(LexicalUnit.NUMBER, "[Number]");
        put(LexicalUnit.BEG, "begin");
        put(LexicalUnit.END, "end");
        put(LexicalUnit.SEMICOLON, ";");
        put(LexicalUnit.ASSIGN, ":=");
        put(LexicalUnit.LPAREN, "(");
        put(LexicalUnit.RPAREN, ")");
        put(LexicalUnit.MINUS, "-");
        put(LexicalUnit.PLUS, "+");
        put(LexicalUnit.TIMES, "*");
        put(LexicalUnit.DIVIDE, "/");
        put(LexicalUnit.IF, "if");
        put(LexicalUnit.THEN, "then");
        put(LexicalUnit.ENDIF, "endif");
        put(LexicalUnit.ELSE, "else");
        put(LexicalUnit.NOT, "not");
        put(LexicalUnit.EQUAL, "=");
        put(LexicalUnit.GREATER, ">");
        put(LexicalUnit.SMALLER, "<");
        put(LexicalUnit.WHILE, "while");
        put(LexicalUnit.DO, "do");
        put(LexicalUnit.ENDWHILE, "endwhile");
        put(LexicalUnit.FOR, "for");
        put(LexicalUnit.FROM, "from");
        put(LexicalUnit.BY, "by");
        put(LexicalUnit.TO, "to");
        put(LexicalUnit.ENDFOR, "endfor");
        put(LexicalUnit.PRINT, "print");
        put(LexicalUnit.READ, "read");
        put(LexicalUnit.END_OF_STREAM, "$");
    }};

    private static final HashMap<String, LexicalUnit> terminalToLexicalUnit = new HashMap<String, LexicalUnit>(){{
        put("[VarName]",LexicalUnit.VARNAME);
        put("[Number]", LexicalUnit.NUMBER);
        put("begin",LexicalUnit.BEG);
        put("end",LexicalUnit.END);
        put(";",LexicalUnit.SEMICOLON);
        put(":=",LexicalUnit.ASSIGN);
        put("(",LexicalUnit.LPAREN);
        put(")",LexicalUnit.RPAREN);
        put("-",LexicalUnit.MINUS);
        put("+",LexicalUnit.PLUS);
        put("*",LexicalUnit.TIMES);
        put("/",LexicalUnit.DIVIDE);
        put("if",LexicalUnit.IF);
        put("then",LexicalUnit.THEN);
        put("endif",LexicalUnit.ENDIF);
        put("else",LexicalUnit.ELSE);
        put("not",LexicalUnit.NOT);
        put("=",LexicalUnit.EQUAL);
        put(">",LexicalUnit.GREATER);
        put("<",LexicalUnit.SMALLER);
        put("while",LexicalUnit.WHILE);
        put("do",LexicalUnit.DO);
        put("endwhile",LexicalUnit.ENDWHILE);
        put("for",LexicalUnit.FOR);
        put("from",LexicalUnit.FROM);
        put("by",LexicalUnit.BY);
        put("to",LexicalUnit.TO);
        put("endfor",LexicalUnit.ENDFOR);
        put("print",LexicalUnit.PRINT);
        put("read",LexicalUnit.READ);
        put("$",LexicalUnit.END_OF_STREAM);
    }};

    public Symbol(LexicalUnit unit, int line, int column, Object value) {
        this.type = unit;
        this.line = line + 1;
        this.column = column;
        this.value = value;
    }

    public Symbol(LexicalUnit unit, int line, int column) {
        this(unit, line, column, NO_VALUE);
    }

    public Symbol(LexicalUnit unit, int line) {
        this(unit, line, UNDEFINED_POSITION, NO_VALUE);
    }

    public Symbol(LexicalUnit unit) {
        this(unit, UNDEFINED_POSITION, UNDEFINED_POSITION, NO_VALUE);
    }

    public Symbol(LexicalUnit unit, Object value) {
        this(unit, UNDEFINED_POSITION, UNDEFINED_POSITION, value);
    }

    public static LexicalUnit getLexicalUnit(String str) {
        if(terminalToLexicalUnit.containsKey(str)) {
            return terminalToLexicalUnit.get(str);
        }

        return null;
    }

    @Override
    public int hashCode() {
        final String value = this.value != null ? this.value.toString() : "null";
        final String type = this.type != null ? this.type.toString() : "null";
        return (value + "_" + type).hashCode();
    }

    @Override
    public String toString() {
        if (this.type != null) {
            return this.lexicalUnitToTerminal.get(this.type);
        } else {
            return null;
        }
    }

    public boolean isTerminal() {
        return this.type != null;
    }

    public String toTexString() {
        if(!isTerminal()) {
            return this.value.toString();
        }

        StringBuilder str = new StringBuilder();
        str.append(this.lexicalUnitToTerminal.get(this.type));

        if(this.type == LexicalUnit.VARNAME || this.type == LexicalUnit.NUMBER) {
            str.append(": ");
            str.append(this.value.toString());
        }
        return str.toString();
    }
}