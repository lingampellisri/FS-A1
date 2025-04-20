/*
Create 3 tasks and assign to three threads ,  taks are:- sorting , searching , printing
 */
public class Test{
    public static void main(String[] args) {
        Runnable task1 = ()->{
            
            System.out.println("Sorting the data...");
           
            
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            System.out.println("Searching for element");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        Runnable task3 = () -> {
            System.out.println("Prining the data");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(task1,"Sorting_Raja");
        Thread t2 = new Thread(task2,"Searching Queen");
        Thread t3 = new Thread(task3,"Printing pandu");

        t1.start();
        Thread.yield();
        t2.start();
        t3.start();
        
    }
}