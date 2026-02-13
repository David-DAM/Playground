package com.david.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    static void main() {
        int result = minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}});
        System.out.println("Result: " + result);
    }


    public static int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }
            heap.offer(intervals[i][1]);
        }

        return heap.size();
    }
}

