import java.util.HashMap;

public class Symbol {
    public static final int UNDEFINED_POSITION = -1;
    public static final Object NO_VALUE = null;

    private final LexicalUnit type;
    private final Object value;
    private final int line, column;

    private HashMap<LexicalUnit, String> lexicalUnitToGrammarString = new HashMap<LexicalUnit, String>(){{
        put(LexicalUnit.VARNAME, "[VarName]");
        put(LexicalUnit.NUMBER, "[Number]");
        put(LexicalUnit.BEG, "beg");
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

    public boolean isTerminal() {
        return this.type != null;
    }

    public boolean isNonTerminal() {
        return this.type == null;
    }

    public LexicalUnit getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public int hashCode() {
        final String value = this.value != null ? this.value.toString() : "null";
        final String type = this.type != null ? this.type.toString() : "null";
        return new String(value + "_" + type).hashCode();
    }

    @Override
    public String toString() {
        if (this.type != null) {
            return this.lexicalUnitToGrammarString.get(this.type);
        } else {
            return null;
        }
    }
}