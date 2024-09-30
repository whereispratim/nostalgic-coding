# 155. Min Stack

## Problem Description

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the `MinStack` class:

- `MinStack()` initializes the stack object.
- `void push(int val)` pushes the element `val` onto the stack.
- `void pop()` removes the element on the top of the stack.
- `int top()` gets the top element of the stack.
- `int getMin()` retrieves the minimum element in the stack.

You must implement a solution with `O(1)` time complexity for each function.

## Solution

<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">

class MinValUsing2Stack:
def __init__(self):
self.stack = []
self.minStack = []

    def push(self, val: int) -> None:
        self.stack.append(val)
        if not self.minStack or val <= self.minStack[-1]:
            self.minStack.append(val)

    def pop(self) -> None:
        if self.stack:
            if self.stack[-1] == self.minStack[-1]:
                self.minStack.pop()
            self.stack.pop()

    def top(self) -> int:
        if self.stack:
            return self.stack[-1]

    def getMin(self) -> int:
        if self.minStack:
            return self.minStack[-1]

minStack = MinValUsing2Stack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
print(minStack.getMin())  # return -3
minStack.pop()
print(minStack.top())     # return 0
print(minStack.getMin())  # return -2


</code></pre>
</td>
<td>
<pre><code class="java">
mport java.util.Stack;
class MinValUsing2Stack {
    private Stack<Integer> stack;
    private Stack<Integer>  minStack;

    public MinValUsing2Stack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty()) {
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        throw new IllegalStateException("Stack is empty");
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        throw new IllegalStateException("Stack is empty");
    }

    public static void main(String[] args) {
        MinValUsing2Stack minStack = new MinValUsing2Stack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}


</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses two stacks to implement the MinStack:
- `stack`: This is the main stack that stores all the elements.
- `minStack`: This auxiliary stack keeps track of the minimum elements.

### How It Works:

1. `push(val)`:
    - Always push the value onto the main stack.
    - If the minStack is empty or the new value is less than or equal to the top of minStack, push it onto minStack.

2. `pop()`:
    - If the top element of the main stack is equal to the top of minStack, pop from minStack as well.
    - Always pop from the main stack.

3. `top()`:
    - Return the top element of the main stack.

4. `getMin()`:
    - Return the top element of minStack, which is always the current minimum.

### Example Walkthrough:

1. `push(-2)`:
    - stack: [-2], minStack: [-2]
2. `push(0)`:
    - stack: [-2, 0], minStack: [-2]
3. `push(-3)`:
    - stack: [-2, 0, -3], minStack: [-2, -3]
4. `getMin()`: Returns -3
5. `pop()`: Removes -3
    - stack: [-2, 0], minStack: [-2]
6. `top()`: Returns 0
7. `getMin()`: Returns -2

## Complexity Analysis

- Time Complexity: O(1) for all operations (push, pop, top, getMin)
- Space Complexity: O(n), where n is the number of elements in the stack

## Different Approach

```java
import java.util.*;

class MinStack {
    private Stack<Integer> stack;
    private int minElement;

    public MinStack() {
        stack = new Stack<>();
        minElement = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= minElement) {
            stack.push(minElement); // push previous minElement
            minElement = x; // update minElement
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int poppedElement = stack.pop();
        if (poppedElement == minElement) {
            minElement = stack.pop(); // restore previous minElement
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            return -1; // or throw an exception
        }
        return stack.peek();
    }

    public int getMin() {
        return minElement;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Output: -3
        minStack.pop();
        System.out.println(minStack.top());    // Output: 0
        System.out.println(minStack.getMin()); // Output: -2
    }
}
```
## Example Walkthrough

1. `minStack.push(-2)`:
    - stack: [-2], minElement: -2

2. `minStack.push(0)`:
    - stack: [-2, 0], minElement: -2

3. `minStack.push(-3)`:
    - stack: [-2, 0, -2, -3], minElement: -3
    - (Note: -2 is pushed before -3 because -3 is the new minimum)

4. `minStack.getMin()`:
    - Returns -3

5. `minStack.pop()`:
    - Removes -3
    - stack: [-2, 0], minElement: -2
    - (Note: When -3 is popped, we also pop the previous minimum -2 and update minElement)

6. `minStack.top()`:
    - Returns 0

7. `minStack.getMin()`:
    - Returns -2
## LeetCode Link

[155. Min Stack](https://leetcode.com/problems/min-stack/)