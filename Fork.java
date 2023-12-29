import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final int id;
    private boolean inUse;
    private final Lock lock;

    public Fork(int id) {
        this.id = id;
        this.inUse = false;
        this.lock = new ReentrantLock();
    }

    public synchronized void pickUp() throws InterruptedException {
        lock.lock();
        try {
            while (inUse) {
                wait();
            }
            inUse = true;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void putDown() {
        lock.lock();
        try {
            inUse = false;
            notifyAll();
        } finally {
            lock.unlock();
        }
    }
}

