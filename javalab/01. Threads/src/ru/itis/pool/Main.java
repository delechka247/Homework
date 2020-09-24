package ru.itis.pool;


public class Main {
    public static void main(String[] args) {
        /*
        Сделать проверки:

        1) Одна задача - один поток
        2) Две задачи - один поток (поочередно выполнить каждую)
        3) Три задачи - три потока (каждый поток выполняет свою задачу)
        4) Четыре задачи - три потока (три потока выполняют три задачи, четвертая задача выполняется первым свободным)
         */


        ThreadPool threadPool1 = new ThreadPool(1);
        threadPool1.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task A " + i);
            }
        });



        /*
        ThreadPool threadPool2 = new ThreadPool(1);
        threadPool2.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task A " + i);
            }
        });

        threadPool2.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task B " + i);
            }
        });
         */



        /*
        ThreadPool threadPool3 = new ThreadPool(3);
        threadPool3.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task A " + i);
            }
        });

        threadPool3.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task B " + i);
            }
        });

        threadPool3.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task C " + i);
            }
        });

         */


        /*
        ThreadPool threadPool4 = new ThreadPool(3);
        threadPool4.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task A " + i);
            }
        });

        threadPool4.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task B " + i);
            }
        });

        threadPool4.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task C " + i);
            }
        });

        threadPool4.submit(() -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println(Thread.currentThread().getName() + " Task D " + i);
            }
        });
        */
    }
}
