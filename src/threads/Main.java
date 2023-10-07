package threads;

public class Main {
    public static void main(String[] args) {
        CustomThread customThread1 = new CustomThread("first");
        CustomThread customThread2 = new CustomThread("second");

        customThread1.start();
        customThread2.start();

        try {
            customThread1.join();
            customThread2.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
