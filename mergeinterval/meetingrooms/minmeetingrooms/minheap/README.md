# Meeting Rooms II

## Problem Statement

Given an array of intervals `intervals` where each interval is represented as a pair `[start, end]`, find the minimum number of meeting rooms required.

## Examples

### Example 1:

Input: `[[0,30],[5,10],[15,20]]`
Output: `2`
Explanation: We need two meeting rooms because the first meeting overlaps with the second one.

### Example 2:

Input: `[[7,10],[2,4]]`
Output: `1`
Explanation: Only one meeting room is needed because there is no overlap.

## Approach

1. Sort the intervals based on start times.
2. Use a min-heap to keep track of the earliest ending time of allocated rooms.
3. Iterate through the sorted intervals:
    - If the current meeting starts after the earliest ending time, remove the top of the heap.
    - Add the current meeting's end time to the heap.
4. The size of the heap at the end represents the minimum number of rooms required.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
import heapq

class Solution:
def minMeetingRooms(self, meetings: List[List[int]]) -> int:
if not meetings:
return 0

        # Sort meetings by start time
        meetings.sort(key=lambda x: x[0])

        # Min heap to store end times of meetings
        rooms = []
        heapq.heappush(rooms, meetings[0][1])

        for meeting in meetings[1:]:
            if meeting[0] >= rooms[0]:
                # If the current meeting starts after the
                # earliest ending meeting, we can reuse that room
                heapq.heappop(rooms)
            
            # Add the current meeting's end time to the heap
            heapq.heappush(rooms, meeting[1])

        # The size of the heap is the minimum number of rooms required
        return len(rooms)

# Example usage
solution = Solution()
print(solution.minMeetingRooms([[0,30],[5,10],[15,20]]))  # 2
print(solution.minMeetingRooms([[7,10],[2,4]]))  # 1
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class Solution {
public int minMeetingRooms(int[][] meetings) {
if (meetings == null || meetings.length == 0) {
return 0;
}

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Min heap to store end times of meetings
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.offer(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] >= rooms.peek()) {
                // If the current meeting starts after the
                // earliest ending meeting, we can reuse that room
                rooms.poll();
            }
            
            // Add the current meeting's end time to the heap
            rooms.offer(meetings[i][1]);
        }

        // The size of the heap is the minimum number of rooms required
        return rooms.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));  // 2
        System.out.println(solution.minMeetingRooms(new int[][]{{7,10},{2,4}}));  // 1
    }
}
</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a min-heap to keep track of the end times of meetings currently in progress. Here's how it works:

1. We start by sorting the meetings based on their start times. This allows us to process meetings in chronological order.

2. We initialize a min-heap with the end time of the first meeting.

3. For each subsequent meeting:
    - We check if its start time is greater than or equal to the earliest end time in our heap.
    - If it is, we can reuse that room, so we remove the earliest end time from the heap.
    - We then add the end time of the current meeting to the heap.

4. The size of the heap at the end represents the minimum number of rooms required.

## Step-by-Step Iteration Walkthrough with Output

### Example 1: `int[][] meetings = {{0, 30}, {5, 10}, {15, 20}}`

Input Intervals: `[[0, 30], [5, 10], [15, 20]]`

#### Step 1: Sort meetings by start time
Sorted meetings: `[[0, 30], [5, 10], [15, 20]]` (already sorted)

#### Step 2: Initialize min-heap
- Add end time of first meeting: heap = `[30]`

#### Iteration Walkthrough:

**Iteration 1 (meeting [5, 10]):**
- Current meeting: `[5, 10]`
- Heap: `[30]`
- 5 < 30, so we need a new room
- Add 10 to the heap
- Heap after iteration: `[10, 30]`

**Iteration 2 (meeting [15, 20]):**
- Current meeting: `[15, 20]`
- Heap: `[10, 30]`
- 15 > 10, so we can reuse a room
- Remove 10 from the heap
- Add 20 to the heap
- Heap after iteration: `[20, 30]`

#### Output for Example 1:
The minimum number of rooms required is 2 (size of the heap).

### Example 2: `int[][] meetings = {{7, 10}, {2, 4}}`

Input Intervals: `[[7, 10], [2, 4]]`

#### Step 1: Sort meetings by start time
Sorted meetings: `[[2, 4], [7, 10]]`

#### Step 2: Initialize min-heap
- Add end time of first meeting: heap = `[4]`

#### Iteration Walkthrough:

**Iteration 1 (meeting [7, 10]):**
- Current meeting: `[7, 10]`
- Heap: `[4]`
- 7 > 4, so we can reuse a room
- Remove 4 from the heap
- Add 10 to the heap
- Heap after iteration: `[10]`

#### Output for Example 2:
The minimum number of rooms required is 1 (size of the heap).

## Complexity Analysis

- Time Complexity: O(n log n), where n is the number of meetings. This is due to the sorting step and heap operations.
- Space Complexity: O(n) in the worst case, where we might need to store all meetings in the heap.

## LeetCode Link

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)