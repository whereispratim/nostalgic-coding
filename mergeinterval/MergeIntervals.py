class MergeIntervals:
    def merge(self, intervals):
        # Sort the intervals based on start time
        intervals.sort(key=lambda x: x[0])

        merged = []
        for interval in intervals:
            # If the list of merged intervals is empty or if the current
            # interval does not overlap with the previous, simply append it.
            if not merged or merged[-1][1] < interval[0]:
                merged.append(interval)
            else:
                # Otherwise, there is overlap, so we merge the current and previous
                # intervals.
                merged[-1][1] = max(merged[-1][1], interval[1])

        return merged

# Example usage
intervals = [[1,3],[2,6],[8,10],[15,18]]
solution = MergeIntervals()
result = solution.merge(intervals)
print(f"Merged intervals: {result}")