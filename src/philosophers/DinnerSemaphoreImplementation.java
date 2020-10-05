package philosophers;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class DinnerSemaphoreImplementation extends Dinner {

    private Semaphore mutex;

    public DinnerSemaphoreImplementation (int totalPhilosophers) {
        super();
        this.mutex = new Semaphore(1);
        this.totalPhilosophers = totalPhilosophers;
        this.philosopherStates = new ArrayList<>(totalPhilosophers);
        this.philosophers = new Semaphore[this.totalPhilosophers];

        for(int i = 0; i < this.totalPhilosophers; i++) {
            this.philosopherStates.add(PhilosopherStates.THINKING);
            this.philosophers[i] = new Semaphore(0);
        }

        System.out.println(philosopherStates);
    }

    @Override
    public void getFork(int philosopher) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        philosopherStates.set(philosopher, PhilosopherStates.HUNGRY);
        if (canEat(philosopher)) {
            ((Semaphore) philosophers[philosopher]).release();
            philosopherStates.set(philosopher, PhilosopherStates.EATING);
        }
        mutex.release();
        try {
            ((Semaphore) philosophers[philosopher]).acquire();
            System.out.println(philosopherStates);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    @Override
    public void returnFork(int philosopher) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }

        System.out.println(philosopherStates);
        philosopherStates.set(philosopher, PhilosopherStates.THINKING);
        if (getRightState(philosopher) == PhilosopherStates.HUNGRY &&
                getRightState(getRight(philosopher)) != PhilosopherStates.EATING) {
            philosopherStates.set(getRight(philosopher),  PhilosopherStates.EATING);
            ((Semaphore) philosophers[getRight(philosopher)]).release();
        }
        if (getLeftState(philosopher) == PhilosopherStates.HUNGRY &&
                getLeftState(getLeft(philosopher)) != PhilosopherStates.EATING) {
            philosopherStates.set(getLeft(philosopher), PhilosopherStates.EATING);
            ((Semaphore) philosophers[getLeft(philosopher)]).release();
        }
        mutex.release();
    }
}