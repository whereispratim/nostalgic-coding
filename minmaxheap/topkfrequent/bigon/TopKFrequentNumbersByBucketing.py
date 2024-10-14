from collections import Counter

class TopKFrequentNumbers:
    @staticmethod
    def find_top_k_frequent_numbers(nums, k):
        # Step 1: Count the frequencies of each number
        frequency_map = Counter(nums)

        # Step 2: Initialize an array of empty lists for bucket sorting
        # The size of the bucket is len(nums) + 1 because the max frequency can't exceed the size of nums.
        bucket = [[] for _ in range(len(nums) + 1)]

        # Step 3: Populate the bucket array, where index represents the frequency
        for num, freq in frequency_map.items():
            bucket[freq].append(num)

        # Step 4: Collect the top 'K' frequent numbers from the bucket
        result = []
        for i in range(len(bucket) - 1, 0, -1):  # Iterate from the highest possible frequency to the lowest
            if bucket[i]:
                result.extend(bucket[i])  # Add all numbers with this frequency to the result
            if len(result) >= k:  # Stop once we've collected 'K' numbers
                return result[:k]

        return result

# Example 1
nums = [1, 3, 5, 12, 11, 12, 11]
K = 2
result = TopKFrequentNumbers.find_top_k_frequent_numbers(nums, K)
print("Top K frequent numbers:", result)  # Output: [12, 11]

# Example 2
nums = [5, 12, 11, 3, 11]
K = 2
result = TopKFrequentNumbers.find_top_k_frequent_numbers(nums, K)
print("Top K frequent numbers:", result)  # Output: [11, 5] or [11, 12] or [11, 3]
