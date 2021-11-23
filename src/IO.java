import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO {
    public static String readFile(String path)
    {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, Charset.defaultCharset());
    }

    public static void writeIntoFile(String fileName, String content){
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
