# Top 'K' Frequent Numbers

## Problem Statement

Given an unsorted array of numbers, find the top 'K' frequently occurring numbers in it.

## Examples

### Example 1:

Input: `[1, 3, 5, 12, 11, 12, 11]`, K = 2
Output: `[12, 11]`
Explanation: Both '11' and '12' appeared twice.

### Example 2:

Input: `[5, 12, 11, 3, 11]`, K = 2
Output: `[11, 5]` or `[11, 12]` or `[11, 3]`
Explanation: Only '11' appeared twice, all other numbers appeared once.

## Approach

We can solve this problem using the following steps:
1. Count the frequency of each number using a hash map.
2. Use a min-heap to keep track of the K most frequent elements.
3. Iterate through the frequency map, maintaining a min-heap of size K.
4. Return the elements in the heap as the result.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from heapq import heappush, heappop

class TopKFrequentNumbers:
def find_top_k_frequent_numbers(self, nums, k):

frequency_map = {}
for num in nums:
frequency_map[num] = frequency_map.get(num, 0) + 1

        # Step 2: Use a min-heap to keep track of top 'K' frequent numbers
        min_heap = []
        
        # Step 3: Add each number's frequency to the heap
        for num, freq in frequency_map.items():
            heappush(min_heap, (freq, num))
            if len(min_heap) > k:
                heappop(min_heap)  # remove the least frequent element
        
        # Step 4: Extract the top 'K' frequent numbers from the heap
        top_numbers = [heappop(min_heap)[1] for _ in range(len(min_heap))]
        
        return top_numbers

top_k_frequent = TopKFrequentNumbers()
result = top_k_frequent.find_top_k_frequent_numbers([1, 3, 5, 12, 11, 12, 11], 2)
print("Top K frequent numbers:", result)  # Output: [12, 11]

result = top_k_frequent.find_top_k_frequent_numbers([5, 12, 11, 3, 11], 2)
print("Top K frequent numbers:", result)  # Output: [11, 5] or [11, 12] or [11, 3]
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class TopKFrequentNumbers {
public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
// Step 1: Count the frequency of each number
Map<Integer, Integer> frequencyMap = new HashMap<>();
for (int num : nums) {
frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
}

        // Step 2: Use a min-heap to keep track of top 'K' frequent numbers
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Step 3: Add each number's frequency to the heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();  // remove the least frequent element
            }
        }

        // Step 4: Extract the top 'K' frequent numbers from the heap
        List<Integer> topNumbers = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().getKey());
        }

        return topNumbers;
    }

    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Top K frequent numbers: " + result);  // Output: [12, 11]

        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Top K frequent numbers: " + result);  // Output: [11, 5] or [11, 12] or [11, 3]
    }
}

</code></pre>
</td>
</tr>
</table>


## Step-by-Step Explanation

### Step 1: Build Frequency Map

First, we build a frequency map from the input array. For each number in the array, we count how many times it appears.

Array: `[1, 3, 5, 12, 11, 12, 11]`

Iterating through each number:

1. 1: Frequency map becomes `{1=1}`
2. 3: Frequency map becomes `{1=1, 3=1}`
3. 5: Frequency map becomes `{1=1, 3=1, 5=1}`
4. 12: Frequency map becomes `{1=1, 3=1, 5=1, 12=1}`
5. 11: Frequency map becomes `{1=1, 3=1, 5=1, 12=1, 11=1}`
6. 12: Frequency map becomes `{1=1, 3=1, 5=1, 12=2, 11=1}`
7. 11: Frequency map becomes `{1=1, 3=1, 5=1, 12=2, 11=2}`

Final Frequency Map: `{1=1, 3=1, 5=1, 12=2, 11=2}`

### Step 2: Process the Frequency Map with a Min Heap

Now, we use a Min Heap (Priority Queue) to store the top 'K' frequent numbers. We iterate through the frequency map and insert the numbers into the heap. If the heap size exceeds K, we remove the least frequent element.

Heap: Initially empty.

Processing each entry in the frequency map:

1. (1, 1):
    - Heap: `[(1, 1)]`
    - Heap size: 1 (less than K = 2), so no removal is necessary.

2. (3, 1):
    - Heap: `[(1, 1), (3, 1)]`
    - Heap size: 2 (equal to K = 2), so no removal is necessary.

3. (5, 1):
    - Heap: `[(1, 1), (3, 1), (5, 1)]`
    - Heap size exceeds K = 2, so we remove the smallest frequency element.
    - Removed: (1, 1)
    - Heap after removal: `[(3, 1), (5, 1)]`

4. (12, 2):
    - Heap: `[(3, 1), (5, 1), (12, 2)]`
    - Heap size exceeds K = 2, so we remove the smallest frequency element.
    - Removed: (3, 1)
    - Heap after removal: `[(5, 1), (12, 2)]`

5. (11, 2):
    - Heap: `[(5, 1), (12, 2), (11, 2)]`
    - Heap size exceeds K = 2, so we remove the smallest frequency element.
    - Removed: (5, 1)
    - Heap after removal: `[(12, 2), (11, 2)]`

Final Heap: `[(12, 2), (11, 2)]`

This heap now contains the top 'K' frequent numbers.

### Step 3: Extract the Top 'K' Elements

Now that we have processed the frequency map, we extract the top K frequent numbers from the heap.

1. Extract (12, 2) from the heap: Result: `[12]`
2. Extract (11, 2) from the heap: Result: `[12, 11]`

Final Result: `[12, 11]` (this can be any permutation of `[11, 12]` as both are valid since both have the same frequency).

### Summary of Each Iteration:

1. Iteration 1:
    - Process number 1.
    - Frequency map: `{1=1}`
    - Heap: `[(1, 1)]`

2. Iteration 2:
    - Process number 3.
    - Frequency map: `{1=1, 3=1}`
    - Heap: `[(1, 1), (3, 1)]`

3. Iteration 3:
    - Process number 5.
    - Frequency map: `{1=1, 3=1, 5=1}`
    - Heap: `[(3, 1), (5, 1)]` (removed (1, 1))

4. Iteration 4:
    - Process number 12.
    - Frequency map: `{1=1, 3=1, 5=1, 12=2}`
    - Heap: `[(5, 1), (12, 2)]` (removed (3, 1))

5. Iteration 5:
    - Process number 11.
    - Frequency map: `{1=1, 3=1, 5=1, 12=2, 11=2}`
    - Heap: `[(12, 2), (11, 2)]` (removed (5, 1))

6. Iteration 6:
    - Extract top K frequent elements.
    - Heap: `[(12, 2), (11, 2)]`
    - Result: `[12, 11]`
    - 
## Example 2


## Explanation of Each Iteration

We will track the state of the heap and frequency map step by step as we process the array `[5, 12, 11, 3, 11]` with K = 2.

### Step 1: Build Frequency Map

As we iterate over the array, we count the frequency of each number.
Array: `[5, 12, 11, 3, 11]`

After processing each number:

1. Processing 5: `{5=1}`
2. Processing 12: `{5=1, 12=1}`
3. Processing 11: `{5=1, 12=1, 11=1}`
4. Processing 3: `{5=1, 12=1, 11=1, 3=1}`
5. Processing 11: `{5=1, 12=1, 11=2, 3=1}`

Final Frequency Map: `{5=1, 12=1, 11=2, 3=1}`

### Step 2: Process the Frequency Map with a Min Heap

We now process the entries in the frequency map and maintain a min heap to keep track of the top K frequent elements.
Heap: Initially empty.

Processing each entry:

1. Insert (5, 1) into the heap:
    - Heap: `[(5, 1)]`

2. Insert (12, 1) into the heap:
    - Heap: `[(5, 1), (12, 1)]`

3. Insert (11, 2) into the heap:
    - Heap: `[(5, 1), (12, 1), (11, 2)]`
    - Since the heap size exceeds K = 2, we remove the smallest frequency element:
        - Removed: (5, 1)
    - Heap: `[(12, 1), (11, 2)]`

4. Insert (3, 1) into the heap:
    - Heap: `[(3, 1), (11, 2), (12, 1)]`
    - Since the heap size exceeds K = 2, we remove the smallest frequency element:
        - Removed: (3, 1)
    - Heap: `[(12, 1), (11, 2)]`

### Step 3: Extract the Top K Elements

We extract the elements from the heap, which are the top K frequent numbers.

1. Extract (12, 1) from the heap: Result: `[12]`
2. Extract (11, 2) from the heap: Result: `[12, 11]`

Final Result: `[12, 11]` (this can be any permutation of `[11, 12]` as both are valid)

## Detailed Walkthrough of the Algorithm

1. **Building the Frequency Map**:
    - We iterate through the input array once, counting the occurrences of each number.
    - This step has a time complexity of O(N), where N is the length of the input array.
    - The space complexity is O(M), where M is the number of unique elements in the array.

2. **Processing with Min Heap**:
    - We iterate through the frequency map, which has at most M entries.
    - For each entry, we perform heap operations:
        - If the heap size is less than K, we simply add the element (O(log K)).
        - If the heap size is K and the current element's frequency is greater than the top of the heap, we remove the top element and add the current one (O(log K)).
    - This step has a time complexity of O(M log K).
    - The space complexity is O(K) for the heap.

3. **Extracting the Result**:
    - We extract K elements from the heap.
    - This step has a time complexity of O(K log K).

Overall Time Complexity: O(N + M log K)
Overall Space Complexity: O(M + K)

Where:
- N is the length of the input array
- M is the number of unique elements in the array
- K is the number of top frequent elements we want to find

This approach is particularly efficient when K is much smaller than M, as it allows us to process the elements in O(M log K) time instead of sorting all elements, which would take O(M log M) time.


## LeetCode Link

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)