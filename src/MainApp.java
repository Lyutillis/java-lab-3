import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Виберіть опцію:");
            System.out.println("1. М'ютекс");
            System.out.println("2. Семафор");
            System.out.println("3. Atomic");
            System.out.println("4. Thread Pool");
            System.out.println("5. Вийти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    MutexDemo.runMutex(scanner);
                    break;
                case 2:
                    SemaphoreDemo.runSemaphore(scanner);
                    break;
                case 3:
                    AtomicDemo.runAtomic(scanner);
                    break;
                case 4:
                    ThreadPoolDemo.runThreadPool(scanner);
                    break;
                case 5:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

}
