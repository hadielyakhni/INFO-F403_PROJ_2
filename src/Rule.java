public class Rule {

    public String lhs;
    public String[] rhs;

    public Rule(String lhs, String[] rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lhs, GrammarManager.separator, String.join(" ", rhs));
    }
}
