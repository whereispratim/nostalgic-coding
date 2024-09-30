# Maximum Sum Subarray With Length k

## Problem Description

Given an array of integers and a positive integer k, find the maximum sum of any contiguous subarray of size k.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class MaxSumSubarrayOfK:
    def __init__(self, array):
        self.array = array

    def max_sum_of_subarray(self, subarray_size):
        # Edge case: if the array length is less than the size of the subarray
        if len(self.array) < subarray_size:
            return -1  # or raise an exception

        current_window_sum = sum(self.array[:subarray_size])  # Calculate the sum of the first 'subarray_size' elements
        max_sum = current_window_sum

        # Iterate over the array starting from the 'subarray_size' index
        for i in range(subarray_size, len(self.array)):
            # Update the window sum: remove the element going out of the window
            # and add the new element coming into the window
            current_window_sum = current_window_sum - self.array[i - subarray_size] + self.array[i]
            max_sum = max(max_sum, current_window_sum)  # Update the maximum sum

        return max_sum  # Return the maximum sum of the subarray found

if __name__ == "__main__":
array = [1, 4, 2, 10, 23, 3, 1, 0, 20]
subarray_size = 4
calculator = MaxSumSubarrayOfK(array)
result = calculator.max_sum_of_subarray(subarray_size)
print(f"Maximum sum of subarray of size {subarray_size}: {result}")

</code></pre>
</td>
<td>
<pre><code class="java">
public class MaxSumSubarrayOfK {

    public static int maxSumOfSubarray(int[] array, int subarraySize) {
        // Edge case: if the array length is less than the size of the subarray
        if (array.length < subarraySize) {
            return -1; // or throw an exception
        }

        int currentWindowSum = 0;

        // Calculate the sum of the first 'subarraySize' elements
        for (int i = 0; i < subarraySize; i++) {
            currentWindowSum += array[i];
        }

        int maxSum = currentWindowSum;

        // Iterate over the array starting from the 'subarraySize' index
        for (int i = subarraySize; i < array.length; i++) {
            // Update the window sum: remove the element going out of the window
            // and add the new element coming into the window
            currentWindowSum = currentWindowSum - array[i - subarraySize] + array[i];
            maxSum = Math.max(maxSum, currentWindowSum); // Update the maximum sum
        }

        return maxSum; // Return the maximum sum of the subarray found
    }

    public static void main(String[] args) {
        int[] array = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int subarraySize = 4;
        int result = maxSumOfSubarray(array, subarraySize);
        System.out.println("Maximum sum of subarray of size " + subarraySize + ": " + result);
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses the sliding window technique to efficiently find the maximum sum subarray of length k.

### How It Works (Step-by-Step):

Consider the array: [1, 4, 2, 10, 23, 3, 1, 0, 20] and k = 4

1. Initialization:
   - Calculate the sum of the first k elements: 1 + 4 + 2 + 10 = 17
   - Set max_sum = 17

2. Sliding the window:
   - Move the window one step right:
      - Subtract the first element (1) and add the next element (23)
      - New sum: 17 - 1 + 23 = 39
      - Update max_sum = 39

3. Continue sliding:
   - Next window: 4 + 2 + 10 + 23 = 39
   - Next window: 2 + 10 + 23 + 3 = 38
   - Next window: 10 + 23 + 3 + 1 = 37
   - Next window: 23 + 3 + 1 + 0 = 27
   - Next window: 3 + 1 + 0 + 20 = 24

4. Final result:
   - The maximum sum found is 39

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the array, as we traverse the array once.
- Space Complexity: O(1), as we use only a constant amount of extra space regardless of the input size.

## LeetCode Link

[This problem is similar to LeetCode 643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i/)