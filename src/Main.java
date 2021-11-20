/**
 * Main entry point of the program
 */
public class Main {
    /**
     * Bootstrap the scanner
     * @param argv - file name to be scanned by the scanner
     */
    public static void main(String[] argv) throws Exception {
        ScannerManager sm = new ScannerManager(argv[0]);

        String grammar = IO.readFile("../more/other_format.txt");
        GrammarManager gm = new GrammarManager(grammar);

        Parser parser = new Parser(gm, sm);
        parser.parse();
    }
}
