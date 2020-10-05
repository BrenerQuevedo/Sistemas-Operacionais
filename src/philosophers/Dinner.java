package philosophers;

import java.util.ArrayList;

public abstract class Dinner {

    protected ArrayList<PhilosopherStates> philosopherStates;
    protected Object[] philosophers;
    protected int totalPhilosophers;

    public void getFork(int philosopherId) {};

    public void returnFork(int philosopherId) {};

    protected boolean canEat (int philosopherId) {
        return (getRightState(philosopherId) != PhilosopherStates.EATING &&
                getLeftState(philosopherId) != PhilosopherStates.EATING);
    }

    protected PhilosopherStates getRightState (int philosopherId) {
        return philosopherStates.get(getRight(philosopherId));
    }

    protected int getRight (int position) {
        return (position + 1) % totalPhilosophers;
    }

    protected PhilosopherStates getLeftState (int philosopherId) {
        return philosopherStates.get(getLeft((philosopherId)));
    }

    protected int getLeft (int position) {
        return (position + totalPhilosophers - 1) % totalPhilosophers;
    }

}
