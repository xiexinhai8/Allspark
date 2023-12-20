package com.xiexinhai8.base_alg.bin_search.advance;

/**
 * 1901. 寻找峰值 II
 *
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 *
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 *
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 *
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-20
 */
public class FindPeakGrid {

    public int[] findPeakGrid(int[][] mat) {
        int[] result = new int[2];
        int size = mat.length;

        int start = 0, end = size - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int[] maxLoc = findMaxLoc(mat, mid);
            if (bigThanMaxLoc(mat, maxLoc[0], maxLoc[1], maxLoc[0] - 1, maxLoc[1])) {
                end = mid - 1;
            } else if (bigThanMaxLoc(mat, maxLoc[0], maxLoc[1], maxLoc[0] + 1, maxLoc[1])) {
                start = mid + 1;
            } else {
                return maxLoc;
            }

        }

        return null;
    }

    private boolean bigThanMaxLoc(int[][] mat, int baseRow, int baseCol, int eRow, int eCol) {
        if (eRow < 0 || eRow >= mat.length) {
            return false;
        }
        return mat[eRow][eCol] > mat[baseRow][baseCol];
    }

    private int[] findMaxLoc(int[][] mat, int row) {
        int[] maxLocal = new int[2];
        maxLocal[0] = row;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < mat[row].length; i++) {
            if (mat[row][i] > maxValue) {
                maxLocal[1] = i;
            }
        }

        return maxLocal;
    }

}
