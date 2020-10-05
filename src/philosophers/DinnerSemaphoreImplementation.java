package philosophers;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class SemaphoreImpl extends Dinner {

    private Semaphore mutex;
    private int initialValue;

    public SemaphoreImpl(int howManyPhilosophers, int initialValue) {
        super(howManyPhilosophers);

        this.mutex = new Semaphore(initialValue);
        this.philosophers = new ArrayList<Semaphore>(this.howManyPhilosophers);

        fillPhilosophers();
    }

    private void fillPhilosophers(){

        for(int i = 0; i < this.howManyPhilosophers; i++){

            this.philosopherStates.add(PhilosopherStates.THINKING);
            this.philosophers.set(i, new Semaphore(0));
        }
    }


}
