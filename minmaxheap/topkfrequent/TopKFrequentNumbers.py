from heapq import heappush, heappop

class TopKFrequentNumbers:
    def find_top_k_frequent_numbers(self, nums, k):
        # Step 1: Count the frequency of each number
        frequency_map = {}
        for num in nums:
            frequency_map[num] = frequency_map.get(num, 0) + 1

        # Step 2: Use a min-heap to keep track of top 'K' frequent numbers
        min_heap = []

        # Step 3: Add each number's frequency to the heap
        for num, freq in frequency_map.items():
            heappush(min_heap, (freq, num))
            if len(min_heap) > k:
                heappop(min_heap)  # remove the least frequent element

        # Step 4: Extract the top 'K' frequent numbers from the heap
        top_numbers = [heappop(min_heap)[1] for _ in range(len(min_heap))]

        return top_numbers

# Example usage
top_k_frequent = TopKFrequentNumbers()
result = top_k_frequent.find_top_k_frequent_numbers([1, 3, 5, 12, 11, 12, 11], 2)
print("Top K frequent numbers:", result)  # Output: [12, 11]

result = top_k_frequent.find_top_k_frequent_numbers([5, 12, 11, 3, 11], 2)
print("Top K frequent numbers:", result)  # Output: [11, 5] or [11, 12] or [11, 3]
