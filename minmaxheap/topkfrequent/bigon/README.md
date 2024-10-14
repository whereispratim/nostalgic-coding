# Top K Frequent Elements

## Problem Statement

Given an integer array `nums` and an integer `k`, return the `k` most frequent elements. You may return the answer in any order.

## Examples

### Example 1:

Input: `nums = [1,1,1,2,2,3]`, `k = 2`
Output: `[1,2]`
Explanation: The elements 1 and 2 appear three times and two times respectively, which are the two most frequent elements.

### Example 2:

Input: `nums = [1]`, `k = 1`
Output: `[1]`
Explanation: The element 1 appears once, which is the most frequent element.

## Approach

1. Count the frequency of each number using a hash map.
2. Create buckets (arrays) where the index represents the frequency.
3. Place numbers in their frequency buckets.
4. Collect the K most frequent elements by iterating through the buckets from highest to lowest frequency.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import Counter

class TopKFrequentNumbers:
@staticmethod
def find_top_k_frequent_numbers(nums, k):
frequency_map = Counter(nums)

        # Step 2: Initialize an array of empty lists for bucket sorting
        # The size of the bucket is len(nums) + 1 because the max frequency can't exceed the size of nums.
        bucket = [[] for _ in range(len(nums) + 1)]

        # Step 3: Populate the bucket array, where index represents the frequency
        for num, freq in frequency_map.items():
            bucket[freq].append(num)

        # Step 4: Collect the top 'K' frequent numbers from the bucket
        result = []
        for i in range(len(bucket) - 1, 0, -1):  # Iterate from the highest possible frequency to the lowest
            if bucket[i]:
                result.extend(bucket[i])  # Add all numbers with this frequency to the result
            if len(result) >= k:  # Stop once we've collected 'K' numbers
                return result[:k]

        return result

nums = [1, 3, 5, 12, 11, 12, 11]
K = 2
result = TopKFrequentNumbers.find_top_k_frequent_numbers(nums, K)
print("Top K frequent numbers:", result)  # Output: [12, 11]

nums = [5, 12, 11, 3, 11]
K = 2
result = TopKFrequentNumbers.find_top_k_frequent_numbers(nums, K)
print("Top K frequent numbers:", result)  # Output: [11, 5] or [11, 12] or [11, 3]

</code></pre>
</td>
<td>
<pre><code class="java">
mport java.util.*;

class TopKFrequentNumbers {

    public List<Integer> findTopKFrequentNumbers(int[] numbers, int k) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        // Step 2: Create an array of lists (buckets) to group numbers by frequency
        List<Integer>[] frequencyBuckets = new ArrayList[numbers.length + 1];
        for (int i = 0; i <= numbers.length; i++) {
            frequencyBuckets[i] = new ArrayList<>();
        }

        // Step 3: Place each number into its corresponding frequency bucket
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            frequencyBuckets[frequency].add(number);
        }

        // Step 4: Collect the top 'K' frequent numbers from the buckets, starting from the highest frequency
        List<Integer> topKFrequentNumbers = new ArrayList<>();
        for (int i = frequencyBuckets.length - 1; i > 0; i--) {  // Start from the highest frequency bucket
            for (int number : frequencyBuckets[i]) {
                topKFrequentNumbers.add(number);
                if (topKFrequentNumbers.size() == k) {  // Stop when we have collected 'K' numbers
                    return topKFrequentNumbers;
                }
            }
        }

        return topKFrequentNumbers;  // This should not be reached if input is valid
    }

    public static void main(String[] args) {
        TopKFrequentNumbers finder = new TopKFrequentNumbers();

        // Test case 1
        int[] numbers1 = {1, 3, 5, 12, 11, 12, 11};
        int k1 = 2;
        List<Integer> result1 = finder.findTopKFrequentNumbers(numbers1, k1);
        System.out.println("Top K frequent numbers: " + result1);  // Expected output: [12, 11]

        // Test case 2
        int[] numbers2 = {5, 12, 11, 3, 11};
        int k2 = 2;
        List<Integer> result2 = finder.findTopKFrequentNumbers(numbers2, k2);
        System.out.println("Top K frequent numbers: " + result2);  // Expected output: [11, 5] or [11, 12] or [11, 3]
    }
}


</code></pre>
</td>
</tr>
</table>

## Explanation

1. We start by counting the frequency of each number in the input array using a hash map.

2. We create an array of buckets where the index represents the frequency. The maximum possible frequency is the length of the input array.

3. We iterate through the frequency map and place each number in its corresponding frequency bucket.

4. Finally, we iterate through the buckets from highest frequency to lowest, collecting numbers until we have K elements.

## Explanation of Each Step

### Frequency Counting:

We create a HashMap to count the frequency of each number in the array.
For example, in the first test case `[1, 3, 5, 12, 11, 12, 11]`, the frequencies will be:
- 1 -> 1 time
- 3 -> 1 time
- 5 -> 1 time
- 12 -> 2 times
- 11 -> 2 times

### Bucket Creation:

We create an array of ArrayLists called `frequencyBuckets`. The index of this array represents the frequency count, and each index contains a list of numbers that appear with that frequency.
For example, after processing the first test case, `frequencyBuckets` would look like this:
- Index 0: `[]`
- Index 1: `[1, 3, 5]`
- Index 2: `[12, 11]`
- Index 3: `[]`
- Index 4: `[]`
- Index 5: `[]`
- Index 6: `[]`
- Index 7: `[]`

### Collecting Top K Frequent Numbers:

We iterate over `frequencyBuckets` starting from the highest frequency. We add numbers to the `topKFrequentNumbers` list until we reach `k`.
In the first test case (with k = 2), we would collect:
- From index 2: Add 12 and 11 to the result.
- Since we've collected 2 numbers, we stop and return `[12, 11]`.


This approach effectively finds and displays the top K frequent numbers for the provided test cases, utilizing a counting approach combined with bucket sorting for efficiency.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input array. We iterate through the array once to build the frequency map, and then we perform bucket operations which are also linear.
- Space Complexity: O(n) for the frequency map and the buckets.

## LeetCode Link

[347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)