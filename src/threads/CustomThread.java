package threads;

public class CustomThread extends Thread{

    public CustomThread(String name) {
        super(name);
    }

    @Override
    public void run(){
        System.out.println("Start " + getName());
    }

}
