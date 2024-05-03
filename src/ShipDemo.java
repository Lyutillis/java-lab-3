import java.util.Random;

class Ship implements Runnable {
    private final String name;
    private final Port port;
    private final int capacity; // максимальна кількість контейнерів, які корабель може містити
    private int containerCount; // поточна кількість контейнерів на кораблі

    public Ship(String name, Port port, int capacity, int initialContainers) {
        this.name = name;
        this.port = port;
        this.capacity = capacity;
        this.containerCount = initialContainers;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getContainerCount() {
        return containerCount;
    }

    public void addContainers(int quantity) {
        containerCount += quantity;
    }

    public void removeContainers(int quantity) {
        containerCount -= quantity;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            if (port.enterPort(this)) { // якщо корабель потрапив до порту
                // Випадково завантажити або розвантажити контейнери
                if (random.nextBoolean()) {
                    // Завантажити контейнери
                    port.loadContainers(this, random.nextInt(10) + 1); // від 1 до 10 контейнерів
                } else {
                    // Розвантажити контейнери
                    port.unloadContainers(this, random.nextInt(10) + 1);
                }
                port.leavePort(this); // покинути причал

                // Затримка перед наступною операцією
                try {
                    Thread.sleep(random.nextInt(5000) + 1000); // від 1 до 6 секунд
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }
}