# Maximum Subarray

## Problem Description

The Maximum Subarray problem is a classic algorithm challenge that requires finding the contiguous subarray with the largest sum in a given integer array. This repository contains both Python and Java implementations of the solution using `Kadane's Algorithm.`
## Solution

<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class MaximumSubarray:
    def maxSubArray(self, nums):
        # Initialize currentSum and maxSum with the first element of the array
        currentSum = nums[0]
        maxSum = nums[0]

        # Iterate through the array starting from the second element
        for i in range(1, len(nums)):
            # For each element, decide whether to start a new subarray or continue the existing one
            currentSum = max(nums[i], currentSum + nums[i])

            # Update maxSum if currentSum is larger
            maxSum = max(maxSum, currentSum)

            # Print each iteration's details for better understanding
            print(f"Iteration {i}: current element = {nums[i]}, currentSum = {currentSum}, maxSum = {maxSum}")

        return maxSum

nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
solution = MaximumSubarray()
result = solution.maxSubArray(nums)
print(f"Maximum Subarray Sum: {result}")


</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.Arrays;

public class MaximumSubarray {
    public static int maxSubArray(int[] nums) {
        // Initialize currentSum and maxSum with the first element of the array
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // For each element, decide whether to start a new subarray or continue the existing one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Update maxSum if currentSum is larger
            maxSum = Math.max(maxSum, currentSum);
            
            // Print each iteration's details for better understanding
            System.out.println("Iteration " + i + ": current element = " + nums[i] +
                               ", currentSum = " + currentSum + ", maxSum = " + maxSum);
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubArray(nums);
        System.out.println("Maximum Subarray Sum: " + result);
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses Kadane's algorithm to solve the maximum subarray problem efficiently.

### How It Works (Step-by-Step for Both Java and Python):

Consider the array: [-2, 1, -3, 4, -1, 2, 1, -5, 4].

1. Initialization:
    - maxSum = currentSum = -2

2. Iteration 1:
    - current element = 1
    - currentSum = max(1, -2 + 1) = 1
    - maxSum = max(-2, 1) = 1

3. Iteration 2:
    - current element = -3
    - currentSum = max(-3, 1 + -3) = -2
    - maxSum = max(1, -2) = 1

4. Iteration 3:
    - current element = 4
    - currentSum = max(4, -2 + 4) = 4
    - maxSum = max(1, 4) = 4

5. Iteration 4:
    - current element = -1
    - currentSum = max(-1, 4 + -1) = 3
    - maxSum = max(4, 3) = 4

6. Iteration 5:
    - current element = 2
    - currentSum = max(2, 3 + 2) = 5
    - maxSum = max(4, 5) = 5

7. Iteration 6:
    - current element = 1
    - currentSum = max(1, 5 + 1) = 6
    - maxSum = max(5, 6) = 6

8. Iteration 7:
    - current element = -5
    - currentSum = max(-5, 6 + -5) = 1
    - maxSum = max(6, 1) = 6

9. Iteration 8:
    - current element = 4
    - currentSum = max(4, 1 + 4) = 5
    - maxSum = max(6, 5) = 6

The algorithm maintains two variables:
- `currentSum`: Represents the maximum sum ending at the current position.
- `maxSum`: Represents the maximum sum found so far.

At each step, we decide whether to start a new subarray (by taking the current element alone) or extend the existing subarray (by adding the current element to the previous sum). We update `maxSum` whenever we find a larger sum.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input array
- Space Complexity: O(1), as we only use a constant amount of extra space

## LeetCode Link

[53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)