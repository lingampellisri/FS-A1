# 🚀 Maximum Score of a Valid Path

Given two sorted arrays `nums1` and `nums2` with distinct integers, the goal is to determine the **maximum score** from all valid paths.

## 🧠 Problem Statement

A valid path is defined as:

- Start from index 0 of either `nums1` or `nums2`.
- Traverse from left to right.
- You are allowed to **switch paths** **only** at elements common to both arrays.
- A common element is **counted only once** in the final score.

The score is the **sum of unique values** in a chosen valid path.  
Return the **maximum possible score**, modulo `10⁹ + 7`.

---

## 📥 Sample Test Case 1

Input: nums1 = [2,4,5,8,10] nums2 = [4,6,8,9]

Output: 30

## 🧩 Explanation:

### Valid paths:

- From `nums1`: `[2,4,5,8,10]`, `[2,4,5,8,9]`
- From `nums2`: `[4,6,8,10]`, `[4,6,8,9]`
- Mixed paths using common values `4` and `8`:  
  e.g., `[2,4,6,8,10]` → Score = `2 + 4 + 6 + 8 + 10 = 30`

---

## 🖼️ Visual Diagram (Example 1)

🖼️ **Insert the following diagram here**

> _You can illustrate this with two rows (nums1 and nums2) and arrows showing where switching is allowed._

**📌 Image Placeholder:**  
`![Valid Path Illustration](../assets/lc_1537_gt1.png)`

---

## ✅ Optimal Approach: Two Pointer + Greedy

### Why This Works:

- Arrays are strictly increasing.
- We can move forward only.
- Switching is only possible at matching values.
- So we use **two partial sums**:
  - `sum1` → total so far in `nums1`
  - `sum2` → total so far in `nums2`

### Greedy Choice:

- At a common value, take the **maximum** of both partial sums + the common value.
- Reset both sums and continue.

---

## 💡 Time and Space Complexity

| Complexity | Value                                                           |
| ---------- | --------------------------------------------------------------- |
| Time       | `O(m + n)` where `m` and `n` are lengths of `nums1` and `nums2` |
| Space      | `O(1)` — uses constant space                                    |

---

## ✅ Java Implementation

```java
class Solution {
    private final int MOD = 1000_000_007;

    public int maxSum(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0, score = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                sum1 += nums1[i++];
            } else if (nums2[j] < nums1[i]) {
                sum2 += nums2[j++];
            } else {
                // common element
                score += Math.max(sum1, sum2) + nums1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        while (i < nums1.length) sum1 += nums1[i++];
        while (j < nums2.length) sum2 += nums2[j++];
        score += Math.max(sum1, sum2);

        return (int)(score % MOD);
    }
}
```
