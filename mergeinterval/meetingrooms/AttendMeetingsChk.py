class AttendMeetingsChk:
    def can_attend_meetings(self, intervals):
        # Sort intervals by start time
        intervals.sort(key=lambda x: x[0])

        # Check for overlaps
        for i in range(1, len(intervals)):
            if intervals[i][0] < intervals[i-1][1]:
                return False

        return True

# Testing the code
if __name__ == "__main__":
    solution = AttendMeetingsChk()
    print(solution.can_attend_meetings([[0, 30], [5, 10], [15, 20]]))  # Output: False
    print(solution.can_attend_meetings([[7, 10], [2, 4]]))  # Output: True
