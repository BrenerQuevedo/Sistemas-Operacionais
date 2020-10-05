package philosophers;

public class MainSemaphore {
    private static final int EATING_TIME = 1000;
    private static final int THINKING_TIME = 1000;


    public static void main(String[] args) {
        int size = 5;
        Dinner dinnerTable = new DinnerMonitorImplementation(size);

        for (int i = 0; i < size; i ++) {
            new Philosopher(i,EATING_TIME, THINKING_TIME, dinnerTable);
        }


    }
}
