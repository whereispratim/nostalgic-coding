import heapq
from typing import List

class KthLargestNum:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        min_heap = []

        for num in nums:
            heapq.heappush(min_heap, num)

            # If the heap size exceeds k, remove the smallest element
            if len(min_heap) > k:
                heapq.heappop(min_heap)

        # The root of the heap is the k-th largest element
        return min_heap[0]

# Example usage
nums = [3, 2, 1, 5, 6, 4]
k = 2
solution = KthLargestNum()
result = solution.findKthLargest(nums, k)
print(f"The {k}th largest element is: {result}")
