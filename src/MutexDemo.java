import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class MutexDemo {
    protected static void runMutex(Scanner scanner) {
        System.out.println("Введіть кількість потоків:");
        int numThreads = scanner.nextInt();

        System.out.println("Введіть інтервал часу (в секундах) t1 і t2:");
        int t1 = scanner.nextInt();
        int t2 = scanner.nextInt();

        ReentrantLock lock = new ReentrantLock();
        Random random = new Random();

        for (int i = 0; i < numThreads; i++) {
            String threadName = "Thread-" + (i + 1);
            new Thread(() -> {
                try {
                    lock.lock();
                    System.out.println(threadName + " захопив м'ютекс.");
                    int sleepTime = random.nextInt(t2 - t1) + t1;
                    Thread.sleep(sleepTime * 1000);
                    System.out.println(threadName + " звільняє м'ютекс.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, threadName).start();
        }
    }
}
