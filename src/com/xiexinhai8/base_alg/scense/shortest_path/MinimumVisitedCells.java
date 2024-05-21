package com.xiexinhai8.base_alg.scense.shortest_path;

import java.util.PriorityQueue;

/**
 * 2617. 网格图中最少访问的格子数
 *
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 *
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 *
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 * @author xxh
 * Created on 2024-05-21
 */
public class MinimumVisitedCells {

    /**
     * 本题其实就是一个最短路径问题:
     * 采用dijkstra最短路径法
     * 问题: 有一个case过不了
     */
    int[] colMax;
    int[] rowMax;

    public int minimumVisitedCells1(int[][] grid) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        boolean[][] step = new boolean[grid.length][grid[0].length];
        queue.offer(new int[] { 0, 0, 1 });
        step[0][0] = true;
        colMax = new int[grid[0].length];
        rowMax = new int[grid.length];
        colMax[0] = 0;
        rowMax[0] = 0;

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int posX = pair[0];
            int posY = pair[1];
            int dis = pair[2];

            /**
             * if (step[posX][posY]) {
             * continue;
             * }
             */

            if (posX == grid.length - 1 && posY == grid[0].length - 1) {
                return dis;
            }
            processCurPos(grid, queue, posX, posY, dis, step);
        }

        return -1;
    }

    private void processCurPos(int[][] grid, PriorityQueue<int[]> queue, int x, int y, int dis, boolean[][] step) {
        int adder = grid[x][y];
        if (adder == 0) {
            return;
        }

        for (int i = colMax[y] + 1; i <= x + adder && i < grid.length; i++) {
            if (step[i][y]) {
                continue;
            }
            queue.offer(new int[] { i, y, dis + 1 });
            step[i][y] = true;
            colMax[y] = Math.max(colMax[y], i);
        }

        for (int i = rowMax[x] + 1; i <= y + adder && i < grid[0].length; i++) {
            if (step[x][i]) {
                continue;
            }
            queue.offer(new int[] { x, i, dis + 1 });
            step[x][i] = true;
            rowMax[x] = Math.max(rowMax[x], i);
        }
    }
}
