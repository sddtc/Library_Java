package sddtc.library.java.other;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sddtc on 16/9/16.
 */
public class OnePeaceTest {
    Stack<Integer> items = new Stack<Integer>();
    static final int ITEMS_COUNT = 10;

    public static void main(String args[]) {
        OnePeaceTest pc = new OnePeaceTest();
        Thread t1 = new Thread(pc.new Product());
        Consumer consumer  = pc.new Consumer();
        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(consumer);
        Thread t4 = new Thread(consumer);
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        t2.start();
        t3.start();
        t4.start();
        try {
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Product implements Runnable {
        public void product(int i) {
            System.out.println(Thread.currentThread().getId() + " product: " + i);
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
                System.out.println(Thread.currentThread().getId() + " consumed get : " + consumed.get() + " ; " + "consumed: " + items.pop());
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
                            System.out.println(Thread.currentThread().getId() +" wait ");
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                        }
                    }
                    consume();
                }
            }
            System.out.println(Thread.currentThread().getId() +"----" + consumed.get());
        }
    }
}
