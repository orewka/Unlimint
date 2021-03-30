import com.alibaba.fastjson.JSON;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Convert implements Runnable {
    private LinkedBlockingQueue<Order> orders;
    private AtomicBoolean stop;

    public Convert(LinkedBlockingQueue<Order> orders, AtomicBoolean stop) {
        this.orders = orders;
        this.stop = stop;
    }

    @Override
    public void run() {
        while (!stop.get() || orders.size() != 0) {
            try {
                Order order = orders.take();
                ExecutedOrder executedOrder = new ExecutedOrder(order.getOrderId(), order.getAmount(), order.getComment(), order.getFilename(), order.getLine(), order.getResult());
                String json = JSON.toJSONString(executedOrder);
                System.out.println(json);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
