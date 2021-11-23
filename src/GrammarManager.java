import java.util.*;

/**
 * This class is responsible for computing a given grammar tables, specifically,
 * the parsing table (action table), by computing the sets of first and follow.
 *
 * Also provide some helper methods that are used by the parser instance.
 */
public class GrammarManager {

    public static String leftRightSeparator = "->";
    public static String rulesSeparator = "\n";
    public static String rhsSeparator = " ";
    public static String epsilon = "ε";

    private final String grammar;

    private final Set<String> nonTerminals = new HashSet<>();
    private final Set<String> terminals = new HashSet<>();
    private final ArrayList<Rule> rules = new ArrayList<>();     // <- epsilon(s) filtered from rhs
    private final Set<String> nullables = new HashSet<>();

    private final HashMap<String, Set<String>> first = new HashMap<>();
    private final HashMap<String, Set<String>> follow = new HashMap<>();
    private final HashMap<String, HashMap<String, ArrayList<Rule>>> transitions = new HashMap<>();

    public GrammarManager(String grammar) {
        this.grammar = grammar;

        initializeNonTerminals();
        initializeTerminals();
        initializeRules();

        computeNullable();
        computeFirst();
        computeFollow();
        computeTransitions();
    }

    /**
     * Returns the first Production left-hand side (it must be a non-terminal).
     * The Parser stack will be initialized with this non-terminal.
     */
    public String getGrammarTop() {
        return this.rules.get(0).lhs;
    }

    public HashMap<String, Set<String>> getFirst() {
        return this.first;
    }

    public HashMap<String, HashMap<String, ArrayList<Rule>>> getTransitions() {
        return this.transitions;
    }

    /**
     * Find non-terminal symbols from the grammar rules
     */
    private void initializeNonTerminals() {
        String[] lines = this.grammar.split(rulesSeparator);

        for (String line : lines) {
            String lhs = line.split(leftRightSeparator)[0].trim();
            this.nonTerminals.add(lhs);
        }
    }

    /**
     * Find the terminals of a grammar by searching for symbols that are not non-terminals.
     * "ε" symbol is not counted though.
     */
    private void initializeTerminals() {
        String[] lines = this.grammar.split(rulesSeparator);
        for (String line : lines) {
            String[] rhs = line.split(leftRightSeparator)[1].trim().split(rhsSeparator);
            for (String s : rhs) {
                if (!isNonTerminal(s) && notEpsilon(s)) {
                    this.terminals.add(s);
                }
            }
        }
    }

    /**
     * Save the grammar rules as an array of Rule
     */
    private void initializeRules() {
        String[] lines = this.grammar.split(rulesSeparator);
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            String[] splitLine = line.split(leftRightSeparator);
            String lhs = splitLine[0].trim();
            String[] rhs = splitLine[1].trim().split(rhsSeparator);
            String[] filteredRhs = Arrays.stream(rhs).filter(this::notEpsilon).toArray(String[]::new);

            this.rules.add(new Rule(lhs, filteredRhs, String.valueOf(lineNumber)));
        }
    }

    /**
     * Find the nullable productions. These are the productions that produce only "ε",
     * or the production that all their rhs rules only produce "ε".
     */
    private void computeNullable() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : this.rules) {
                // if all rhs of this production are nullable, mark it as nullable.
                // if rhs == [], this will also mark lhs as nullable.
                if (Arrays.stream(rule.rhs).allMatch(this::isNullable)) {
                    // will be true if rhs.length = 0, and here we start!!
                    if (!isNullable(rule.lhs)) {
                        this.nullables.add(rule.lhs);
                        changed = true;
                    }
                }
            }
        }
    }

    /**
     * Given a rule of the form lhs -> rhs, where rhs is a string array of terminals and non-terminals,
     * find the first of the rhs array.
     * @param rule
     * @return
     */
    private Set<String> getFirstOfRhs(Rule rule) {
        String[] rhs = rule.rhs;
        int end = -1;
        int itr = 0;
        for (String sym : rhs) {
            itr++;
            if (!isNullable(sym)) {
                end = itr;
                break;
            }
        }
        if (end == -1) {
            end = rhs.length;
        }

        String[] rhsPortion = new String[end];
        System.arraycopy(rhs, 0, rhsPortion, 0, end);

        return Arrays
                .stream(rhsPortion)
                .map(first::get)
                .reduce(new HashSet<>(), (Set<String> set1, Set<String> set2) -> {
                    Set<String> result = new HashSet<>(set1);
                    result.addAll(set2);
                    return result;
                });
    }

    /**
     * Compute first set
     */
    private void computeFirst() {
        for (String terminal : this.terminals) {
            Set<String> terminalFirst = new HashSet<>();
            terminalFirst.add(terminal);
            this.first.put(terminal, terminalFirst);
        }

        for (String nonTerminal : this.nonTerminals) {
            this.first.put(nonTerminal, new HashSet<>());
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : this.rules) {
                Set<String> newFirst = getFirstOfRhs(rule);
                for (String s : newFirst) {
                    if (!this.first.get(rule.lhs).contains(s)) {
                        this.first.get(rule.lhs).add(s);
                        changed = true;
                    }
                }
            }
        }
    }

    /**
     * Compute follow set
     */
    private void computeFollow() {
        for (String nonTerminal : this.nonTerminals) {
            this.follow.put(nonTerminal, new HashSet<>());
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : this.rules) {
                Set<String> ruleFollow = this.follow.get(rule.lhs);
                for (int i = rule.rhs.length - 1; i >= 0; i--) {
                    String rhsSymbol = rule.rhs[i];
                    if (isNonTerminal(rhsSymbol)) {
                        for (String terminal : ruleFollow) {
                            if (!this.follow.get(rhsSymbol).contains(terminal)) {
                                this.follow.get(rhsSymbol).add(terminal);
                                changed = true;
                            }
                        }
                    }
                    if (isNullable(rhsSymbol)) {
                        // update the follow set to also includes the first oh this nullable non-terminal
                        // (only non-terminal can be nullable)
                        ruleFollow = union(ruleFollow, this.first.get(rhsSymbol));
                    } else {
                        // rhs is a symbol
                        // -> set the follow set as the first of this symbol (the symbol itself)
                        ruleFollow = this.first.get(rhsSymbol);
                    }
                }
            }
        }
    }

    /**
     * Compute transition set, a.k.a. the action table
     */
    private void computeTransitions() {
        for (String nonTerminal : this.nonTerminals) {
            this.transitions.put(nonTerminal, new HashMap<>());
            for (String terminal : this.terminals) {
                this.transitions.get(nonTerminal).put(terminal, new ArrayList<>());
            }
        }

        for (Rule rule : this.rules) {
            for (String a : getFirstOfRhs(rule)) {
                this.transitions.get(rule.lhs).get(a).add(rule);

                if(this.transitions.get(rule.lhs).get(a).size() > 1) {
                    System.out.println("Ambiguous Grammar: more than one transition from " + rule.lhs + " to " + a);
                    System.exit(1);
                }
            }

            if (Arrays.stream(rule.rhs).allMatch(this::isNullable)) {
                for (String a : this.follow.get(rule.lhs)) {
                    this.transitions.get(rule.lhs).get(a).add(rule);
                }
            }
        }
    }

    private <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    public boolean isTerminal(String str) {
        return this.terminals.contains(str);
    }

    public boolean isNonTerminal(String str) {
        return this.nonTerminals.contains(str);
    }

    public boolean notEpsilon(String str) {
        return !str.equals(epsilon);
    }

    public boolean isNullable(String str) {
        return this.nullables.contains(str);
    }
}


