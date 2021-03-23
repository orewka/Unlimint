import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Parsing implements Runnable {
    private File file;
    private ConcurrentLinkedQueue<Order> orders = new ConcurrentLinkedQueue<>();
    private List<String> lines = new ArrayList<>();
    private Convert convert;

    public Parsing(File file) {
        this.file = file;
        this.convert = new Convert(orders);
    }

    @Override
    public void run() {
        switch (getFileExtension(file)) {
            case "csv" :
                csv();
                break;
            case "json" :
                json();
                break;
            default:
                System.out.println("File not support");
                break;
        }
    }

    private void csv() {
        int i = 0;
        readFile();
        for (String s : lines) {
            String[] temp = s.split(",");
            Order order = new Order(temp[0], temp[1], temp[2], temp[3], file.getName(), ++i, "OK");
            orders.add(order);
            convert.run();
        }
    }

    private void json() {
        String temp;
        int i = 0;
        readFile();
        for (String s : lines) {
            temp = s.replaceAll("[“”]", "\"");
            Order order = JSON.parseObject(temp, Order.class);
            order.setFilename(file.getName());
            order.setLine(++i);
            order.setResult("OK");
            orders.add(order);
            convert.run();
        }
    }

    private List<String> readFile() {
        try {
            BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
