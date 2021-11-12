import java.io.IOException;

/**
 * Main entry point of the program
 */
public class Main {
    /**
     * Bootstrap the scanner
     * @param argv - file name to be scanned by the scanner
     */
    public static void main(String[] argv) {
        try {
            ScannerManager sm = new ScannerManager(argv[0]);
            sm.scanFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
