## Product of Array Except Self

### Problem Statement

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

The product of any prefix or suffix of `nums` is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

### Examples

#### Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]

#### Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

### Approach

We'll use a two-pass approach:
1. First pass: Calculate the product of all elements to the left of each element.
2. Second pass: Calculate the product of all elements to the right of each element and multiply it with the left product.

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
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        n = len(nums)
        answer = [1] * n

        # Left pass
        left_product = 1
        for i in range(n):
            answer[i] = left_product
            left_product *= nums[i]
        
        # Right pass
        right_product = 1
        for i in range(n - 1, -1, -1):
            answer[i] *= right_product
            right_product *= nums[i]
        
        return answer

# Test the solution
solution = Solution()
print(solution.productExceptSelf([1,2,3,4]))       # Output: [24,12,8,6]
print(solution.productExceptSelf([-1,1,0,-3,3]))   # Output: [0,0,9,0,0]
</code></pre>
</td>
<td>
<pre><code class="java">
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Left pass
        int leftProduct = 1;
        for (int i = 0; i < n; i++) {
            answer[i] = leftProduct;
            leftProduct *= nums[i];
        }
        
        // Right pass
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return answer;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{1,2,3,4})));       // Output: [24,12,8,6]
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{-1,1,0,-3,3})));   // Output: [0,0,9,0,0]
    }
}
</code></pre>
</td>
</tr>
</table>

### Explanation

1. We initialize the `answer` array with 1s.
2. In the first pass (left to right):
    - We calculate the product of all elements to the left of the current element.
    - We store this product in the `answer` array.
3. In the second pass (right to left):
    - We calculate the product of all elements to the right of the current element.
    - We multiply this right product with the left product already stored in the `answer` array.
4. The final `answer` array contains the product of all elements except the element at that index.

### Detailed Walkthrough

Let's walk through the solution for nums = [1,2,3,4]:

1. Initialize: result = [1,1,1,1]

2. Left pass:
    - i = 0: result[0] = 1, left_product = 1 * 1 = 1
    - i = 1: result[1] = 1, left_product = 1 * 2 = 2
    - i = 2: result[2] = 2, left_product = 2 * 3 = 6
    - i = 3: result[3] = 6, left_product = 6 * 4 = 24
      After left pass: result = [1,1,2,6]

3. Right pass:
    - i = 3: result[3] = 6 * 1 = 6,  right_product = 1 * 4 = 4
    - i = 2: result[2] = 2 * 4 = 8,  right_product = 4 * 3 = 12
    - i = 1: result[1] = 1 * 12 = 12, right_product = 12 * 2 = 24
    - i = 0: result[0] = 1 * 24 = 24, right_product = 24 * 1 = 24

Final answer: [24,12,8,6]

### Complexity Analysis

- Time Complexity: O(n), where n is the length of the input array. We make two passes through the array.
- Space Complexity: O(1) if we don't count the output array. We only use a constant amount of extra space.

### LeetCode Link

[238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)