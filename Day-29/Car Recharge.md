# Tata Motors Electric Car - Maximum Recharge Benefit

## 🧩 Problem Statement

Tata Motors released a new **electric car** that recharges its battery based on vehicle movement at specific points along a road. These recharge points provide a certain battery percentage **only when the car travels at a specified speed**, and this speed can be maintained for only a continuous segment of **K kilometres**.

As the owner of this car, your goal is to **maximize the battery benefit** while travelling on a road of length **N** kilometres, where each kilometre has a given recharge value.

---

## 📥 Input Format

- Line 1: Two space-separated integers `N` and `K`
    - `N`: Length of the road (number of kilometres)
    - `K`: Length of the segment the car can travel at specified speed
- Line 2: `N` space-separated integers denoting the recharge value at each kilometre

---

## 📤 Output Format

- Print a single integer — the **maximum recharge benefit** obtainable by selecting any continuous `K` km segment.

---

## 🧪 Sample Input
```
7 2
2 4 8 1 2 1 8
```

### Sample Output
```
12
```

### Explanation:
- The maximum sum of any 2 consecutive values in the list is `4 + 8 = 12`.
- So, the best benefit is gained by driving at the specified speed from kilometre 2 to 3.

---

## ✅ Constraints
- 1 ≤ T ≤ 10
- 1 ≤ K ≤ N ≤ 100
- 1 ≤ ai ≤ 100

---

## 🚀 Solution Approach

The idea is to use a **sliding window** technique:
1. Compute the sum of the first `K` kilometres.
2. Slide the window one step at a time, adjusting the sum by subtracting the value that goes out and adding the value that comes in.
3. Keep track of the **maximum sum** encountered during this process.

---

## 💻 Java Code
```java
import java.util.Scanner;

public class Main {
    private static final int getMaxBenefit(final int[] points, final int n, final int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += points[i];
        }

        int left = 0;
        int right = k;
        int maxSum = sum;

        while (right < n) {
            sum += (points[right] - points[left]);
            maxSum = Math.max(maxSum, sum);
            right++;
            left++;
        }

        return maxSum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] points = new int[n];
        for (int i = 0; i < n; i++) points[i] = sc.nextInt();

        System.out.print(getMaxBenefit(points, n, k));
        sc.close();
    }
}
```

---

## ⏱ Time & Space Complexity

### Time Complexity:  
- **O(N)** — We traverse the array once with a sliding window of size K.

### Space Complexity:  
- **O(1)** — Only a few variables used. Input is stored in a fixed array.

---

## 🏁 Conclusion

This problem demonstrates the effectiveness of the **sliding window technique** in optimizing range-based operations in an array. It's commonly seen in competitive programming and interview questions.
