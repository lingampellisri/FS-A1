# 🍦 Ice Cream Parlour Offer - Max Cups with At Most Two Flavours

## 🧩 Problem Statement

In Turkey, an ice cream parlour gives an offer to a lucky kid.  
There are **N ice cream cups** placed in a row, each with a different flavour represented by an integer.

The kid can pick a **continuous sequence** of cups, but the catch is:
> **Only up to two different flavours** are allowed in the picked sequence.

Your job is to help the kid **maximize** the number of ice cream cups they can pick!

---

## 📥 Input Format

- Line 1: An integer `N`, number of ice cream cups.
- Line 2: `N` space-separated integers, `flavours[]`, where `flavours[i]` represents the flavour of the `i-th` cup.

---

## 📤 Output Format

- Print a single integer: the **maximum number of cups** the kid can pick with at most two different flavours.

---

## 🧪 Sample Input 1
```
10
1 2 3 1 1 3 3 2 3 2
```

### Sample Output 1
```
5
```

### Explanation
The longest valid sequence is: `3 1 1 3 3` → Only flavours 1 and 3 are present.

---

## 🧪 Sample Input 2
```
10
2 1 1 3 2 1 3 0 0 3
```

### Sample Output 2
```
4
```

---

## ✅ Constraints

- 1 ≤ N ≤ 10⁵  
- 0 ≤ flavours[i] ≤ 10⁴

---

## 🚀 Solution Approach

We use a **sliding window with hashmap** approach:
- Maintain a window `[left...right]` that contains **at most two flavours**.
- Expand the window by moving `right` forward and tracking flavours using a HashMap.
- When flavours exceed 2, shrink the window from the left until the condition is satisfied again.
- Track the **maximum length** of all valid windows.

---

## 💻 Java Code
```java
import java.util.*;

public class MaxIcecream {
    private static final int getMaxIceCreams(int[] flavors, final int n) {
        HashMap<Integer, Integer> mp = new HashMap<>();

        int left = 0, right = 0;
        int cnt = 0, maxi = 0;

        while (right < n) {
            mp.put(flavors[right], mp.getOrDefault(flavors[right], 0) + 1);
            cnt++;

            if(mp.size() > 2) { // while is not required here if will work 
                mp.put(flavors[left], mp.get(flavors[left]) - 1);
                if (mp.get(flavors[left]) == 0) {
                    mp.remove(flavors[left]);
                }
                cnt--;
                left++;
            }

            maxi = Math.max(maxi, cnt);
            right++;
        }

        return maxi;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] flavors = new int[n];

        for (int i = 0; i < n; i++) flavors[i] = sc.nextInt();

        System.out.print(getMaxIceCreams(flavors, n));
        sc.close();
    }
}
```

---

## ⏱ Time & Space Complexity

### Time Complexity:
- **O(N)** — Each index is visited at most twice (once by `right`, once by `left`).

### Space Complexity:
- **O(1)** — At most two flavours stored in the map at any time (constant-sized hashmap).

---

## 🏁 Conclusion

This is a classic sliding window problem that challenges you to dynamically track a window under constraints. It’s often seen in string or array-based coding interviews!
