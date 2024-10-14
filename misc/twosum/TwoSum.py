class TwoSum:
    def twoSum(self, nums, target):
        # Create a dictionary to store the number and its index
        num_map = {}

        # Iterate over the list of numbers
        for i, num in enumerate(nums):
            complement = target - num  # Find the complement

            # Check if the complement is already in the dictionary
            if complement in num_map:
                # Return the indices of the complement and the current number
                return [num_map[complement], i]

            # Store the current number and its index in the dictionary
            num_map[num] = i

        # Return an empty list if no solution is found
        return []

# Sample usage
if __name__ == "__main__":
    solution = TwoSum()

    # Test case 1
    nums = [2, 7, 11, 15]
    target = 9
    print(solution.twoSum(nums, target))  # Output: [0, 1]

    # Test case 2
    nums = [3, 2, 4]
    target = 6
    print(solution.twoSum(nums, target))  # Output: [1, 2]

    # Test case 3
    nums = [3, 3]
    target = 6
    print(solution.twoSum(nums, target))  # Output: [0, 1]
