package com.david.problems;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPoints {

    static void main() {
        int[][] points = {{1, 3}, {-2, 2}};
        int k = 1;
        int[][] result = kClosest(points, k);
        System.out.println(Arrays.deepToString(result));
    }

    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(
                        b[0] * b[0] + b[1] * b[1],
                        a[0] * a[0] + a[1] * a[1]
                )
        );

        for (int[] p : points) {
            heap.offer(p);
            if (heap.size() > k) heap.poll();
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++)
            res[i] = heap.poll();

        return res;
    }
}

