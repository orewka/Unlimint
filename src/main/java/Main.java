import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();
        for (String arg : args) {
            File file = new File("/Users/sawok/IdeaProjects/sobes/src/main/resources/" + arg);
            Parsing parsing = new Parsing(file, orders);
            parsing.run();
        }
    }
}
