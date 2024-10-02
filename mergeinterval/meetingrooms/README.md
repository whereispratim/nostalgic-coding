# Meeting Rooms

## Problem Statement

Given an array of intervals `intervals` where each interval is represented as a pair `[start, end]`, determine if a person can attend all meetings (i.e., no two meetings overlap).

## Examples

### Example 1:

Input: `[[0,30],[5,10],[15,20]]`
Output: `false`
Explanation: The person cannot attend all meetings because the first meeting [0,30] overlaps with the second [5,10] and third [15,20].

### Example 2:

Input: `[[7,10],[2,4]]`
Output: `true`
Explanation: There is no overlap between the meetings.

## Approach

1. Sort the intervals based on their start times.
2. Iterate through the sorted intervals and check if any interval overlaps with the previous one.
3. If any overlap is found, return false. Otherwise, return true.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class AttendMeetingsChk:
    def can_attend_meetings(self, intervals):
        # Sort intervals by start time
        intervals.sort(key=lambda x: x[0])

        # Check for overlaps
        for i in range(1, len(intervals)):
            if intervals[i][0] < intervals[i-1][1]:
                return False

        return True

if __name__ == "__main__":
solution = AttendMeetingsChk()
print(solution.can_attend_meetings([[0, 30], [5, 10], [15, 20]]))  # Output: False
print(solution.can_attend_meetings([[7, 10], [2, 4]]))  # Output: True

</code></pre>
</td>
<td>
<pre><code class="java">
mport java.util.Arrays;
class AttendMeetingsChk {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        AttendMeetingsChk solution = new AttendMeetingsChk();
        System.out.println(solution.canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));  // false
        System.out.println(solution.canAttendMeetings(new int[][]{{7,10},{2,4}}));  // true
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

1. We start by sorting the intervals based on their start times. This allows us to compare each interval only with the previous one.

2. After sorting, we iterate through the intervals starting from the second one (index 1).

3. For each interval, we check if its start time is less than the end time of the previous interval. If so, we have found an overlap, and we return false.

4. If we complete the iteration without finding any overlaps, we return true.

## Complexity Analysis

- Time Complexity: O(n log n), where n is the number of intervals. This is due to the sorting step.
- Space Complexity: O(1) if we're allowed to modify the input array, O(n) if we need to create a copy for sorting.

## LeetCode Link

[252. Meeting Rooms](https://leetcode.com/problems/meeting-rooms/)