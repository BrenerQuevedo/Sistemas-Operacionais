package philosophers;

public class MainMonitor {
    private static final int EATING_TIME = 1000;
    private static final int THINKING_TIME = 1000;


    public static void main(String[] args) {
        int size = 5;
        Dinner dinnerTable = new DinnerSemaphoreImplementation(size);

        for (int i = 0; i < size; i ++) {
            new Philosopher(i,EATING_TIME, THINKING_TIME, dinnerTable);
        }


    }

}
