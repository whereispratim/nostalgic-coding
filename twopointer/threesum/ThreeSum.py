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

# Example 1
nums = [-1, 0, 1, 2, -1, -4]
result = threeSum(nums)
print("Unique triplets that sum up to 0:", result)

# Example 2
nums2 = [0, 1, 1]
result2 = threeSum(nums2)
print("Unique triplets that sum up to 0:", result2)
