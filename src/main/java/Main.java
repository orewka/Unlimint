import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
        for (String arg : args) {
            String path = Main.class.getResource(arg).getPath();
            File file = new File(path);
            Parsing parsing = new Parsing(file);
            parsing.run();
        }
    }
}
