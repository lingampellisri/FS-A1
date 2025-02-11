/*
A string is good if there are no repeated characters.
Given a string s, return the number of good substrings of length three in s.
Note that if there are multiple occurrences of the same substring, every occurrence should be counted.
A substring is a contiguous sequence of characters in a string.

Sample Input-1:
---------------
xyzzaz

Sample Output-1:
----------------
1

Explanation: 
------------
There are 4 substrings of size 3: "xyz", "yzz", "zza", and "zaz". 
The only good substring of length 3 is "xyz".

Sample Input-2:
---------------
aababcabc

Sample Output-2:
----------------
4

Explanation: 
------------
There are 7 substrings of size 3: "aab", "aba", "bab", "abc", "bca", "cab", and "abc".
The good substrings are "abc", "bca", "cab", and "abc".
 */
import java.util.*;
public class P3_Good_Strings {
    private static int getNoOfGoodSubStrs(String s,int n){
        int cnt = 0;
        int i = 0 , j = 0 ;
        HashSet<Character> st = new HashSet<>();
        while(j < n){

            while(j - i == 3 || st.contains(s.charAt(j))){
                st.remove(s.charAt(i));
                i++;
            }
            
            
            st.add(s.charAt(j));

            if(j - i + 1 == 3) cnt++; // since we need 3 sized sub strings with all unique characters 
           

            j++;

        }

        return cnt;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.next();

        System.out.println(getNoOfGoodSubStrs(s,s.length()));
        
        sc.close();
    }
}
