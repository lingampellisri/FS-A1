
---

## 📚 **Python Concepts**

### 🔥 **String Immutability and String Pool**
- In Python, **strings are immutable**, which means any modification creates a new string object.
- `s = "hello"` and `s1 = "hello"` share the same ID initially because Python uses **string interning** to save memory.
- However, after modifying `s1` using concatenation, `s1` points to a different object.

```python
s = "hello"
s1 = "hello"
print(id(s), id(s1))  # Same ID due to string interning
s1 = s1 + "world"
print(id(s), id(s1))  # Different IDs after modification
print(s, s1)  # Output: hello helloworld
```

---

## 📚 **Java Concepts**

### 🔥 **Constructors and Initialization Blocks**
- **Default Constructor:** If no constructor is defined, Java provides a default constructor.
- **Parameterized Constructor:** If a parameterized constructor is defined, a no-arg object cannot be created unless explicitly defined.

```java
class Circle {
    int radius;
    Circle() {
        radius = 1; // Default value
    }
    Circle(int i) {
        radius = i;
    }
}
```

### 🕹️ **Order of Execution in Initialization Blocks:**
- **Static Block:** Runs only once when the class is loaded.
- **Instance Initialization Block:** Runs each time an object is created, before the constructor.

```java
class Init {
    int value;
    { value = 20; } // Instance Initialization Block

    Init(int x) { System.out.println("1-arg constructor"); }
    Init() { System.out.println("no-args constructor"); }

    static { System.out.println("1st static init"); }
    { System.out.println("1st init block"); }
    { System.out.println("2nd init block"); }
    static { System.out.println("2nd static init"); }

    public static void main(String[] args) {
        new Init(); // Order: static > instance block > constructor
        new Init(14);
        System.out.println(value); // Prints 0, because instance variable is not initialized in static context
    }
}
```

---

## 🌀 **Labeled Loops in Java**
- Labeled loops allow breaking or continuing outer loops.
```java
FIRST_CHAR_LOOP:
for (int a = 1; a <= 4; a++) {
    for (char x = 'a'; x <= 'c'; x++) {
        if (a == 2 || x == 'b') {
            continue FIRST_CHAR_LOOP; // Skip to outer loop
        }
        System.out.println(a + "" + x);
    }
}
```

---

## 🕹️ **Bitwise OR (|)**
- **Bitwise OR (`|`)** checks all conditions, even if the result is known early.

---

## 🎯 **Annotations in Java**
- Annotations (`@`) provide metadata and additional information to the compiler.
- **Examples:**
    - `@Override`
    - `@FunctionalInterface`
    - `@Deprecated`

---

## 🔥 **String and String Pool in Java**
### 🔹 **String Interning**
- **String Pool:** 
    - Strings created with string literals (`String s = "Hello"`) are stored in a pool.
    - `new String("Hello")` creates a new object in the heap.

### 🔹 **Comparisons**
- `s1.equals(s2)` — Compares **values**.
- `s1 == s2` — Compares **references**.

```java
String s1 = "Hello";
String s2 = new String("Hello").intern();
System.out.println(s1 == s2); // true, because both point to the same string in the pool
```

---

## 🔥 **StringBuilder vs. StringBuffer**
- **StringBuilder:** Mutable, not thread-safe, faster in single-threaded environments.
- **StringBuffer:** Mutable, thread-safe, slower in multi-threaded environments.

---

## 🔥 **HashSet, TreeSet, LinkedHashSet**
- `HashSet`: Unordered collection.
- `TreeSet`: Sorted order.
- `LinkedHashSet`: Maintains insertion order.

---

## 📚 **Generics in Java**
- Prefer **generics** over non-generics for:
    - Type safety.
    - Compile-time error prevention.
- Compile-time errors are preferable over runtime exceptions.

---

## 🕹️ **Immutable Collections in Java**
- `List.of()` and `Collections.unmodifiableList()` create immutable lists.

---

## 🔥 **Autoboxing and Auto-unboxing**
- **Autoboxing:** Conversion of primitive to wrapper type.
- **Auto-unboxing:** Conversion of wrapper type to primitive.

---

## 📚 **Date and Time in Java**
- **LocalDate**, **LocalTime**, and **LocalDateTime** classes do **not have constructors**.
- Date and time cannot be modified by each other.

---

## 📚 **Fenwick Tree (Binary Indexed Tree - BIT)**
- Efficiently computes prefix sums in logarithmic time.
- Can handle point updates and prefix queries.

---

## 🔥 **Final Keyword in Java**
- `final` variables cannot be reassigned.
- `final` methods cannot be overridden.
- `final` classes cannot be subclassed.

---

## 🕹️ **Design Patterns - Singleton**
- **Singleton Pattern:** Restrict class instantiation to one object.
- **Steps:**
    1. Declare a `static` instance.
    2. Make the constructor `private`.
    3. Provide a `static` method to get the instance.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

---

## 🔥 **Call by Value and Reference**
- Java follows **call by value**.
- **Primitive types:** Copies are passed.
- **Objects:** References are passed.

---

## 🕹️ **Functional Interface and Lambda Expressions**
- **Functional Interface:** Contains only one abstract method.
- **Lambda Expression:** Implements a functional interface.
```java
@FunctionalInterface
interface MathOp {
    int op(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        MathOp add = (int a, int b) -> a + b;
        System.out.println(add.op(5, 10)); // Output: 15
    }
}
```

---

## 🚀 **Bonus Points Added:**
✅ Clarified string pool vs. new object instantiation.  
✅ Added missing points about Singleton pattern.  
✅ Covered generics for type safety.  
✅ Explained difference between `List.of()` and `Collections.unmodifiableList()`.  
✅ Highlighted differences between `StringBuilder` and `StringBuffer`.

---

This `.md` version is now polished and enriched with additional information that will help understand the concepts in greater depth! 🚀