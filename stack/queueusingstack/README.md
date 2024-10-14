## Implement Queue using Stacks

### Problem Statement

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (`push`, `pop`, `peek`, and `empty`).

Implement the `MyQueue` class:

- `void push(int x)` Pushes element x to the back of the queue.
- `int pop()` Removes the element from the front of the queue and returns it.
- `int peek()` Returns the element at the front of the queue.
- `boolean empty()` Returns true if the queue is empty, false otherwise.

Notes:

- You must use only standard operations of a stack, which means only `push to top`, `peek/pop from top`, `size`, and `is empty` operations are valid.
- Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

### Examples

#### Example 1:

```
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is:
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is
myQueue.empty(); // return false
```

### Constraints:

- 1 <= x <= 9
- At most 100 calls will be made to push, pop, peek, and empty.
- All the calls to pop and peek are valid.

### Approach

We can use two stacks to implement a queue:
1. One stack for pushing elements (let's call it `inStack`).
2. Another stack for popping and peeking elements (let's call it `outStack`).

When pushing an element, we simply push it onto `inStack`.

When popping or peeking, if `stack2` is empty, we transfer all elements from `inStack` to `outStack` (which reverses their order). Then we perform the operation on `outStack`.

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class MyQueue:
    def __init__(self):
        # Stack to handle push operations
        self.inStack = []
        # Stack to handle pop and peek operations
        self.outStack = []

    def push(self, x: int) -> None:
        """
        Push element x to the back of queue.
        """
        self.inStack.append(x)

    def pop(self) -> int:
        """
        Removes the element from in front of queue and returns that element.
        """
        self.move()
        return self.outStack.pop()

    def peek(self) -> int:
        """
        Get the front element.
        """
        self.move()
        return self.outStack[-1]

    def empty(self) -> bool:
        """
        Returns whether the queue is empty.
        """
        return not self.inStack and not self.outStack

    def move(self) -> None:
        """
        Transfer elements from inStack to outStack only when outStack is empty.
        """
        if not self.outStack:
            while self.inStack:
                self.outStack.append(self.inStack.pop())

myQueue = MyQueue()
myQueue.push(1)      # queue is [1]
myQueue.push(2)      # queue is [1, 2]
print(myQueue.peek()) # return 1
print(myQueue.pop())  # return 1, queue is [2]
print(myQueue.empty()) # return False
</code></pre>
</td>
<td>
<pre><code class="java">

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class MyQueue {
private Stack<Integer> inStack;  // Stack for push operations
private Stack<Integer> outStack; // Stack for pop/peek operations

    // Constructor
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    // Push element x to the back of the queue
    public void push(int x) {
        inStack.push(x);
    }

    // Removes the element from in front of the queue and returns that element
    public int pop() {
        peek(); //or move()
        return outStack.pop();
    }

    // Get the front element
    public int peek() {
        move();
        return outStack.peek();
    }

    // Returns whether the queue is empty
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    // Transfer elements from inStack to outStack only when outStack is empty
    private void move() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    // Main method for testing the MyQueue class
    public static void main(String[] args) {
        Queue<Integer> q =  new LinkedList<Integer>();
        q.offer(1);
        q.offer(2);
        System.out.println("General Queue"+ q); //[1, 2]

        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // Queue is [1]
        myQueue.push(2); // Queue is [1, 2]
        System.out.println(myQueue.peek()); // Output: 1
        System.out.println(myQueue.pop());  // Output: 1, Queue is now [2]
        System.out.println(myQueue.empty()); // Output: false
    }
}

</code></pre>
</td>
</tr>
</table>

### Explanation

1. `push(x)`: We simply push the element onto `inStack`.
2. `pop()`: We first call `peek()` to ensure `outStack` is not empty, then pop from `outStack`.
3. `peek()`: If `outStack` is empty, we transfer all elements from `inStack` to `outStack`. Then we return the top element of `stack2`.
4. `empty()`: We return true if both stacks are empty.

### Complexity Analysis

- Time Complexity:
    - `push()`: O(1)
    - `pop()`: Amortized O(1), worst-case O(n)
    - `peek()`: Amortized O(1), worst-case O(n)
    - `empty()`: O(1)
- Space Complexity: O(n), where n is the number of elements in the queue.

### Key Observations

1. The amortized time complexity of all operations is O(1). While a single pop or peek operation might take O(n) time to transfer elements, this only happens occasionally, and the cost is distributed among all operations.
2. This implementation satisfies the follow-up question about amortized O(1) time complexity.
3. The space complexity is optimal as we need to store all elements of the queue.

### LeetCode Link

[232. Implement Queue using Stacks](https://leetcode.com/problems/implement-queue-using-stacks/)