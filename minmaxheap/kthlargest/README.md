# Kth Largest Element in an Array

## Problem Description

Given an unsorted array of integers `nums` and an integer `k`, return the kth largest element in the array.

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
from typing import List

class KthLargestNum:
def findKthLargest(self, nums: List[int], k: int) -> int:
min_heap = []

        for num in nums:
            heapq.heappush(min_heap, num)
            
            # If the heap size exceeds k, remove the smallest element
            if len(min_heap) > k:
                heapq.heappop(min_heap)
        
        # The root of the heap is the k-th largest element
        return min_heap[0]

nums = [3, 2, 1, 5, 6, 4]
k = 2
solution = KthLargestNum()
result = solution.findKthLargest(nums, k)
print(f"The {k}th largest element is: {result}")

</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.PriorityQueue;
class KthLargestNum {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap to keep track of the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k); //OF SIZE K

        for (int num : nums) {
            minHeap.offer(num); // Add the current element to the heap

            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        KthLargestNum solution = new KthLargestNum();
        int result = solution.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result);
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a min heap (priority queue) to efficiently find the kth largest element in the array.

### How It Works (Step-by-Step):

Consider the array: [3, 2, 1, 5, 6, 4] and k = 2

1. Initialize an empty min heap.

2. Iterate through the array:
   - For 3: Add to heap. Heap: [3]
   - For 2: Add to heap. Heap: [2, 3]
   - For 1: Add to heap, then remove smallest. Heap: [2, 3]
   - For 5: Add to heap, then remove smallest. Heap: [3, 5]
   - For 6: Add to heap, then remove smallest. Heap: [5, 6]
   - For 4: Add to heap, then remove smallest. Heap: [5, 6]

3. The root of the heap (5) is the 2nd largest element.

4. Return this element as the result.

## Complexity Analysis

- Time Complexity: O(n log k), where n is the length of the array. We iterate through all n elements, and each heap operation takes O(log k) time.
- Space Complexity: O(k) to store the k largest elements in the heap.

## LeetCode Link

[215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)