package philosophers;

import java.util.ArrayList;

public class DinnerMonitorImplementation extends Dinner{

    public DinnerMonitorImplementation(int totalPhilosophers) {
        super();
        this.totalPhilosophers = totalPhilosophers;
        this.philosopherStates = new ArrayList<>(this.totalPhilosophers);
        this.philosophers = new Object[this.totalPhilosophers];

        for(int i = 0; i < this.totalPhilosophers; i++) {
            this.philosopherStates.add(PhilosopherStates.THINKING);
            this.philosophers[i] = new Object();
        }

        System.out.println(philosopherStates);
    }

    @Override
    public void getFork(int philosopher) {
        philosopherStates.set(philosopher, PhilosopherStates.HUNGRY);
        synchronized (philosophers[philosopher]) {
            if (canEat(philosopher)) {
                philosopherStates.set(philosopher, PhilosopherStates.EATING);
            } else {
                try {
                    this.philosophers[philosopher].wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                }
            }
            System.out.println(philosopherStates);
        }
    }

    @Override
    public void returnFork(int philosopher) {
        philosopherStates.set(philosopher, PhilosopherStates.THINKING);
        System.out.println(philosopherStates);

        if(getRightState(philosopher) == PhilosopherStates.HUNGRY && getRightState(getRight(philosopher)) != PhilosopherStates.EATING) {
            philosopherStates.set(getRight(philosopher), PhilosopherStates.EATING);
            synchronized (philosophers[getRight(philosopher)]) {
                philosophers[getRight(philosopher)].notify();
            }
        }
        if(getLeftState(philosopher) == PhilosopherStates.HUNGRY && getLeftState(getLeft(philosopher)) != PhilosopherStates.EATING) {
            philosopherStates.set(getLeft(philosopher), PhilosopherStates.EATING);
            synchronized (philosophers[getLeft(philosopher)]) {
                philosophers[getLeft(philosopher)].notify();
            }
        }
    }
}