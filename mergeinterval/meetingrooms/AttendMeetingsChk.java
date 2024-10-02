package meetingrooms;

import java.util.Arrays;
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
        System.out.println(solution.canAttendMeetings(new int[][]{{7,10},{2,4}}));  // sorted to {2,4},{7,10} : true
    }
}
