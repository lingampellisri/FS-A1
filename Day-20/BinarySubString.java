/*
Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

Sample Input-1:
---------------
00110110
2

Sample Output-1: 
----------------
true

Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.

Sample Input-2:
---------------
0110
1

Sample Output-2: 
----------------
true

Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring. 

Sample Input-3:
---------------
0110
2

Sample Output-3: 
----------------
false

Explanation: The binary code "00" is of length 2 and does not exist in the array.
 

Constraints:
------------
1 <= s.length <= 5 * 10^5
s[i] is either '0' or '1'.
1 <= k <= 20

 */
import java.util.*;
public class BinarySubString {
    private static List<String> res ; // it stores all binary codes of length k 

    // Approach-i) Naive apporach using backtracking 
    // T.C:- O(2^n + n) , S.C:- O(H) (recursion stack space)
    private static final void generateAllBinaryCodesOfLenK(int idx,StringBuilder sb,int k){
        if(sb.length() == k){
            res.add(sb.toString());
            return;
        }
        
            
        generateAllBinaryCodesOfLenK(idx+1, sb.append('0'), k);
        sb.setLength(sb.length()-1);  // remove the last added character
        generateAllBinaryCodesOfLenK(idx+1, sb.append('1'), k);
        sb.setLength(sb.length()-1); // while going back remove the one that you have added 

        
    }

    private static final boolean isPossible(String str){
        // go to each and every item of the binary code
        for(String binaryCode : res){
            int idx = str.indexOf(binaryCode);
            if(idx == -1) return false;
        }

        return true;

    }

    // Approach-ii) Get all k sized sub strings and put them into a hashset , if size if 2^k at any time return true 
    // T.C:- O(n*k) , S.C:- (2^k)
    private static final boolean isPossible(String str,int k,int n){
        // this is a overloaded method
        HashSet<String> seen = new HashSet<>();
        int required_size = 1 << k; // 2^k
        for(int i = 0 ; i + k <= n; i++){
            String sub = str.substring(i, i+k);
            seen.add(sub);

            if(seen.size() == required_size) return true; // (if it's size if 2^k early stopping by returning true)
        }

        return false;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        res = new ArrayList<>();
        String binaryStr = sc.next();
        int k = sc.nextInt();
        
        // uncomment this for backtracking approach
        // System.out.println(solve(0,k,new StringBuilder(),binaryStr,binaryStr.length()));
        // generateAllBinaryCodesOfLenK(0,new StringBuilder(),k);

        
        System.out.println(isPossible(binaryStr,k,binaryStr.length()));
        sc.close();
    }
}
