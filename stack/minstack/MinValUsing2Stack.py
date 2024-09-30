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

# Example usage:
minStack = MinValUsing2Stack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
print(minStack.getMin())  # return -3
minStack.pop()
print(minStack.top())     # return 0
print(minStack.getMin())  # return -2
