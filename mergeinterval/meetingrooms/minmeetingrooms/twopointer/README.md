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

-No overlap -> room++, overlap -> room-- 
1. Separate the start times and end times into two separate arrays.
2. Sort both arrays.
3. Use two pointers to iterate through the sorted arrays.
4. If a start time is less than or equal to an end time, increment the room count and move the start pointer.
5. Otherwise, decrement the room count and move the end pointer.
6. Keep track of the maximum room count encountered.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class MinMeetingRooms:
    def __init__(self, intervals):
        self.intervals = intervals

    def minMeetingRooms(self):
        if not self.intervals:
            return 0

        # Step 1: Separate start and end times
        start_times = sorted([interval[0] for interval in self.intervals])
        end_times = sorted([interval[1] for interval in self.intervals])

        # Step 2: Use two pointers to count rooms
        start_pointer = 0
        end_pointer = 0
        rooms_required = 0
        max_rooms = 0

        while start_pointer < len(self.intervals):
            if start_times[start_pointer] < end_times[end_pointer]:
                # A new meeting is starting before the last one ends, need a new room
                rooms_required += 1
                start_pointer += 1
            else:
                # A meeting has ended, free up a room
                rooms_required -= 1
                end_pointer += 1

            # Track the maximum number of rooms required
            max_rooms = max(max_rooms, rooms_required)

        return max_rooms

meeting_rooms_solver = MinMeetingRooms([[0, 30], [5, 10], [15, 20]])
print(meeting_rooms_solver.minMeetingRooms())  # Output: 2

meeting_rooms_solver_2 = MinMeetingRooms([[7, 10], [2, 4]])
print(meeting_rooms_solver_2.minMeetingRooms())  # Output: 1
</code></pre>
</td>
<td>
<pre><code class="java">
class MinMeetingRooms {
    public int minMeetingRooms(int[][] meetings) {
        int totalMeetings = meetings.length;
        // Step 1: Extract start and end times of meetings
        int[] startTimes = new int[totalMeetings];
        int[] endTimes = new int[totalMeetings];

        for (int i = 0; i < totalMeetings; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1];
        }

        // Step 2: Sort both start and end times
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        // Step 3: Initialize two pointers for start and end times
        int currentRooms = 0;
        int maxRoomsNeeded = 0;
        int startPointer = 0;
        int endPointer = 0;

        // Step 4: Traverse through all meetings using startPointer
        while (startPointer < totalMeetings) {
            // If there's a meeting that has started before the previous one ends, allocate a new room
            if (startTimes[startPointer] < endTimes[endPointer]) {
                currentRooms++;
                startPointer++;
            } else {
                // Otherwise, a meeting has ended and we can free a room
                currentRooms--;
                endPointer++;
            }

            // Keep track of the maximum number of rooms required at any point
            maxRoomsNeeded = Math.max(maxRoomsNeeded, currentRooms);
        }

        return maxRoomsNeeded;
    }

    public static void main(String[] args) {
        MinMeetingRooms solution = new MinMeetingRooms();
        System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));  // 2
        System.out.println(solution.minMeetingRooms(new int[][]{{7,10},{2,4}}));  // 1
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

1. We separate the start times and end times into two separate arrays and sort them.

2. We use two pointers, one for start times (`startPointer`) and one for end times (`endPointer`).

3. We iterate through the arrays:
    - If a start time is less than an end time, it means a new meeting is starting before the earliest ending meeting has finished. So we increment the `currentRooms` count and move the `startPointer`.
    - If a start time is greater than or equal to an end time, it means a meeting has ended before the next one starts. So we decrement the `currentRooms` count and move the `endPointer`.

4. We keep track of the maximum number of rooms needed (`maxRoomsNeeded`) at any point.

5. At the end, we return the maximum number of rooms needed.


## Step-by-Step Iteration Walkthrough with Output

Let's walk through the examples and explain how each iteration works.

### Example 1: `int[][] meetings = {{0, 30}, {5, 10}, {15, 20}}`

Input Intervals: `[[0, 30], [5, 10], [15, 20]]`

#### Step 1: Extract start and end times
- Start times: `[0, 5, 15]`
- End times: `[10, 20, 30]`

#### Step 2: Sort the start and end times
- Start times: `[0, 5, 15]` (Already sorted)
- End times: `[10, 20, 30]` (Already sorted)

#### Step 3: Initialize two pointers and variables
- `startPointer = 0`, `endPointer = 0`
- `currentRooms = 0`, `maxRoomsNeeded = 0`

#### Iteration Walkthrough:

**Iteration 1:**
- Current `startPointer` points to 0 (first meeting starts at time 0).
- Current `endPointer` points to 10 (first meeting ends at time 10).
- Since the current start time 0 is less than the current end time 10, a new room is required.
- Update:
    - `currentRooms = 1`
    - `maxRoomsNeeded = 1`
- Increment `startPointer` to 1.

**Iteration 2:**
- Current `startPointer` points to 5 (second meeting starts at time 5).
- Current `endPointer` still points to 10 (first meeting ends at time 10).
- Since the current start time 5 is less than the current end time 10, another room is required.
- Update:
    - `currentRooms = 2`
    - `maxRoomsNeeded = 2` (new max, because we are now using 2 rooms)
- Increment `startPointer` to 2.

**Iteration 3:**
- Current `startPointer` points to 15 (third meeting starts at time 15).
- Current `endPointer` still points to 10 (first meeting ends at time 10).
- Since the current start time 15 is greater than or equal to the current end time 10, a room becomes available.
- Free up one room by decrementing `currentRooms`.
- Update:
    - `currentRooms = 1`
- Increment `endPointer` to 1 (move to the next meeting's end time).

**Iteration 4:**
- Current `startPointer` points to 15 (third meeting starts at time 15).
- Current `endPointer` points to 20 (second meeting ends at time 20).
- Since the current start time 15 is less than the current end time 20, we need another room.
- Update:
    - `currentRooms = 2`
- Increment `startPointer` to 3 (all meetings processed).

**End of Iteration:**
- At the end of the loop, `maxRoomsNeeded = 2`.

#### Output for Example 1:
The minimum number of rooms required is 2.

```
Minimum rooms required: 2
```
## Example 2

[Previous content remains the same]

## Step-by-Step Iteration Walkthrough with Output

[Example 1 walkthrough remains the same]

### Example 2: `int[][] meetings = {{7, 10}, {2, 4}}`

Input Intervals: `[[7, 10], [2, 4]]`

#### Step 1: Extract start and end times
- Start times: `[7, 2]`
- End times: `[10, 4]`

#### Step 2: Sort the start and end times
- Start times: `[2, 7]`
- End times: `[4, 10]`

#### Step 3: Initialize two pointers and variables
- `startPointer = 0`, `endPointer = 0`
- `currentRooms = 0`, `maxRoomsNeeded = 0`

#### Iteration Walkthrough:

**Iteration 1:**
- Current `startPointer` points to 2 (first meeting starts at time 2).
- Current `endPointer` points to 4 (first meeting ends at time 4).
- Since the current start time 2 is less than the current end time 4, a new room is required.
- Update:
    - `currentRooms = 1`
    - `maxRoomsNeeded = 1`
- Increment `startPointer` to 1.

**Iteration 2:**
- Current `startPointer` points to 7 (second meeting starts at time 7).
- Current `endPointer` points to 4 (first meeting ends at time 4).
- Since the current start time 7 is greater than or equal to the current end time 4, a room becomes available.
- Free up one room by decrementing `currentRooms`.
- Update:
    - `currentRooms = 0`
- Increment `endPointer` to 1.

**Iteration 3:**
- Current `startPointer` points to 7 (second meeting starts at time 7).
- Current `endPointer` points to 10 (second meeting ends at time 10).
- Since the current start time 7 is less than the current end time 10, we need another room.
- Update:
    - `currentRooms = 1`
- Increment `startPointer` to 2 (all meetings processed).

#### Output for Example 2:
The minimum number of rooms required is 1.

```
Minimum rooms required: 1
```
## Complexity Analysis

- Time Complexity: O(n log n), where n is the number of meetings. This is due to the sorting step.
- Space Complexity: O(n) to store the separate start and end time arrays.

## LeetCode Link

[253. Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/)