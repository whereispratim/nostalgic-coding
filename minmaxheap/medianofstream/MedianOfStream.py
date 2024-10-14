import heapq

class MedianOfStream:
    def __init__(self):
        self.small = []  # max heap
        self.large = []  # min heap

    def insertNum(self, num: int) -> None:
        if len(self.small) == len(self.large):
            heapq.heappush(self.large, -heapq.heappushpop(self.small, -num))
        else:
            heapq.heappush(self.small, -heapq.heappushpop(self.large, num))

    def findMedian(self) -> float:
        if len(self.small) == len(self.large):
            return (-self.small[0] + self.large[0]) / 2.0
        else:
            return float(self.large[0])

# Example usage
medianFinder = MedianOfStream()
medianFinder.insertNum(3)
medianFinder.insertNum(1)
print(medianFinder.findMedian())  # Output: 2.0
medianFinder.insertNum(5)
print(medianFinder.findMedian())  # Output: 3.0
medianFinder.insertNum(4)
print(medianFinder.findMedian())  # Output: 3.5