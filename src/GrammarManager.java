import java.util.*;

public class GrammarManager {

    public static String separator = "->";
    public static String epsilon = "ε";

    private final String grammar;

    private final Set<String> nonTerminals = new HashSet<>();
    private final Set<String> terminals = new HashSet<>();

    private final ArrayList<Rule> rules = new ArrayList<>();           // <- epsilon(s) filtered from rhs
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

    public String getGrammarTop() {
        return this.rules.get(0).lhs;
    }

    public Set<String> getTerminals() {
        return this.terminals;
    }

    public HashMap<String, Set<String>> getFirst() {
        return this.first;
    }

    public HashMap<String, HashMap<String, ArrayList<Rule>>> getTransitions() {
        return this.transitions;
    }

    private void initializeNonTerminals() {
        String[] lines = this.grammar.split("\n");

        for (String line : lines) {
            String lhs = line.split(separator)[0].trim();
            this.nonTerminals.add(lhs);
        }
    }

    private void initializeTerminals() {
        String[] lines = this.grammar.split("\n");
        for (String line : lines) {
            String[] rhs = line.split(separator)[1].trim().split(" ");
            for (String s : rhs) {
                if (!isNonTerminal(s) && isNotEpsilon(s)) {
                    this.terminals.add(s);
                }
            }
        }
    }

    private void initializeRules() {
        String[] lines = this.grammar.split("\n");
        int lineNumber = 0;
        for (String line : lines) {
            lineNumber++;
            String[] splitLine = line.split(separator);
            String lhs = splitLine[0].trim();
            String[] rhs = splitLine[1].trim().split(" ");
            String[] filteredRhs = Arrays.stream(rhs).filter(this::isNotEpsilon).toArray(String[]::new);

            this.rules.add(new Rule(lhs, filteredRhs, String.valueOf(lineNumber)));
        }
    }

    private void computeNullable() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : this.rules) {
                // if all rhs of this production are nullable,
                // mark it as nullable
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
                    if (this.nonTerminals.contains(rule.rhs[i])) {
                        for (String terminal : ruleFollow) {
                            if (!this.follow.get(rule.rhs[i]).contains(terminal)) {
                                this.follow.get(rule.rhs[i]).add(terminal);
                                changed = true;
                            }
                        }
                    }
                    if (this.nullables.contains(rule.rhs[i])) {
                        ruleFollow = union(ruleFollow, this.first.get(rule.rhs[i]));
                    } else {
                        ruleFollow = this.first.get(rule.rhs[i]);
                    }
                }
            }
        }
    }

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

    private boolean isNonTerminal(String str) {
        return this.nonTerminals.contains(str);
    }

    private boolean isNotEpsilon(String str) {
        return !str.equals(GrammarManager.epsilon);
    }

    private boolean isNullable(String str) {
        return this.nullables.contains(str);
    }
}


