import java.io.File;

public class Main {

    public static void main(String[] args) {
        for (String arg : args) {
            File file = new File(arg);
            Thread parsing = new Thread(new Parsing(file));
            parsing.start();
        }
    }
}
