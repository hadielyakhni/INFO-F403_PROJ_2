/**
 * Main entry point of the program
 */
public class Main {
    /**
     * Bootstrap the scanner
     * @param argv - file name to be scanned by the scanner
     */
    public static void main(String[] argv) throws Exception {
        String grammar = IO.readFile("../more/grammar.txt");
        GrammarManager gm = new GrammarManager(grammar);
        ScannerManager sm = new ScannerManager(argv[0]);

        Parser parser = new Parser(gm, sm);
        parser.parse();
    }
}
