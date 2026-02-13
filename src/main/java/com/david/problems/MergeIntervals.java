package com.david.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class MergeIntervals {

    static void main() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = mergeIntervals(intervals);
        System.out.println(Arrays.deepToString(result));
    }

    public static int[][] mergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] top = stack.peek();
            if (intervals[i][0] <= top[1]) {
                top[1] = Math.max(top[1], intervals[i][1]);
            } else {
                stack.push(intervals[i]);
            }
        }

        return stack.toArray(new int[stack.size()][]);
    }
}

