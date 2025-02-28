
/*
Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:
input =1432219
3
output =1219

num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.


Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

 */
import java.math.BigInteger;
import java.util.*;

public class RemoveKDigits {
    private static boolean isLesser(String s1, String s2) {
        if (s1.isEmpty())
            return false; // Avoid comparing with empty string
        if (s2.isEmpty())
            return true; // Empty `s2` means `s1` is automatically smaller
        return new BigInteger(s1).compareTo(new BigInteger(s2)) < 0;
    }

    private static List<String> res;

    private static void removeKDigits(int idx, String s, StringBuilder temp, int n, int k) {

        if (temp.length() == n - k) {
            res.add(temp.toString());
            return;
        }
        if (idx == s.length())
            return;

        // take this character
        temp.append(s.charAt(idx));
        removeKDigits(idx + 1, s, temp, n, k);

        // undo

        temp.setLength(temp.length() - 1); // this removes the last character that i have added

        removeKDigits(idx + 1, s, temp, n, k);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int k = sc.nextInt();

        res = new ArrayList<>();
        int n = s.length();
        if (n == k) {
            System.out.print(0);
        } else {

            removeKDigits(0, s, new StringBuilder(), n, k);

            // for traverse through the list

            String ans = "";

            for (String str : res) {

                if (isLesser(str, ans)) {
                    ans = str;
                }

            }

            System.out.println(ans);
        }

        sc.close();
    }
}
