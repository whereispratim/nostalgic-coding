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