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

# Example usage
myQueue = MyQueue()
myQueue.push(1)      # queue is [1]
myQueue.push(2)      # queue is [1, 2]
print(myQueue.peek()) # return 1
print(myQueue.pop())  # return 1, queue is [2]
print(myQueue.empty()) # return False
