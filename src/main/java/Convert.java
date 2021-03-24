import com.alibaba.fastjson.JSON;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Convert implements Runnable {
    private ConcurrentLinkedQueue<Order> orders;
    public Convert(ConcurrentLinkedQueue<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
        while (orders.size() != 0 || !Thread.currentThread().isInterrupted()) {
            if (orders.size() == 0) {
                continue;
            }
            Order order = orders.poll();
            ExecutedOrder executedOrder = new ExecutedOrder(order.getOrderId(), order.getAmount(), order.getComment(), order.getFilename(), order.getLine(), order.getResult());
            String json = JSON.toJSONString(executedOrder);
            System.out.println(json);
        }
    }
}
