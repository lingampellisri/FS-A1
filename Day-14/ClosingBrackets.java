/*
Clavius, the person who suggested grouping of data using brackets.
He has suggested that the group of brackets should be Well-Formed.

A string is said to be Well-Formed, if:
    - Any open bracket must have corresponding two consecutive close brackets.
    - Open bracket must appear before the corresponding close brackets.

You will be given a string B, consists of square brackets only '[' , ']'.
In one operation, you can insert one square bracket at any position of B.
i.e., Given B = [[]]], in one operation you can add a open square bracket,
now B can be Well-Formed string, if you add at index 2,3,4 or 5 => [[]]]]

Your task is to return the least number of operations required,
to make B as a Well-Formed string.

Input Format:
-------------
A string, B contains only characters '[', ']'

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
[]]][

Sample Output-1:
----------------
4


Sample Input-2:
---------------
[]][[]]

Sample Output-2:
----------------
2

 */
import java.util.*;
public class ClosingBrackets {

    private static int countBrackets(String s,int n){
        int ans = 0;
        int closeBraces = 0;

        Stack<Character> st = new Stack<>();

        for(int i = 0 ; i < n ; i++){
            char ch = s.charAt(i);

            if(ch == '['){
                st.add(ch);
                
            }
            else{
                closeBraces++;
                // check if stack has some opening elements to pop
                if(!st.isEmpty() && closeBraces == 2){
                    closeBraces -= 2;
                    st.pop();
                }else if(st.isEmpty()){
                    ans++;
                    st.add('[');
                }

            }
        }

        // come out and check if there are some elements to be processed
        while( !st.isEmpty()){
            if(closeBraces == 2){
                closeBraces -= 2;
            }else{
                ans++;
            }
            st.pop();
            // closeBraces -= 2;
        }

        return  ans + st.size() * 2 + 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        
        System.out.println(countBrackets(s,s.length()));
        sc.close();
    }
}
