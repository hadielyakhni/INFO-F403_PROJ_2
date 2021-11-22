import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Helper class to run the LexicalAnalyzer lexer on a given file
 */
public class ScannerManager {

    private final LexicalAnalyzer lexer;
    private final String fileName;

    public ScannerManager(String fileName) throws Exception {
        this.fileName = fileName;
        this.lexer = initializeLexer();
    }

    /**
     * Initialize the LexicalAnalyzer by creating the needed Reader from a given file name.
     */
    private LexicalAnalyzer initializeLexer() throws Exception {
        if (fileName.length() == 0) {
            throw new Exception("Usage : java Main <inputfile>");
        }

        FileInputStream stream = new FileInputStream(fileName);
        Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        return new LexicalAnalyzer(reader);
    }

    public Symbol nextToken() {
        try {
            return this.lexer.nextToken();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
