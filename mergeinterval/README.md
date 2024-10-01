# Merge Intervals

## Problem Description

Given an array of intervals where `intervals[i] = [starti, endi]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

## Solution

<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
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

intervals = [[1,3],[2,6],[8,10],[15,18]]
solution = MergeIntervals()
result = solution.merge(intervals)
print(f"Merged intervals: {result}")
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class MergeIntervals {
public int[][] merge(int[][] intervals) {
// Sort the intervals based on start time
Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // If the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            // currentEnd = merged.getLast()[1];
            // nextStart = interval[0];
            // nextEnd = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) { // merged.getLast()[1]
                merged.add(interval);
            }
            // Otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                //currentEnd > nextStart
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals solution = new MergeIntervals();
        int[][] result = solution.merge(intervals);
        System.out.println("Merged intervals: " + Arrays.deepToString(result));
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution efficiently merges overlapping intervals using a sorting-based approach.

### How It Works (Step-by-Step for Both Java and Python):

Consider the intervals: [[1,3],[2,6],[8,10],[15,18]]

1. Sort the intervals based on start time:
    - Sorted intervals: [[1,3],[2,6],[8,10],[15,18]]

2. Iterate through the sorted intervals:
    - Start with [1,3]
    - [2,6] overlaps with [1,3], merge to [1,6]
    - [8,10] doesn't overlap with [1,6], add it
    - [15,18] doesn't overlap with [8,10], add it

3. Final result:
    - Merged intervals: [[1,6],[8,10],[15,18]]

The algorithm works as follows:
1. Sort the intervals based on their start time.
2. Iterate through the sorted intervals:
    - If the result list is empty or if the current interval doesn't overlap with the previous interval, add it to the result list.
    - If there is an overlap, merge the current interval with the previous one by updating the end time of the previous interval.

## Complexity Analysis

- Time Complexity: O(n log n), where n is the number of intervals. This is due to the sorting step.
- Space Complexity: O(n) to store the sorted intervals and the output.

## LeetCode Link

[56. Merge Intervals](https://leetcode.com/problems/merge-intervals/)