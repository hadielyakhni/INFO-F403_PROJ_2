import java.util.*;

public class Parser {

    private ScannerManager scanner;

    private Set<String> nonTerminals;
    private Set<String> terminals;
    private HashMap<String, HashMap<String, ArrayList<Rule>>> transitions;
    private HashMap<String, Set<String>> first;

    private Stack<String> stack = new Stack<>();
    private boolean error;
    private int position;
    private int prevPosition;
    private Symbol currentToken;

    public Parser(GrammarManager grammarManager, ScannerManager scanner) {
        initializeScanner(scanner);
        initializeGrammarSate(grammarManager);
        initializeParserState();
    }

    private void initializeScanner(ScannerManager scanner) {
        this.scanner = scanner;
    }

    private void initializeGrammarSate(GrammarManager grammarManager){
        this.nonTerminals = grammarManager.getNonTerminals();
        this.terminals = grammarManager.getTerminals();
        this.transitions = grammarManager.getTransitions();
        this.first = grammarManager.getFirst();
    }

    private void initializeParserState() {
        this.stack.push("$");
        this.stack.push("S");

        this.position = 0;
        this.error = false;
    }

    private Symbol getToken() {
        if(this.prevPosition == this.position && this.position > 0) {
            return this.currentToken;
        }

        return this.scanner.nextToken();
    }

    public void parse() {
        String next = getToken().toString();

        //print next
        System.out.println("Next: " + next);
        if(!next.equals("$")) {
            parse();
        }

        return ;

//        String top = this.stack.pop();
//        if (top.equals("$") && next.equals("$")) {
//            System.out.println("Parsing complete! Press reset to see it again.");
//            return;
//        }
//
//        Rule rule;
//        if (this.terminals.contains(top)) {
//            // If top of the stack matches next input character, consume the input
//            if (next == top) {
//                this.prevPosition = this.position;
//                this.position++;
//            } else {
//                this.error = false;
//                System.out.println("Expected " + top + ", got " + next);
//                return;
//            }
//        } else if (this.transitions.get(top).containsKey(next)) {
//            // try to find a valid transition first to include the next input value
//            if(this.transitions.get(top).get(next).size() == 0){
//                //no transitions found
//                error = true;
//                String expected = String.join(", ",this.first.get(top).stream().toArray(String[]::new));
//                System.out.println("Syntax error, expected one of the following keywords: [" + expected + "]");
//                return;
//            }
//
//            rule = this.transitions.get(top).get(next).get(0);
//            //should only have one possible rule/production, since LL(1) is not ambiguous
//
//            // expand the rule (push the right side of the rule onto the stack)
//            for (int i = rule.rhs.length - 1; i >= 0; i--) {
//                this.stack.push(rule.rhs[i]);
//            }
//
//        } else {
//            error = true;
//            System.out.println("failure");
//            return;
//        }
//
//        if (!error) {
//            parse();
//        }
    }

}
