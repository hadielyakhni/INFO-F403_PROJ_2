import java.util.HashMap;

public class Symbol {
    public static final int UNDEFINED_POSITION = -1;
    public static final Object NO_VALUE = null;

    private final LexicalUnit type;
    private final Object value;
    private final int line, column;

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

    public String getActualValue() {
        if(this.type == LexicalUnit.VARNAME || this.type == LexicalUnit.NUMBER) {
            return this.value.toString();
        }

        return null;
    }

    public String toTexString() {
        return this.value.toString();
    }
}