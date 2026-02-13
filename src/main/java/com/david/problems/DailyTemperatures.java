package com.david.problems;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    static void main() {
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(temps);
        System.out.println(Arrays.toString(result));
    }

    public static int[] dailyTemperatures(int[] temps) {
        int n = temps.length;
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {
                int prev = stack.pop();
                res[prev] = i - prev;
            }
            stack.push(i);
        }
        return res;
    }
}

