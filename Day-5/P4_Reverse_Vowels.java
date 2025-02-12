/*
Given a string s, reverse only all the vowels in the 
string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can 
appear in both lower and upper cases, more than once.

Sample Input-1:
---------------
hello

Sample Output-1:
----------------
holle

Sample Input-2:
----------------
Keshavmemorial

Sample Output-2:
----------------
Kashivmomerael

 */
import java.util.*;
public class P4_Reverse_Vowels {

    private static boolean isVowel(char ch){
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U');
    }

    private static String reverseVowels(String s,int n){
        int i = 0 , j = n - 1;
        StringBuilder sb = new StringBuilder(s);
        while(i <= j){
            if(isVowel(sb.charAt(i)) && isVowel(sb.charAt(j))) {
                char temp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, temp);

                i++;
                j--;
            }
            else if(isVowel(sb.charAt(i))){
                j--;
            }else{
                i++;
            }


        }

        return sb.toString();
                

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String s = sc.next();


        
        System.out.print(reverseVowels(s,s.length()));
        
        sc.close();
    }
}
