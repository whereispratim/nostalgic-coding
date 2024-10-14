package queueusingstack;

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
