import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Parsing implements Runnable {
    private File file;
    private LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    private List<String> lines = new ArrayList<>();
    private AtomicBoolean stop = new AtomicBoolean(false);
    private Thread convert = new Thread(new Convert(orders, stop));

    public Parsing(File file) {
        this.file = file;
    }

    @Override
    public void run() {
        convert.start();
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
        stop.set(true);
    }

    private void csv() {
        int i = 0;
        readFile();
        for (String s : lines) {
            String[] temp = s.split(",");
            Order order = new Order(Integer.parseInt(temp[0]), Float.parseFloat(temp[1]), temp[2], temp[3], file.getName(), ++i, "OK");
            try {
                orders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            try {
                orders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFile() {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
