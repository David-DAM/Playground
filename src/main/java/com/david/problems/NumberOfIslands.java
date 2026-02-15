package com.david.problems;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    static void main(String[] args) {
        int result = numIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 1, 1}
        });
        System.out.println("Result: " + result);
    }

    public static int numIslands(int[][] grid) {

        if (grid == null || grid.length == 0) return 0;

        int row = grid.length;
        int col = grid[0].length;
        int numberOfIslands = 0;
        for (int x = 0; x < row; x++) {

            for (int y = 0; y < col; y++) {

                if (grid[x][y] == 1) {
                    numberOfIslands++;
                    dfs(x, y, grid);
                    //bfs(x, y, grid);
                }

            }
        }

        return numberOfIslands;
    }

    private static void dfs(int x, int y, int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        if (x < 0 || x >= row || y < 0 || y >= col || grid[x][y] == 0) {
            return;
        }

        grid[x][y] = 0;

        //up
        dfs(x, y - 1, grid);
        //down
        dfs(x, y + 1, grid);
        //left
        dfs(x - 1, y, grid);
        //right
        dfs(x + 1, y, grid);

    }

    private static void bfs(int i, int j, int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();

        int rows = grid.length;
        int cols = grid[0].length;

        queue.add(new int[]{i, j});

        grid[i][j] = 0; // marcar visitado

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (x > 0 && grid[x - 1][y] == 1) {
                grid[x - 1][y] = 0;
                queue.add(new int[]{x - 1, y});
            }

            if (x < rows - 1 && grid[x + 1][y] == 1) {
                grid[x + 1][y] = 0;
                queue.add(new int[]{x + 1, y});
            }

            if (y > 0 && grid[x][y - 1] == 1) {
                grid[x][y - 1] = 0;
                queue.add(new int[]{x, y - 1});
            }

            if (y < cols - 1 && grid[x][y + 1] == 1) {
                grid[x][y + 1] = 0;
                queue.add(new int[]{x, y + 1});
            }
        }
    }


}
