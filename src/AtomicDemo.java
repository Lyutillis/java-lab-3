import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    protected static void runAtomic(Scanner scanner) {
        AtomicInteger atomicValue = new AtomicInteger(0);

        while (true) {
            System.out.println("Atomic варіанти:");
            System.out.println("1. Збільшити");
            System.out.println("2. Зменшити");
            System.out.println("3. Встановити значення");
            System.out.println("4. Повернутись до головного меню");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atomicValue.incrementAndGet();
                    System.out.println("Значення збільшено: " + atomicValue.get());
                    break;
                case 2:
                    atomicValue.decrementAndGet();
                    System.out.println("Значення зменшено: " + atomicValue.get());
                    break;
                case 3:
                    System.out.println("Введіть нове значення:");
                    int newValue = scanner.nextInt();
                    atomicValue.set(newValue);
                    System.out.println("Значення встановлено: " + atomicValue.get());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }
}
