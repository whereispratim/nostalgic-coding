# Kth Smallest Element in an Array

## Problem Description

Given an unsorted array of integers `nums` and an integer `k`, return the kth smallest element in the array.

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

def find_kth_smallest(nums: List[int], k: int) -> int:
max_heap = []

    for num in nums:
        if len(max_heap) < k:
            heapq.heappush(max_heap, -num)  # Use negative for max-heap
        elif -num > max_heap[0]:
            heapq.heapreplace(max_heap, -num)
    
    return -max_heap[0]  # The root of the heap is the kth smallest element

nums = [7, 10, 4, 3, 20, 15]
k = 3
result = find_kth_smallest(nums, k)
print(f"The {k}th smallest element is: {result}")
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.PriorityQueue;
import java.util.Collections;
class KthSmallestNum {
    public static int findKthSmallest(int[] nums, int k) {
        // Use a max-heap to keep track of the k smallest elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();  // Remove the largest element
            }
        }
        return maxHeap.peek();  // The root of the heap is the kth smallest element
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;
        int result = findKthSmallest(nums, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a max heap (priority queue) to efficiently find the kth smallest element in the array. The key idea is to maintain a heap of size k that always contains the k smallest elements seen so far.

### How It Works (Step-by-Step):

Consider the array: [7, 10, 4, 3, 20, 15] and k = 3

1. Initialize an empty max heap.

2. Iterate through the array:
   - For 7: Add to heap. Heap: [7]
      * Heap size (1) is less than k (3), so we add 7.
   - For 10: Add to heap. Heap: [10, 7]
      * Heap size (2) is still less than k, so we add 10.
   - For 4: Add to heap. Heap: [10, 7, 4]
      * Heap size (3) is now equal to k, so we add 4.
   - For 3: Add to heap, remove largest. Heap: [7, 4, 3]
      * Heap is full, so we add 3 and remove the largest (10).
   - For 20: Add to heap, remove largest. Heap: [7, 4, 3]
      * 20 is larger than the largest in the heap (7), so we don't add it.
   - For 15: Add to heap, remove largest. Heap: [7, 4, 3]
      * 15 is larger than the largest in the heap (7), so we don't add it.

3. The root of the heap (7) is the 3rd smallest element.

4. Return this element as the result.

### Key Concepts:

1. Max Heap Usage: We use a max heap of size k. This ensures that the largest element among the k smallest is always at the root.

2. Heap Maintenance:
   - If the heap size is less than k, we add elements directly.
   - Once the heap has k elements, for each new element:
      * If it's smaller than the root (largest in the heap), we remove the root and add the new element.
      * If it's larger than or equal to the root, we ignore it.

3. Final Result: After processing all elements, the root of the heap is the kth smallest element.

### Why This Works:

- By maintaining a max heap of size k, we ensure that we always have the k smallest elements seen so far in the heap.
- The largest among these k elements (the root of the max heap) is the kth smallest element in the entire array.
- This approach is more efficient than sorting the entire array, especially for large arrays with small k values.

### Advantages:

1. Efficiency: It's more efficient than sorting for large arrays when k is small.
2. Space: It uses only O(k) extra space.
3. Online Processing: It can process elements one by one, suitable for streaming data.

This method effectively "filters" the array to keep only the k smallest elements, with the kth smallest always at the top of the heap.

## Complexity Analysis

- Time Complexity: O(n log k), where n is the length of the array. We iterate through all n elements, and each heap operation takes O(log k) time.
- Space Complexity: O(k) to store the k smallest elements in the heap.

## Geeksforgeeks Link

[Kth Smallest Element in an Array](https://www.geeksforgeeks.org/kth-smallest-largest-element-in-unsorted-array/)