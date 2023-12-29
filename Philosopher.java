import javax.swing.*;
import java.util.Random;

public class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private final JTextArea output;
    private int mealsEaten;
    private Random random;

    public Philosopher(int id, Fork leftFork, Fork rightFork, JTextArea output) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.output = output;
        this.mealsEaten = 0;
        this.random = new Random();
    }

    private void eat() throws InterruptedException {
        output.append("Philosopher " + id + " is eating.\n");
        sleep(500);
        mealsEaten++;
    }

    private void think() throws InterruptedException {
        output.append("Philosopher " + id + " is thinking.\n");
        sleep(500);
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                leftFork.pickUp();
                rightFork.pickUp();

                eat();

                rightFork.putDown();
                leftFork.putDown();

                think();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

