import com.alibaba.fastjson.JSON;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Convert implements Runnable {
    ConcurrentLinkedQueue<Order> orders;

    public Convert(ConcurrentLinkedQueue<Order> orders) {
        this.orders = orders;
    }

    @Override
    public void run() {
            Order order = orders.poll();
            ExecutedOrder executedOrder = new ExecutedOrder(order.getOrderId(), order.getAmount(), order.getComment(), order.getFilename(), order.getLine(), order.getResult());
            String json = JSON.toJSONString(executedOrder);
            System.out.println(json);
    }
}
