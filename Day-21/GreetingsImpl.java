@FunctionalInterface
interface Greetings{
    String greet(String name);
}

class GreetWithoutLambda implements Greetings{

    public String greet(String name){
        return "Happy Birthday " + name;
    }

}
public class GreetingsImpl{
    public static void main(String[] args) {
        GreetWithoutLambda glb = new GreetWithoutLambda();
        System.out.println(glb.greet("Abhinav"));

        // Now let's do it lambda 
        Greetings gl_lambda = (String name) -> "Happy Birthday " + name;
        System.out.println(gl_lambda.getClass().getName()); // GreetingsImpl$$Lambda/0x00007fada4001000
        System.out.println(gl_lambda.greet("Abhinav Chandra"));
        System.out.println("\n " + gl_lambda); //  GreetingsImpl$$Lambda/0x00007fada4001000@45ee12a7

        // Do Lambda create objects ? Yes , they create anonmuous objects internally 
    }
}