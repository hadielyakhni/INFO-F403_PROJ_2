/**
 * Main entry point of the program
 */
public class Main {
    /**
     * Bootstrap the scanner
     * @param argv - file name to be scanned by the scanner
     */
    public static void main(String[] argv) throws Exception {
        // ScannerManager sm = new ScannerManager(argv[0]);
        // sm.scanFile();  <- will be called by the parser now

        String grammar = IO.readFile("../more/other_format.txt");
        GrammarManager gm = new GrammarManager(grammar);

        gm.printTransitionTable();
    }
}
