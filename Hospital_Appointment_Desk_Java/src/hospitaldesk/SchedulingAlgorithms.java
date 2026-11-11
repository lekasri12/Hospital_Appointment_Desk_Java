package hospitaldesk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchedulingAlgorithms {
    public static class TimeSlot {
        public final int start;
        public final int end;

        public TimeSlot(int start, int end) {
            if (end <= start) throw new IllegalArgumentException("End must be after start");
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() { return "(" + start + ", " + end + ")"; }
    }

    public static class ScheduleResult {
        public final List<TimeSlot> slots;
        public ScheduleResult(List<TimeSlot> slots) {
            this.slots = slots;
        }
        public int count() { return slots.size(); }
        @Override
        public String toString() { return "count=" + count() + ", slots=" + slots; }
    }

    private static List<TimeSlot> sortedByEnd(List<TimeSlot> slots) {
        List<TimeSlot> sorted = new ArrayList<>(slots);
        sorted.sort(Comparator.comparingInt((TimeSlot s) -> s.end)
                              .thenComparingInt(s -> s.start));
        return sorted;
    }

    // DP recurrence: dp[i] = max(dp[i-1], 1 + dp[p(i)]).
    // Each appointment has equal value 1; touching endpoints do not overlap.
    public static ScheduleResult maximumAppointmentsDP(List<TimeSlot> input) {
        List<TimeSlot> slots = sortedByEnd(input);
        int n = slots.size();
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) ends[i] = slots.get(i).end;

        int[] previous = new int[n];
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            previous[i] = upperBound(ends, i, slots.get(i).start);
            int take = 1 + dp[previous[i]];
            int skip = dp[i];
            dp[i + 1] = Math.max(take, skip);
        }

        List<TimeSlot> chosen = new ArrayList<>();
        int i = n;
        while (i > 0) {
            int take = 1 + dp[previous[i - 1]];
            if (take > dp[i - 1]) {
                chosen.add(slots.get(i - 1));
                i = previous[i - 1];
            } else {
                i--;
            }
        }
        java.util.Collections.reverse(chosen);
        return new ScheduleResult(chosen);
    }

    // Count of end-times <= start among indexes [0, endExclusive).
    private static int upperBound(int[] ends, int endExclusive, int target) {
        int low = 0, high = endExclusive;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (ends[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // Greedy comparison: repeatedly choose the compatible earliest-finishing slot.
    public static ScheduleResult maximumAppointmentsGreedy(List<TimeSlot> input) {
        List<TimeSlot> slots = sortedByEnd(input);
        List<TimeSlot> chosen = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;

        for (TimeSlot slot : slots) {
            if (slot.start >= lastEnd) {
                chosen.add(slot);
                lastEnd = slot.end;
            }
        }
        return new ScheduleResult(chosen);
    }
}
