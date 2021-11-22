public class Rule {

    //TODO: add a field for rule number

    public String lhs;
    public String[] rhs;
    public String ruleNumber;

    public Rule(String lhs, String[] rhs, String ruleNumber) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.ruleNumber = ruleNumber;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", lhs, GrammarManager.leftRightSeparator, String.join(GrammarManager.rhsSeparator, rhs));
    }
}
