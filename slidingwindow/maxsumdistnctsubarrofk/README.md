# Maximum Sum of Distinct Subarrays With Length K

## Problem Description

Given an integer array `nums` and an integer `k`, find the maximum sum of a subarray of length `k` where all elements in the subarray are distinct.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class MaxSumDistnctSubarrayOfK:
    def maximum_distinct_subarray_sum(self, nums, k):
        distinct_elements = set()
        current_window_sum = 0
        max_distinct_sum = 0

        for i in range(len(nums)):
            # Maintain the size of the window and ensure all elements are distinct
            while len(distinct_elements) == k or nums[i] in distinct_elements:
                current_window_sum -= nums[i - len(distinct_elements)]
                distinct_elements.remove(nums[i - len(distinct_elements)])

            # Add the current element to the window
            distinct_elements.add(nums[i])
            current_window_sum += nums[i]

            # Update the maximum sum if the window size is k
            if len(distinct_elements) == k:
                max_distinct_sum = max(max_distinct_sum, current_window_sum)

        return max_distinct_sum  # Return the maximum sum of the distinct subarray


if __name__ == "__main__":
nums = [1, 5, 4, 2, 9, 9, 9]
k = 3
calculator = MaxSumDistnctSubarrayOfK()
result = calculator.maximum_distinct_subarray_sum(nums, k)
print(f"Maximum sum of distinct subarray of length {k}: {result}")

</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.HashSet;
import java.util.Set;

class MaxSumDistnctSubarrayOfK {
public long maximumDistinctSubarraySum(int[] nums, int k) {
Set<Integer> distinctElements = new HashSet<>();
long currentWindowSum = 0;
long maxDistinctSum = 0;

        for (int i = 0; i < nums.length; i++) {
            // Maintain the size of the window and ensure all elements are distinct
            while (distinctElements.size() == k || distinctElements.contains(nums[i])) {
                currentWindowSum -= nums[i - distinctElements.size()];
                distinctElements.remove(nums[i - distinctElements.size()]);
            }

            // Add the current element to the window
            distinctElements.add(nums[i]);
            currentWindowSum += nums[i];

            // Update the maximum sum if the window size is k
            if (distinctElements.size() == k) {
                maxDistinctSum = Math.max(maxDistinctSum, currentWindowSum);
            }
        }

        return maxDistinctSum; // Return the maximum sum of the distinct subarray
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 4, 2, 9, 9, 9};
        int k = 3;
        MaxSumDistnctSubarrayOfK calculator = new MaxSumDistnctSubarrayOfK();
        long result = calculator.maximumDistinctSubarraySum(nums, k);
        System.out.println("Maximum sum of distinct subarray of length " + k + ": " + result);
    }
}


</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a sliding window approach with a set to keep track of distinct elements.

### How It Works (Step-by-Step):

Consider the array: [1, 5, 4, 2, 9, 9, 9] and k = 3

1. Initialize:
    - window = set(), currentSum = 0, maxSum = 0

2. Iteration 1: [1]
    - Add 1 to window and currentSum
    - window = {1}, currentSum = 1

3. Iteration 2: [1, 5]
    - Add 5 to window and currentSum
    - window = {1, 5}, currentSum = 6

4. Iteration 3: [1, 5, 4]
    - Add 4 to window and currentSum
    - window = {1, 5, 4}, currentSum = 10
    - Window size is k, update maxSum = 10

5. Iteration 4: [5, 4, 2]
    - Remove 1 from window and currentSum, add 2
    - window = {5, 4, 2}, currentSum = 11
    - Update maxSum = 11

6. Iteration 5: [4, 2, 9]
    - Remove 5 from window and currentSum, add 9
    - window = {4, 2, 9}, currentSum = 15
    - Update maxSum = 15

7. Iteration 6: [2, 9]
    - Remove 4 from window and currentSum
    - window = {2, 9}, currentSum = 11

8. Iteration 7: [9]
    - Remove 2 from window and currentSum
    - window = {9}, currentSum = 9

9. Final result: maxSum = 15

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the array, as we traverse the array once.
- Space Complexity: O(k), as the set can contain at most k distinct elements.

## LeetCode Link

[2461. Maximum Sum of Distinct Subarrays With Length K](https://leetcode.com/problems/maximum-sum-of-distinct-subarrays-with-length-k/)