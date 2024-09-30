package maxsumdistnctsubarrofk;

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

