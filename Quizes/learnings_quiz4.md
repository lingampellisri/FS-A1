# 🧠 Java Quiz Recap & Concepts Cheat Sheet
This document summarizes all the key learnings, mistakes, and important Java concepts discussed during your quiz-4 practice. Use this to revise effectively before interviews or certification exams.

## ✅ 1. Varargs vs Arrays in Method Signatures
- `String... args` is a varargs parameter (Java treats it as a `String[]` internally).
- You **can change** `String[]` to `String...` without breaking old calls.
- But changing `String...` to `String[]` may cause compile-time errors.
```java
public void call(String... args)  // ✅ Flexible
public void call(String[] args)   // ✅ Valid but less flexible
```

## ✅ 2. Final Variables in Constructors
- Final instance variables must be initialized **once**.
- They can be initialized in each constructor instead of during declaration.
```java
final int x;
Test() { x = 10; }
Test(int y) { x = 20; }
```

## ✅ 3. Assignment (`=`) vs Comparison (`==`) in `if` Conditions
- `=` assigns a value and returns the assigned value.
- `==` compares values.
```java
if (b = true)    // ✅ Valid but misleading — assigns true
if (b == true)   // ✅ Preferred for clarity
```

## ✅ 4. Method Hiding with Static Methods
- Static methods are not overridden — they are hidden.
```java
Parent obj = new Child();
obj.show();  // Calls Parent.show() if method is static
```

## ✅ 5. Java's `Predicate<T>` Functional Interface
- Method: `boolean test(T t)`
- Used in filtering, validation, etc.
```java
Predicate<String> startsWithA = s -> s.startsWith("A");
Predicate<String> endsWithX = s -> s.endsWith("X");
Predicate<String> both = startsWithA.and(endsWithX);
System.out.println(both.test("AppleX")); // true
```

## ✅ 6. `String::isEmpty` — Method Reference
- Java uses `::` for **method references**, not scope resolution (like C++).
```java
Predicate<String> isEmpty = String::isEmpty;
System.out.println(isEmpty.test("")); // true
```

## ✅ 7. Array Indexing & Runtime Errors
- Accessing out-of-bounds index results in `ArrayIndexOutOfBoundsException`.
```java
char[][] grid = new char[3][3];
grid[3][0] = 'X'; // ❌ Runtime error
```

## ✅ 8. Collections.sort vs Arrays.sort
- Both require sorted data before `binarySearch`.
- `Collections.binarySearch(List<T>, key)` works on Lists.
- `Arrays.binarySearch(T[], key)` works on arrays.
```java
List<String> list = Arrays.asList("30", "3A", "8", "FF");
Collections.sort(list);
System.out.println(Collections.binarySearch(list, "4F")); // -3
```

## ✅ 9. Java's `final static` main Method
- Valid signatures:
```java
public static void main(String[] args)
public final static void main(String... args) // ✅ Valid
```
- `final` on main method is allowed but unnecessary.

## ✅ 10. Functional Interfaces That Are Not Equal
- `BiFunction<Double, Double, Double>` = `BinaryOperator<Double>`
- `DoubleFunction<Double>` is **not equal** (only one input)


## ✅ 11. Integer Caching in Java
- Java **caches Integer values** from `-128` to `127`.
- Comparing `Integer a = 10; Integer b = 10;` → `a == b` is `true` due to caching.
- But `Integer c = 200; Integer d = 200;` → `c == d` is `false` (not cached).
```java
System.out.println(a == b); // true
System.out.println(c == d); // false
```


## ✅ 12. Pre/Post-Increment Evaluation
- Complex expressions like `x += x++ + ++x` require understanding **order of evaluation**.
```java
int x = 5;
x += x++ + ++x;
// x becomes 17 because:
// x++ → 5 (then x = 6), ++x → 7 → 5 + 7 = 12; x += 12 → 17
```


## ✅ 13. Ternary Operator Associativity
- Ternary operators are **right-associative**.
```java
System.out.println(x > y ? "Greater" : x == y ? "Equal" : "Smaller");
```


## ✅ 14. Wrapper Class Comparison: `==` vs `.equals()`
- Use `==` to compare references, `.equals()` to compare values.
```java
Integer a = 10;
Integer b = new Integer(10);
System.out.println(a == b);       // false (different objects)
System.out.println(a.equals(b));  // true (same value)
```


## ✅ 15. 2D Array vs 1D Array
```java
String[][] matrix = new String[1][2]; // 2D array (1 row, 2 cols)
String[] arr = new String[2];         // 1D array
// Not equivalent — 2D array is an array of arrays
```


## ✅ 16. Java `::` is Method Reference, Not Scope Resolution
- C++ `::` is scope resolution.
- Java `::` is used for **method references** in lambdas.
```java
Predicate<String> isEmpty = String::isEmpty;
```


## ✅ 17. Compile Errors with `++a = 11`
- You cannot assign a value to an expression.
```java
if (++a = 11) // ❌ Compilation error
if(a = true) // this compiles since this assignment returns true
```
- Fix: use `==` for comparison.


## ✅ 18. Object[] and ArrayStoreException with 2D Arrays
- `int[][]` is assignable to `Object[]`, but modifying element types causes runtime exception.
```java
int[][] grid = new int[3][3];
Object[] obj = grid;
obj[0] = "String"; // ❌ ArrayStoreException
```
