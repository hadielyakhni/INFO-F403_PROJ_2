/**
 * Main entry point of the program
 */
public class Main {
    /**
     * Main entry point of the program
     * @param argv - file name to be scanned by the scanner
     */
    public static void main(String[] argv) throws Exception {
        String grammar = IO.readFile("../more/grammar.txt");
        GrammarManager gm = new GrammarManager(grammar);

        String inputFileName = argv.length == 1 ? argv[0] : argv[2];
        ScannerManager sm = new ScannerManager(inputFileName);

        String parseTreeOutputFile = argv[0].equals("-wt") ? argv[1] : null;

        Parser parser = new Parser(gm, sm, parseTreeOutputFile);
        parser.parse();
    }
}
