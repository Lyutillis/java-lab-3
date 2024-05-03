import java.util.Random;
import java.util.Scanner;

public class SemaphoreDemo {
    protected static void runSemaphore(Scanner scanner) {
        System.out.println("Введіть кількість потоків:");
        int numThreads = scanner.nextInt();

        System.out.println("Введіть інтервал часу (в секундах) t1 і t2:");
        int t1 = scanner.nextInt();
        int t2 = scanner.nextInt();

        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(4);
        Random random = new Random();

        for (int i = 0; i < numThreads; i++) {
            String threadName = "Thread-" + (i + 1);
            new Thread(() -> {
                try {
                    System.out.println(threadName + " чекає семафор.");
                    semaphore.acquire();
                    System.out.println(threadName + " захопив семафор.");
                    int sleepTime = random.nextInt(t2 - t1) + t1;
                    Thread.sleep(sleepTime * 1000);
                    System.out.println(threadName + " звільняє семафор.");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, threadName).start();
        }
    }
}
