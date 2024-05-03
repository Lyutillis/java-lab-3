import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class PortDemo {
    public static void main(String[] args) {
        Port port = new Port(20, 2); // 100 контейнерів, 2 причали
        Ship ship1 = new Ship("Ship-1", port, 50, 30); // ім'я, порт, вантажопідйомність, початкова кількість контейнерів
        Ship ship2 = new Ship("Ship-2", port, 60, 20);

        // Запуск потоків кораблів
        new Thread(ship1).start();
        new Thread(ship2).start();
    }
}

class Port {
    private final Semaphore berths; // семафор для управління причалами
    private int containerCount; // кількість контейнерів в порту
    private final ReentrantLock lock; // блокування для доступу до контейнерів

    public Port(int initialContainers, int berthCount) {
        this.containerCount = initialContainers;
        this.berths = new Semaphore(berthCount, true);
        this.lock = new ReentrantLock();
    }

    public boolean enterPort(Ship ship) {
        try {
            System.out.println(ship.getName() + " чекає на причал.");
            berths.acquire(); // корабель чекає доступу до причалу
            System.out.println(ship.getName() + " зайшов до причалу.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void leavePort(Ship ship) {
        System.out.println(ship.getName() + " покинув причал.");
        berths.release(); // звільнити причал
    }

    public void loadContainers(Ship ship, int quantity) {
        lock.lock(); // заблокувати для безпечного доступу до контейнерів
        try {
            if (containerCount >= quantity && ship.getCapacity() - ship.getContainerCount() >= quantity) {
                containerCount -= quantity; // видалити контейнери з порту
                ship.addContainers(quantity); // додати контейнери на корабель
                System.out.println(ship.getName() + " завантажив " + quantity + " контейнерів.");
            } else {
                System.out.println(ship.getName() + " не зміг завантажити " + quantity + " контейнерів.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void unloadContainers(Ship ship, int quantity) {
        lock.lock(); // заблокувати для безпечного доступу до контейнерів
        try {
            if (ship.getContainerCount() >= quantity) {
                containerCount += quantity; // додати контейнери в порт
                ship.removeContainers(quantity); // видалити контейнери з корабля
                System.out.println(ship.getName() + " розвантажив " + quantity + " контейнерів.");
            } else {
                System.out.println(ship.getName() + " не зміг розвантажити " + quantity + " контейнерів.");
            }
        } finally {
            lock.unlock();
        }
    }
}