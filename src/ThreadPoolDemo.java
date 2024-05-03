import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    protected static void runThreadPool(Scanner scanner) {
        System.out.println("Введіть кількість потоків в пулі:");
        int poolSize = scanner.nextInt();

        System.out.println("Введіть кількість потоків, що виконуються:");
        int numThreads = scanner.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(poolSize);

        for (int i = 0; i < numThreads; i++) {
            String threadName = "Thread-" + (i + 1);
            executor.submit(() -> {
                System.out.println(threadName + " виконується в пулі.");
                try {
                    Thread.sleep(2000); // затримка 2 секунди
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
