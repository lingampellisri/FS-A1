/*
Given a non-empty string s and an abbreviation abbr, 
return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Notice that only the above abbreviations are valid abbreviations of the string "word". 
Any other string is not a valid abbreviation of "word".

Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Input Format:
-------------
Line-1: A string, S contains only lowercase letters
Line-2: A string, abbr contains lowercase letters and digits

Output Format:
--------------
Line-1: A boolean value.
Sample Input-1:
---------------
internationalization
i12iz4n

Sample Output-1:
---------------
true

Sample Input-2:
---------------
apple
a2e

Sample Output-2:
---------------
false

Time Complexity: O(n) where n=max(len(word),len(abbr))
Auxiliary Space:  O(1).
 */
import java.util.*;
public class P1_Valid_Word_Abbr {
    private static boolean isValidAbbr(String word,String abbr){

        int i = 0 , j = 0;
        int m = word.length() , n = abbr.length();

        // 123
        while(i < m && j < n){
            if(Character.isDigit(abbr.charAt(j))){
                String digit = "";
                while(j < n && Character.isDigit(abbr.charAt(j))){
                    digit += (abbr.charAt(j) - '0');
                    j++;
                }

                i += Integer.valueOf(digit);

                if(i >= m && j < n) return false; // it means after increasaing the count i has crossed the boundary but j is still left
            }
            else{
                if(word.charAt(i) == abbr.charAt(j)){
                    i++;
                    j++;
                }else{
                    return false;
                }
            }

        }

        return (i == m && j == n);
    }
            
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        String abbr = sc.next();

        System.out.println(isValidAbbr(s,abbr));
        
        sc.close();
    }
}
