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
        int[] freq = new int[26];

        int n = words.length;
        String firstWord = words[0];

        // first calc word1 ka freq
        for(int i = 0 ; i < firstWord.length() ; i++){
            freq[firstWord.charAt(i) - 'a']++;
        }

        // Now go to each and every word and update the freq map , but considering minimum freq of both
        for(int i = 1 ; i < n ; i++){
            String word = words[i];

            // Now calc the freq of letters in this word
            int[] temp = new int[26];
            for(char ch : word.toCharArray()){
                temp[ch - 'a']++;
            }

            for(int j = 0 ; j < 26 ; j++){
                freq[j] = Math.min(freq[j],temp[j]);
            }
        }

        int cnt = 0;
        for(int i = 0 ; i < 26 ; i++){
            if(freq[i] > 0) cnt++; // just check if this exist in all of the words 
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
