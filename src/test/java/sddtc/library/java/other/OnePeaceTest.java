package sddtc.library.java.other;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sddtc on 16/9/16.
 */
public class OnePeaceTest {
    Stack<Integer> items = new Stack<Integer>();
    static final int ITEMS_COUNT = 10;

    class Product implements Runnable {
        public void product(int i) {
            System.out.println("product: " + i);
            items.push(i);
        }

        public void run() {
            int i = 0;
            while (i++ < ITEMS_COUNT) {
                synchronized (items) {
                    product(i);
                    items.notifyAll();
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    class Consumer implements Runnable {
        AtomicInteger consumed = new AtomicInteger();

        public void consume() {
            if (!items.isEmpty()) {
                consumed.incrementAndGet();
                System.out.println("consumed: " + items.pop());
            }
        }

        public boolean isEnd() {
            return consumed.get() >= ITEMS_COUNT;
        }

        public void run() {
            while (!isEnd()) {
                synchronized (items) {
                    while (items.isEmpty() && !isEnd()) {
                        try {
                            items.wait(10);
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                        }
                    }
                    consume();
                }
            }
        }
    }
}
