package com.xiexinhai8.base_alg.dynamic;

/**
 * LCR 099. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：一个机器人每次只能向下或者向右移动一步。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-11
 */
public class MinPathSum {

    /**
     f[x][y] = min{f[x][y - 1], f[x - 1][y]} + path[x][y]};
     */
    public int minPathSum(int[][] path) {
        int[][] f = new int[path.length][path[0].length];

        for (int row = 0; row < path.length; row++) {

            for (int col = 0; col < path[0].length; col++) {
                f[row][col] = getPrePathSum(row, col, f) + path[row][col];
            }
        }

        return f[path.length - 1][path[0].length - 1];
    }

    private int getPrePathSum(int row, int col, int[][] f) {
        int value = Math.min(getPathSum(row - 1, col, f), getPathSum(row, col - 1, f));
        if (value == Integer.MAX_VALUE) {
            value = 0;
        }

        return value;
    }

    private int getPathSum(int row, int col, int[][] f) {
        if (row < 0 || col < 0) {
            return Integer.MAX_VALUE;
        }
        return f[row][col];
    }
}
