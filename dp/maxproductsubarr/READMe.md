## Maximum Product Subarray

### Problem Statement

Given an integer array `nums`, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

### Examples

#### Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

#### Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

### Approach

We'll use a dynamic programming approach:
1. Keep track of the maximum product up to the current element (including the current element).
2. Also keep track of the minimum product up to the current element (to handle negative numbers).
3. At each step, update both max and min products.
4. Keep track of the overall maximum product seen so far.

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        if not nums:
            return 0

        max_so_far = nums[0]
        min_so_far = nums[0]
        result = max_so_far
        
        for i in range(1, len(nums)):
            curr = nums[i]
            temp_max = max(curr, max_so_far * curr, min_so_far * curr)
            min_so_far = min(curr, max_so_far * curr, min_so_far * curr)
            
            max_so_far = temp_max
            
            result = max(max_so_far, result)
        
        return result

solution = Solution()
print(solution.maxProduct([2,3,-2,4]))  # Output: 6
print(solution.maxProduct([-2,0,-1]))   # Output: 0
</code></pre>
</td>
<td>
<pre><code class="java">
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;
        
        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));
            
            maxSoFar = tempMax;
            
            result = Math.max(maxSoFar, result);
        }
        
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProduct(new int[]{2,3,-2,4}));  // Output: 6
        System.out.println(solution.maxProduct(new int[]{-2,0,-1}));   // Output: 0
    }
}
</code></pre>
</td>
</tr>
</table>

### Explanation

1. We initialize `max_so_far` and `min_so_far` with the first element of the array.
2. We iterate through the array starting from the second element:
    - For each element, we calculate three values:
      a. The element itself
      b. The product of the element and the previous max_so_far
      c. The product of the element and the previous min_so_far
    - We update `max_so_far` with the maximum of these three values.
    - We update `min_so_far` with the minimum of these three values.
    - We update the `result` if the new `max_so_far` is greater.
3. After iterating through all elements, we return the `result`.

### Detailed Walkthrough

Let's walk through the solution for nums = [2,3,-2,4]:

1. Initialize:
    - `max_so_far = 2`, `min_so_far = 2`, `result = 2`

2. For 3:
    - `temp_max = max(3, 2*3, 2*3) = 6`
    - `min_so_far = min(3, 2*3, 2*3) = 3`
    - `max_so_far = 6`
    - `result = max(6, 2) = 6`

3. For -2:
    - `temp_max = max(-2, 6*-2, 3*-2) = -2`
    - `min_so_far = min(-2, 6*-2, 3*-2) = -12`
    - `max_so_far = -2`
    - `result = max(-2, 6) = 6`

4. For 4:
    - `temp_max = max(4, -2*4, -12*4) = 4`
    - `min_so_far = min(4, -2*4, -12*4) = -48`
    - `max_so_far = 4`
    - `result = max(4, 6) = 6`

Final result: 6

### Complexity Analysis

- Time Complexity: O(n), where n is the length of the input array. We make a single pass through the array.
- Space Complexity: O(1). We only use a constant amount of extra space.

### Key Observations

1. This approach handles both positive and negative numbers efficiently.
2. By keeping track of both the maximum and minimum products, we can handle cases where multiplying by a negative number might give us a larger product.
3. The algorithm is able to find the maximum product subarray in a single pass, making it very efficient.

### LeetCode Link

[152. Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/)