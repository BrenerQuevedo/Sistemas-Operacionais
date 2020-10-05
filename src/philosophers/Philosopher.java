package philosophers;

public class Philosopher implements Runnable {

    private int id;
    private int thinkingTime;
    private int eatingTime;
    private Dinner dinner;

    public Philosopher(int id, int thinkingTime, int eatingTime, Dinner dinner) {
        this.id = id;
        this.thinkingTime = thinkingTime;
        this.eatingTime = eatingTime;
        this.dinner = dinner;
        new Thread((Runnable)this, "Philosopher" + id).start();
    }

    @Override
    public void run() {
        while(true) {
            think();
            getFork();
            eat();
            returnFork();
        }
    }

    private void think () {
        try {
            Thread.sleep(this.thinkingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void eat () {
        try {
            Thread.sleep(this.eatingTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getFork() {
        dinner.getFork(this.id);
    }
    private void returnFork() {
        dinner.returnFork(this.id);
    }




}