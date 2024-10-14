# Median of a Number Stream

## Problem Statement

Design a class to calculate the median of a number stream. The class should have the following two methods:

1. `insertNum(int num)`: stores the number in the class
2. `findMedian()`: returns the median of all numbers inserted in the class

If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.

## Examples
```
insertNum(3)
insertNum(1)
findMedian() -> output: 2
insertNum(5)
findMedian() -> output: 3
insertNum(4)
findMedian() -> output: 3.5
```

## Approach

We can use two heaps to solve this problem efficiently:
1. A max-heap to store the smaller half of the numbers
2. A min-heap to store the larger half of the numbers

We'll balance these heaps so that they either have an equal number of elements or the max-heap has one more element than the min-heap.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
import heapq

class MedianOfStream:
def __init__(self):
self.small = []  # max heap
self.large = []  # min heap

    def insertNum(self, num: int) -> None:
        if len(self.small) == len(self.large):
            heapq.heappush(self.large, -heapq.heappushpop(self.small, -num))
        else:
            heapq.heappush(self.small, -heapq.heappushpop(self.large, num))

    def findMedian(self) -> float:
        if len(self.small) == len(self.large):
            return (-self.small[0] + self.large[0]) / 2.0
        else:
            return float(self.large[0])

medianFinder = MedianOfStream()
medianFinder.insertNum(3)
medianFinder.insertNum(1)
print(medianFinder.findMedian())  # Output: 2.0
medianFinder.insertNum(5)
print(medianFinder.findMedian())  # Output: 3.0
medianFinder.insertNum(4)
print(medianFinder.findMedian())  # Output: 3.5
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.PriorityQueue;

class MedianOfStream {
private PriorityQueue<Integer> lowerHalfMaxHeap;  // Max heap to store the smaller half of the numbers
private PriorityQueue<Integer> upperHalfMinHeap;  // Min heap to store the larger half of the numbers

    public MedianOfStream() {
        lowerHalfMaxHeap = new PriorityQueue<>((a, b) -> b - a);  // Max heap to store the lower half
        upperHalfMinHeap = new PriorityQueue<>();  // Min heap to store the upper half
    }

    // Method to insert a number into the stream
    public void insertNumber(int number) {
        // Balance the heaps by alternating insertion between them
        if (lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            lowerHalfMaxHeap.offer(number);
            upperHalfMinHeap.offer(lowerHalfMaxHeap.poll());
        } else {
            upperHalfMinHeap.offer(number);
            lowerHalfMaxHeap.offer(upperHalfMinHeap.poll());
        }
    }

    // Method to find the median of the numbers inserted so far
    public double findMedian() {
        // If both heaps are the same size, the median is the average of the two middle elements
        if (lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            return (lowerHalfMaxHeap.peek() + upperHalfMinHeap.peek()) / 2.0;
        } else {
            // Otherwise, the median is the top element of the heap with more elements (upperHalfMinHeap)
            return upperHalfMinHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianOfStream medianCalculator = new MedianOfStream();
        medianCalculator.insertNumber(3);
        medianCalculator.insertNumber(1);
        System.out.println(medianCalculator.findMedian());  // Output: 2.0
        medianCalculator.insertNumber(5);
        System.out.println(medianCalculator.findMedian());  // Output: 3.0
        medianCalculator.insertNumber(4);
        System.out.println(medianCalculator.findMedian());  // Output: 3.5
    }
}

</code></pre>
</td>
</tr>
</table>


## Explanation of the Code

### Heap Definitions:
- `small`: A max-heap (using a PriorityQueue with a comparator to simulate max-heap behavior) that stores the smaller half of the numbers.
- `large`: A min-heap that stores the larger half of the numbers.

### Insertion (insertNum):
The logic balances the heaps:
- When both heaps are of equal size, a new number is first added to the `small` heap, and then the top of `small` is moved to `large`. This ensures that the `large` heap has the extra element when the total number of elements is odd.
- If the `large` heap has more elements than `small`, the number is added to `large`, and the top of `large` is moved to `small`.

### Finding Median (findMedian):
- If both heaps have the same number of elements, the median is the average of the two middle elements (the top of `small` and `large`).
- If one heap has more elements (specifically, `large`), the median is the root of `large`.

### Example Walkthrough:

1. Insert 3:
    - `small`: Empty.
    - `large`: Empty.
    - Since the sizes are equal, insert 3 into `small`, then move the top of `small` to `large`.
    - Result: `small = []`, `large = [3]`.
    - Median: The `large` heap has more elements, so the median is 3.0.

2. Insert 1:
    - Current heaps: `small = []`, `large = [3]`.
    - Insert 1 into `large`, then move the top of `large` to `small`.
    - Result: `small = [1]`, `large = [3]`.
    - Median: The heaps are equal in size, so the median is (1 + 3) / 2 = 2.0.

3. Insert 5:
    - Current heaps: `small = [1]`, `large = [3]`.
    - Insert 5 into `small`, then move the top of `small` to `large`.
    - Result: `small = [1]`, `large = [3, 5]`.
    - Median: The `large` heap has more elements, so the median is 3.0.

4. Insert 4:
    - Current heaps: `small = [1]`, `large = [3, 5]`.
    - Insert 4 into `large`, then move the top of `large` to `small`.
    - Result: `small = [3, 1]`, `large = [4, 5]`.
    - Median: The heaps are equal in size, so the median is (3 + 4) / 2 = 3.5.

### Output for the above code:
```
2.0
3.0
3.5
```

## Complexity Analysis

- Time Complexity:
    - `insertNum()`: O(log n) due to heap operations.
    - `findMedian()`: O(1) as we're only accessing the top elements of the heaps.
- Space Complexity: O(n), where n is the number of elements in the stream, as we store all elements in the heaps.

## LeetCode Link

[295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)