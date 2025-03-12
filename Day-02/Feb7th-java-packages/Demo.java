import pack1.*;
import pack1.subpack.Test1;
public class Demo {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        System.out.println("After t1 : ");
        t1.main(args);
        System.out.println("After calling main : ");
    }
}

// In immutable , content cannot be changed but reference can be changed
