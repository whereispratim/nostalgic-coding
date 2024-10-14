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

### Approach 1: Brute Force

The brute force approach involves calculating the product of all elements except the current one for each index.

#### Solution (Brute Force)
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class ProductExceptSelf:
    def productExceptSelf(self, nums):
        n = len(nums)
        result = [1] * n

        # For each element, compute the product of all other elements
        for i in range(n):
            product = 1
            for j in range(n):
                if i != j:
                    product *= nums[j]
            result[i] = product

        return result

solution = ProductExceptSelf()
nums = [1, 2, 3, 4]
print(solution.productExceptSelf(nums))  # Output: [24, 12, 8, 6]

</code></pre>
</td>
<td>
<pre><code class="java">
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // For each element in nums, calculate the product of all other elements
        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }

        return result;
    }

    public static void main(String[] args) {
        ProductExceptSelf solution = new ProductExceptSelf();
        int[] nums = {1, 2, 3, 4};
        int[] result = solution.productExceptSelf(nums);

        // Print result
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}


</code></pre>
</td>
</tr>
</table>

#### Explanation (Brute Force)

1. We initialize the `answer` array with 1s.
2. For each index `i` in the array:
    - We calculate the product of all elements except the one at index `i`.
    - We store this product in `answer[i]`.
3. We return the `answer` array.

1. **Outer Loop:** For each element in the array (i from 0 to n - 1), the outer loop tracks the element nums[i] whose product we need to calculate.

2. **Inner Loop:** The inner loop (j from 0 to n - 1) computes the product of all elements except nums[i]. If i == j, we skip that element.

3. **Store Product:** After computing the product for the current element, it is stored in the result[i].

4. **Time Complexity:** The brute force solution has a time complexity of O(n²) because for each element we are iterating through the entire array to compute the product.

#### Example Walkthrough (nums = [1, 2, 3, 4]):

1. For i = 0:
    - Compute the product of nums[1], nums[2], and nums[3]
    - 2 * 3 * 4 = 24
    - result[0] = 24

2. For i = 1:
    - Compute the product of nums[0], nums[2], and nums[3]
    - 1 * 3 * 4 = 12
    - result[1] = 12

3. For i = 2:
    - Compute the product of nums[0], nums[1], and nums[3]
    - 1 * 2 * 4 = 8
    - result[2] = 8

4. For i = 3:
    - Compute the product of nums[0], nums[1], and nums[2]
    - 1 * 2 * 3 = 6
    - result[3] = 6

#### Output:
For the input nums = [1, 2, 3, 4], the output will be:

#### Complexity Analysis (Brute Force)

- Time Complexity: O(n^2), where n is the length of the input array. We have two nested loops, each iterating n times.
- Space Complexity: O(1) if we don't count the output array. We only use a constant amount of extra space.

### LeetCode Link

[238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)