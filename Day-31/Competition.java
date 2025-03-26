/*
 An English competition is taking place in a school. One problem is given in the following manner 
There are words formed with lower case alphabets, participants need to find the count of characters which are repeatedly appeared in all words. 
If no such characters are there, then return 0.

Note: give solution using bit manipulation only

Input Format: 
-------------
Line-1: comma separated strings

Output Format:
--------------
Line-1: an integer

Sample Input-1:
---------------
abc,abc,bc

Sampel Output-1:
----------------
2

Explanation:
------------
The characters 'b' and 'c' appear in each word, so there output is 2 .

Sample Input-2:
---------------
abcdde,baccd,eeabg

Sample Output-2:
----------------
2

Explanation:
------------
Only 'a' and 'b' occur in every word.


Constraints
Each word consists of only lower-case letters ('a'-'z').
 */
import java.util.*;
public class Competition {

    private static int getCountOfCommonChars(String[] words){
        HashSet<Character> st = new HashSet<>();
        
        for(int i = 0 ; i < words[0].length() ; i++){
            st.add(words[0].charAt(i));
        }

        int cnt = 0;
        for(int i = 1 ; i < words.length ; i++){
            String word = words[i];
            for(int j = 0 ; j < word.length() ; j++){
                char ch = word.charAt(j);

                // check if this character exist in st
                if(st.contains(ch)) cnt++;


            }
        }

        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        String[] words = sc.next().split(",");

        System.out.println(getCountOfCommonChars(words));
        
        sc.close();
    }
}
