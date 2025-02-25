// this should have account name , account no and balance
class Account {
    
    // 1) First create the instance variables
    private String name;
    private static String accountNo;
    private double balance;

    // 2) Constructor
    public Account(String name,String accountNo,double balance){
        this.name = name;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getAccountNo() {
        return accountNo;
    }


    public double getBalance() {
        return balance;
    }


    // Now override equals , if name and account balance of two objects are same then return true a.equals(b);
    @Override
    public boolean equals(Object obj){
        // remember to compare string value 
        // if obj is instance of Account it creates a temporary instance temp and makes the comparisions
        if(obj instanceof Account temp){

            return (temp.getName().equals(this.getName()) && temp.getBalance() == this.getBalance());
        }
        
        return false;

        /* use equals() while comparing strings 
        
         * this is not a good way of writing 
         return ((obj instanceof Account) && ((((Account)obj).getName()).equals(this.getName())) && (((Account)obj).getBalance()) == this.getBalance());
         */

    }
   
}

public class Test{
    public static void main(String[] args) {
        // create a obj of account
        Account acc1 = new Account("Viishhnu", "123EW@", 100);
        Account acc2 = new Account("Viishhnu", "2341@", 1090);

        System.out.println("Is Equals : " + (acc1.equals(acc2)));

    }
}

    
