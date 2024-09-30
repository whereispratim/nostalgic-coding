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

# Example usage
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
solution = MaximumSubarray()
result = solution.maxSubArray(nums)
print(f"Maximum Subarray Sum: {result}")
