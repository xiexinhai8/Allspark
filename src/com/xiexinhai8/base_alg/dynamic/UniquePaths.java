package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-07-17
 */
public class UniquePaths {

    public static void main(String[] args) {
        uniquePaths(3, 7);
    }

    public static int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {
                d[i][j] = calPaths(m, n, d);
            }
        }
        return d[m - 1][n - 1];

    }

    private static int calPaths(int row, int col, int[][] d) {
        if (row < 0 || col < 0) {
            return 0;
        }
        if (row == 0 && col == 0) {
            return 1;
        }
        return calPaths(row - 1, col, d) + calPaths(row, col - 1, d);
    }
}
