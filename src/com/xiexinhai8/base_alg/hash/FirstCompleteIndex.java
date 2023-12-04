package com.xiexinhai8.base_alg.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 2661. 找出叠涂元素
 *
 * 给你一个下标从 0 开始的整数数组 arr 和一个 m x n 的整数 矩阵 mat 。arr 和 mat 都包含范围 [1，m * n] 内的 所有 整数。
 *
 * 从下标 0 开始遍历 arr 中的每个下标 i ，并将包含整数 arr[i] 的 mat 单元格涂色。
 *
 * 请你找出 arr 中第一个使得 mat 的某一行或某一列都被涂色的元素，并返回其下标 i 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-04
 */
public class FirstCompleteIndex {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, int[]> indexMap = new HashMap<>();

        for (int row = 0; row < mat.length; row++) {

            for (int col = 0;  col < mat[0].length; col++) {
                indexMap.put(mat[row][col], new int[]{row, col});
            }
        }

        int[] matRow = new int[mat.length];
        int[] matCol = new int[mat[0].length];

        for (int i = 0; i < arr.length; i++) {
            int[] tmpInfo = indexMap.get(arr[i]);
            int row = tmpInfo[0];
            int col = tmpInfo[1];
            matRow[row] += 1;
            matCol[col] += 1;

            if (matRow[row] >= mat[0].length || matCol[col] >= mat.length) {
                return i;
            }
        }

        return -1;
    }
}
