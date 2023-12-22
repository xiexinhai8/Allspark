package com.xiexinhai8.base_alg.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * @author xxh
 * Created on 2023-12-22
 */
public class SpiralOrder {

    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        int curDir = 0;
        int[] cur = new int[]{0, 0};

        while (result.size() < (matrix.length * matrix[0].length)) {

            while (validCurLoc(cur[0], cur[1], matrix) && !visited[cur[0]][cur[1]]) {
                result.add(matrix[cur[0]][cur[1]]);
                visited[cur[0]][cur[1]] = true;
                cur[0] += dir[curDir][0];
                cur[1] += dir[curDir][1];
            }

            cur[0] -= dir[curDir][0];
            cur[1] -= dir[curDir][1];
            curDir += 1;
            curDir %= dir.length;
            cur[0] += dir[curDir][0];
            cur[1] += dir[curDir][1];
        }

        return result;
    }

    private boolean validCurLoc(int row, int col, int[][] matrix) {
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) {
            return false;
        }
        return true;
    }
}
