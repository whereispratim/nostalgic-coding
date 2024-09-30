package minstack;

import java.util.*;

class MinValUsing1Stack {
    private Stack<Integer> stack;
    private int minElement;

    public MinValUsing1Stack() {
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
        MinValUsing1Stack minStack = new MinValUsing1Stack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // Output: -3
        minStack.pop();
        System.out.println(minStack.top());    // Output: 0
        System.out.println(minStack.getMin()); // Output: -2
    }
}
