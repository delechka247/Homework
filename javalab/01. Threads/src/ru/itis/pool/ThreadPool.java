package ru.itis.pool;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

// wait, notify, synchronized
public class ThreadPool {
    // очередь задач
    private Deque<Runnable> tasks;

    // пул потоков
    private PoolWorker threads[];

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        synchronized(tasks) {
            this.tasks.push(task);
            this.tasks.notify();
        }
    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable r;
            while(true) {
                synchronized(tasks) {
                    if(tasks.isEmpty()) {
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {
                            throw new IllegalArgumentException();
                        }
                    }
                    r = tasks.pop();
                }
                r.run();
            }
        }
    }
}
