import java.lang.reflect.Array;
import java.util.*;

public class GrammarManager {

    public static String separator = "->";
    public static String epsilon = "ε";

    private String grammar;

    private Set<String> nonTerminals = new HashSet<>();
    private Set<String> terminals = new HashSet<>();

    private ArrayList<Rule> rules = new ArrayList<>();           // <- epsilon(s) filtered from rhs
    private ArrayList<String> stringRules = new ArrayList<>();   // <- equivalent to rules.map(r -> r.toString())
    private Set<String> nullables = new HashSet<>();

    private HashMap<String, Set<String>> first = new HashMap<>();
    private HashMap<String, Set<String>> follow = new HashMap<>();
    private HashMap<String, HashMap<String, ArrayList<Rule>>> transitions = new HashMap<>();

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

    public Set<String> getNonTerminals() {
        return this.nonTerminals;
    }

    public Set<String> getTerminals() {
        return this.terminals;
    }

    public HashMap<String, HashMap<String, ArrayList<Rule>>> getTransitions() {
        return this.transitions;
    }

    public HashMap<String, Set<String>> getFirst() {
        return this.first;
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
                if (!isNonTerminal(s) && !isEpsilon(s)) {
                    this.terminals.add(s);
                }
            }
        }
    }

    private void initializeRules() {
        String[] lines = this.grammar.split("\n");
        for (String line : lines) {
            String[] splitLine = line.split(separator);
            String lhs = splitLine[0].trim();
            String[] rhs = splitLine[1].trim().split(" ");
            String[] filteredRhs = Arrays.stream(rhs).filter(s -> !isEpsilon(s)).toArray(String[]::new);

            this.rules.add(new Rule(lhs, filteredRhs));
            this.stringRules.add(new Rule(lhs, filteredRhs).toString());
        }
    }

    private void computeNullable() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Rule rule : this.rules) {
                // if all rhs of this production are nullable,
                // mark it as nullable
                if (Arrays.stream(rule.rhs).allMatch(s -> isNullable(s))) {
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
        for (int i = 0; i < end; i++) {
            rhsPortion[i] = rhs[i];
        }

        return Arrays
                .stream(rhsPortion)
                .map(s -> first.get(s))
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

            if (Arrays.stream(rule.rhs).allMatch(s -> isNullable(s))) {
                for (String a : this.follow.get(rule.lhs)) {
                    this.transitions.get(rule.lhs).get(a).add(rule);
                }
            }
        }
    }

    //TODO: make this dynamic
    public void printTransitionTable() {
        String leftAlignFormat = "| %-15s | %-4d | %-4s |%n";

        System.out.format("+-----------------+------+------+%n");
        System.out.format("| Column name     | ID   | NAME |%n");
        System.out.format("+-----------------+------+------|%n");
        for (int i = 0; i < 5; i++) {
            System.out.format(leftAlignFormat, "some data" + i, i * i, "hadi");
        }
        System.out.format("+-----------------+------+------+%n");

        for (String nonTerminal : this.nonTerminals) {
            System.out.println(nonTerminal);
            System.out.println("============");
            System.out.println();
            for (String terminal : this.terminals) {
                ArrayList<Rule> transitions = this.transitions.get(nonTerminal).get(terminal);
                if (transitions.size() == 0) {
//                    System.out.println(terminal + ":\t\t x");
                } else {
                    int ruleNumber = this.stringRules.indexOf(transitions.get(0).toString()) + 1;
                    System.out.println(terminal + "\t\t\t" + ruleNumber);
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
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

    private boolean isEpsilon(String str) {
        return str.equals(this.epsilon);
    }

    private boolean isNullable(String str) {
        return this.nullables.contains(str);
    }
}


