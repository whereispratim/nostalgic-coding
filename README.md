# Coding Style Comparison: Java & Python

This repository is intended to observe and compare coding styles in Java and Python by solving the same problems in both languages. The examples provided here are collected from various sources across the internet, showcasing different approaches to common coding challenges.

## Structure of the Repository

- **filename.java**: Contains solutions implemented in Java.
- **filename.py**: Contains the same solutions implemented in Python.

Each directory may have subfolders or files specific to individual problem sets, such as:
- Sorting algorithms
- Data structures (Stacks, Queues, etc.)
- Graph problems (DFS, BFS, etc.)
- Dynamic programming problems

## Purpose of the Repository

The goal of this repository is to compare:

1. Code readability and syntax in both languages.
2. Efficiency of solutions in terms of time and space complexity.
3. Language-specific idioms and best practices.

By reviewing the examples, you can:
- Learn how the same logic can be translated between Java and Python.
- Understand differences in handling data structures, control flow, and error handling.
- Observe the stylistic preferences and common practices of each language.

## Example Problem: Min Stack

Both Java and Python code for implementing a Min Stack data structure is available in this repository. Here's a brief summary of the problem:

### Problem Description

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time. The stack must maintain the minimum element in O(1) complexity at all times.

### Solution

#### Java:

```java
class MinStack {
    private Stack<Integer> stack;
    private int minElement;

    public MinStack() {
        stack = new Stack<>();
        minElement = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= minElement) {
            stack.push(minElement); // Store the previous minElement
            minElement = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == minElement) {
            minElement = stack.pop(); // Restore the previous minElement
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minElement;
    }
}
```
### Python:

```python
class MinStack:
    def __init__(self):
        self.stack = []
        self.minStack = []

    def push(self, val: int) -> None:
        self.stack.append(val)
        if not self.minStack or val <= self.minStack[-1]:
            self.minStack.append(val)

    def pop(self) -> None:
        if self.stack.pop() == self.minStack[-1]:
            self.minStack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.minStack[-1]
```

## How to Use This Repository

1. Clone the repository using:
    git clone https://github.com/whereispratim/nostalgic-coding

2. Navigate to the appropriate directory (Java or Python) and explore the code examples.

3. Compile and run the Java code using:
    ````
   javac Filename.java
   java Filename
   
4. Run the Python code using:
   ```python filename.py```


## Contributing

If you would like to contribute:

1. Fork the repository.
2. Submit a pull request with a clear description of the changes or solutions you are adding.

## Acknowledgments

This repository is based on publicly available solutions from coding challenge platforms, blogs, and open-source codebases. The goal is to observe different styles of coding rather than create new solutions from scratch.