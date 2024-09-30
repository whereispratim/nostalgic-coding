# Search in Rotated Sorted Array

## Problem Description

There is an integer array `nums` sorted in ascending order (with distinct values).

Prior to being passed to your function, `nums` is possibly rotated at an unknown pivot index `k` (1 <= k < nums.length) such that the resulting array is `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]` (0-indexed). For example, `[0,1,2,4,5,6,7]` might be rotated at pivot index 3 and become `[4,5,6,7,0,1,2]`.

Given the array `nums` after the possible rotation and an integer `target`, return the index of `target` if it is in `nums`, or -1 if it is not in `nums`.

You must write an algorithm with O(log n) runtime complexity.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class RotatedArraySearch:
    def search_in_rotated_array(self, nums, target):
        left_index, right_index = 0, len(nums) - 1

        while left_index <= right_index:
            mid_index = left_index + (right_index - left_index) // 2
            
            # Check if the target is found at mid_index
            if nums[mid_index] == target:
                return mid_index
            
            # Check if the left half is sorted
            if nums[left_index] <= nums[mid_index]:
                # Target is in the sorted left half
                if nums[left_index] <= target < nums[mid_index]:
                    right_index = mid_index - 1  # Search in left half
                else:
                    left_index = mid_index + 1  # Search in right half
            # The right half must be sorted
            else:
                # Target is in the sorted right half
                if nums[mid_index] < target <= nums[right_index]:
                    left_index = mid_index + 1  # Search in right half
                else:
                    right_index = mid_index - 1  # Search in left half
        
        return -1  # Target not found

if __name__ == "__main__":
searcher = RotatedArraySearch()
print(searcher.search_in_rotated_array([4, 5, 6, 7, 0, 1, 2], 0))  # Output: 4
print(searcher.search_in_rotated_array([4, 5, 6, 7, 0, 1, 2], 3))  # Output: -1
</code></pre>
</td>
<td>
<pre><code class="java">
class RotatedArraySearch {
    public int searchInRotatedArray(int[] nums, int target) {
        int leftIndex = 0, rightIndex = nums.length - 1;

        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // Check if the target is found at midIndex
            if (nums[midIndex] == target) {
                return midIndex;
            }

            // Check if the left half is sorted
            if (nums[leftIndex] <= nums[midIndex]) {
                // Target is in the sorted left half
                if (nums[leftIndex] <= target && target < nums[midIndex]) {
                    rightIndex = midIndex - 1; // Search in left half
                } else {
                    leftIndex = midIndex + 1; // Search in right half
                }
            }
            // The right half must be sorted
            else {
                // Target is in the sorted right half
                if (nums[midIndex] < target && target <= nums[rightIndex]) {
                    leftIndex = midIndex + 1; // Search in right half
                } else {
                    rightIndex = midIndex - 1; // Search in left half
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        RotatedArraySearch searcher = new RotatedArraySearch();
        System.out.println(searcher.searchInRotatedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));  // Output: 4
        System.out.println(searcher.searchInRotatedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));  // Output: -1
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a modified binary search algorithm to search for the target in a rotated sorted array.

### How It Works (Step-by-Step):

Let's consider the example: nums = [4,5,6,7,0,1,2], target = 0

1. Initialize left = 0, right = 6
   - mid = (0 + 6) // 2 = 3
   - nums[mid] = 7 ≠ target (0)
   - Left half [4,5,6,7] is sorted
   - target (0) is not in this range, so search right half
   - left = mid + 1 = 4

2. left = 4, right = 6
   - mid = (4 + 6) // 2 = 5
   - nums[mid] = 1 ≠ target (0)
   - Left half [0,1] is sorted
   - target (0) is in this range, so search left half
   - right = mid - 1 = 4

3. left = 4, right = 4
   - mid = (4 + 4) // 2 = 4
   - nums[mid] = 0 = target
   - Return mid (4)

### Key Concepts:

1. Modified Binary Search: We use binary search but with additional logic to handle the rotation.
2. Identifying Sorted Half: In each iteration, we determine which half of the array is sorted.
3. Range Check: We check if the target is within the range of the sorted half.

### Why This Works:

- At least one half of the array is always sorted.
- By identifying the sorted half and checking if the target is in its range, we can eliminate half of the array in each iteration.
- This allows us to maintain O(log n) time complexity despite the rotation.

## Complexity Analysis

- Time Complexity: O(log n), where n is the length of the array. We eliminate half of the array in each iteration.
- Space Complexity: O(1), as we only use a constant amount of extra space.

## LeetCode Link

[33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)