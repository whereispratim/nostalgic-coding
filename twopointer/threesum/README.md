# 3Sum

## Problem Statement

Given an integer array `nums`, return all the triplets `[nums[i], nums[j], nums[k]]` such that `i != j`, `i != k`, and `j != k`, and `nums[i] + nums[j] + nums[k] == 0`. Notice that the solution set must not contain duplicate triplets.

## Examples

### Example 1:

Input: `nums = [-1,0,1,2,-1,-4]`
Output: `[[-1,-1,2],[-1,0,1]]`
Explanation:
- `nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0`
- `nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0`
- `nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0`
  The distinct triplets are `[-1,0,1]` and `[-1,-1,2]`.

### Example 2:

Input: `nums = [0,1,1]`
Output: `[]`
Explanation: The only possible triplet does not sum up to 0.

## Approach

1. Sort the input array.
2. Iterate through the array, fixing one element as the first element of the triplet.
3. Use two pointers (left and right) to find the other two elements that sum up to the negative of the fixed element.
4. Skip duplicate values to avoid duplicate triplets.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
def threeSum(nums):
    result = []

    # Step 1: Sort the array
    nums.sort()

    # Step 2: Iterate through the array
    for i in range(len(nums) - 2):
        # Skip duplicates for the first number
        if i > 0 and nums[i] == nums[i - 1]:
            continue

        # Apply the two-pointer approach to find two other numbers
        left, right = i + 1, len(nums) - 1

        while left < right:
            currentSum = nums[i] + nums[left] + nums[right]

            if currentSum == 0:
                # Found a triplet
                result.append([nums[i], nums[left], nums[right]])

                # Skip duplicates for the second number
                while left < right and nums[left] == nums[left + 1]:
                    left += 1

                # Skip duplicates for the third number
                while left < right and nums[right] == nums[right - 1]:
                    right -= 1

                # Move both pointers
                left += 1
                right -= 1
            elif currentSum < 0:
                # Sum is less than zero, move left pointer to the right
                left += 1
            else:
                # Sum is greater than zero, move right pointer to the left
                right -= 1

    return result

nums = [-1, 0, 1, 2, -1, -4]
result = threeSum(nums)
print("Unique triplets that sum up to 0:", result)

nums2 = [0, 1, 1]
result2 = threeSum(nums2)
print("Unique triplets that sum up to 0:", result2)

</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

public class ThreeSum {
public static List<List<Integer>> findThreeSum(int[] nums) {
List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Step 2: Iterate through the array and apply two-pointer technique
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // Apply the two-pointer approach to find two other numbers
            int left = i + 1, right = nums.length - 1;
            
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                if (currentSum == 0) {
                    // Found a triplet
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for the second number
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    
                    // Skip duplicates for the third number
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // Move both pointers
                    left++;
                    right--;
                } else if (currentSum < 0) {
                    // Sum is less than zero, move left pointer right
                    left++;
                } else {
                    // Sum is greater than zero, move right pointer left
                    right--;
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = findThreeSum(nums);
        System.out.println("Unique triplets that sum up to 0: " + result);
    }
}

</code></pre>
</td>
</tr>
</table>

## Step-by-Step Walkthrough

### Step 1: Sort the Array

We start by sorting the input array nums:
```
Original: [-1, 0, 1, 2, -1, -4]
Sorted: [-4, -1, -1, 0, 1, 2]
```

This sorted array helps us avoid duplicates and efficiently use the two-pointer approach.

### Step 2: Iterate through the Array

We now iterate over the sorted array and apply the two-pointer technique to find the triplets.

#### Iteration 1:
i = 0, nums[i] = -4

We need to find two other numbers that sum to 4 (-(-4) = 4).

Two-pointer setup:
- left = 1 (nums[left] = -1)
- right = 5 (nums[right] = 2)

Sub-iterations:
1. currentSum = nums[i] + nums[left] + nums[right] = -4 + (-1) + 2 = -3
   The sum is less than zero, so we move the left pointer to the right: left = 2.
2. currentSum = -4 + (-1) + 2 = -3 (still less than zero)
   Move the left pointer to the right again: left = 3.
3. currentSum = -4 + 0 + 2 = -2 (still less than zero)
   Move the left pointer to the right: left = 4.
4. currentSum = -4 + 1 + 2 = -1 (still less than zero)
   Move the left pointer to the right: left = 5.

No valid triplet is found for i = 0.

#### Iteration 2:
i = 1, nums[i] = -1

We need to find two other numbers that sum to 1 (-(-1) = 1).

Two-pointer setup:
- left = 2 (nums[left] = -1)
- right = 5 (nums[right] = 2)

Sub-iterations:
1. currentSum = -1 + (-1) + 2 = 0
   This is a valid triplet! We add [-1, -1, 2] to the result.
   Move both pointers to skip duplicates:
   left = 3 (skip duplicate nums[left] = -1)
   right = 4.
2. currentSum = -1 + 0 + 1 = 0
   This is another valid triplet! We add [-1, 0, 1] to the result.
   Move both pointers again:
   left = 4 and right = 3 (they cross over, so we stop this iteration).

At this point, we have found two valid triplets: [-1, -1, 2] and [-1, 0, 1].

#### Iteration 3:
i = 2, nums[i] = -1
Since nums[i] == nums[i - 1], we skip this iteration to avoid duplicates.

#### Iteration 4:
i = 3, nums[i] = 0

We need to find two other numbers that sum to 0 (-(0) = 0).

Two-pointer setup:
- left = 4 (nums[left] = 1)
- right = 5 (nums[right] = 2)

Sub-iteration:
1. currentSum = 0 + 1 + 2 = 3 (greater than zero)
   The sum is greater than zero, so we move the right pointer to the left: right = 4.

No valid triplet is found for i = 3.

### Result:
The loop ends, and we have found the two unique triplets:
```
[[-1, -1, 2], [-1, 0, 1]]
```
### Summary of Iterations:
- Iteration 1 (i = 0): No valid triplet found.
- Iteration 2 (i = 1): Found [-1, -1, 2] and [-1, 0, 1].
- Iteration 3 (i = 2): Skipped to avoid duplicates.
- Iteration 4 (i = 3): No valid triplet found.


## Complexity Analysis

- Time Complexity: Sorting has a time complexity of O(n log n). Nested for loops have a time complexity of O(n^2). Since O(n^2) is bigger than O(n log n), overall time complexity is O(n^2).
- Space Complexity: O(1) if we don't count the space required for the output. The space required for the output will be O(n) in the worst case.

## LeetCode Link

[15. 3Sum](https://leetcode.com/problems/3sum/)