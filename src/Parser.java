import java.util.*;

public class Parser {

    private final ScannerManager scanner;
    private final GrammarManager grammar;

    private final Stack<String> stack = new Stack<>();
    private boolean error;
    private boolean advanceInput;
    private Symbol currentToken;

    private ParseTree rootParseTree;
    private ParseTree currentParseTree;
    private String parseTreeOutputFile;

    private final ArrayList<String> appliedRules = new ArrayList<>();

    public Parser(GrammarManager grammar, ScannerManager scanner, String parseTreeOutputFile) {
        this.scanner = scanner;
        this.grammar = grammar;
        this.parseTreeOutputFile = parseTreeOutputFile;

        initializeParserState();
    }

    private void initializeParserState() {
        this.stack.push("$");
        this.stack.push(grammar.getGrammarTop());

        this.advanceInput = false;
        this.error = false;

        initializeParserTreeState();
    }

    private void initializeParserTreeState() {
        Symbol rootNode = new Symbol(null, Symbol.UNDEFINED_POSITION, Symbol.UNDEFINED_POSITION, grammar.getGrammarTop());
        this.rootParseTree = new ParseTree(rootNode, null);
        this.currentParseTree = this.rootParseTree;
    }

    private Symbol getToken() {
        if (this.currentToken == null || this.advanceInput) {
            this.currentToken = this.scanner.nextToken();
        }

        return this.currentToken;
    }

    public void parse() {
        Symbol next = getToken();
        String top = this.stack.pop();

        if (top.equals("$") && next.toString().equals("$")) {
            onParsingSuccess();
            return;
        }

        this.advanceInput = false;
        if (grammar.isTerminal(top)) {
            // If top of the stack matches next input character, consume the input
            if (next.toString().equals(top)) {
                this.advanceInput = true;

                // updated the current tree, we have to update the current parent!!
                if(next.getActualValue() != null) {
                    this.currentParseTree.addChild(new ParseTree(next, this.currentParseTree));
                }
                this.currentParseTree = this.currentParseTree.advanceToNextNode();
            } else {
                this.error = false;
                System.out.println("Expected " + top + ", got " + next.toString());
                return;
            }
        } else if (grammar.getTransitions().get(top).containsKey(next.toString())) {
            // try to find a valid transition first to include the next input value
            if (grammar.getTransitions().get(top).get(next.toString()).size() == 0) {
                //no transitions were found
                error = true;
                String expected = String.join(", ", this.grammar.getFirst().get(top).stream().toArray(String[]::new));
                System.out.println("Syntax error, expected one of the following keywords: [" + expected + "]");
                return;
            }

            // should only have one possible rule/production, since LL(1) is not ambiguous
            Rule rule = grammar.getTransitions().get(top).get(next.toString()).get(0);
            appliedRules.add(rule.ruleNumber);

            // expand the rule (push the right side of the rule onto the stack)
            for (int i = rule.rhs.length - 1; i >= 0; i--) {
                this.stack.push(rule.rhs[i]);
            }

            updateParseTree(rule);
        } else {
            error = true;
            System.out.println("failure");
            return;
        }

        if (!error) {
            parse();
        }
    }

    private void onParsingSuccess() {
        System.out.println("\nParsing complete! The following rules were applied:\n");
        System.out.println(String.join(" ", appliedRules)+ "\n");

        if(parseTreeOutputFile != null) {
            IO.writeIntoFile(parseTreeOutputFile, this.rootParseTree.toLaTeX());
        }
    }

    private void updateParseTree(Rule rule) {
        if(rule.rhs.length == 0) {
            // so ε is non-terminal at the end
            // -> add it to the parse tree and update the currentParseTree

            Symbol epsilon = new Symbol(null, Symbol.UNDEFINED_POSITION, Symbol.UNDEFINED_POSITION, GrammarManager.epsilon);
            ParseTree epsilonNode = new ParseTree(epsilon, this.currentParseTree);
            this.currentParseTree.addChild(epsilonNode);

            this.currentParseTree = this.currentParseTree.advanceToNextNode();
        } else {
            // multiple rhs symbols, push them as the child of the current parent, and update the current parent;
            for(int i = 0; i < rule.rhs.length; i++) {
                Symbol rhsSymbol = new Symbol(null, Symbol.UNDEFINED_POSITION, Symbol.UNDEFINED_POSITION, rule.rhs[i]);
                ParseTree node = new ParseTree(rhsSymbol, this.currentParseTree);
                this.currentParseTree.addChild(node);
            }

            this.currentParseTree = this.currentParseTree.getChildren().get(0);
        }
    }
}
