/*
Mr.Ram is a sales manager of Dmart, for his analysis he has to monitor 
sales of Dmart every day. He need to send report of maximum sales 
of every K consecutive days from given N number of days sales.
Write a java program to do his task easy.

Input Format:
-------------
Line-1: Two space separated integers, N and K
Line-2: N space separated integers, sales[].

Output Format:
--------------
Print maximum sales of every K consecutive days


Sample Input 1:
---------------
9 3
1 2 3 1 4 5 2 3 6

Sample output 1:
----------------
3 3 4 5 5 5 6

Explanation: 
------------
Maximum of subarray {1, 2, 3} is 3
Maximum of subarray {2, 3, 1} is 3
Maximum of subarray {3, 1, 4} is 4
Maximum of subarray {1, 4, 5} is 5
Maximum of subarray {4, 5, 2} is 5
Maximum of subarray {5, 2, 3} is 5
Maximum of subarray {2, 3, 6} is 6
*/
import java.util.*;

public class P2MaxSubArrkWindow{
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // Max Heap (value only)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        int left = 0;
        int idx = 0;
        int[] res = new int[n - k + 1];

        for (int right = 0; right < n; right++) {
            maxHeap.offer(nums[right]);

            if (right - left + 1 == k) {
                res[idx++] = maxHeap.peek();
                maxHeap.remove(nums[left]);  //  correct way to remove the left element
                left++;
            }
        }

        return res;
    }
}