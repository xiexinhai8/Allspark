package com.xiexinhai8.base_alg.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 1072. 按列翻转得到最大值等行数
 *
 * 给定 m x n 矩阵 matrix 。
 *
 * 你可以从中选出任意数量的列并翻转其上的 每个 单元格。（即翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。）
 *
 * 返回 经过一些翻转后，行中所有值都相等的行数 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-15
 */
public class MaxEqualRowsAfterFlips {

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        /** 思路1: 转换后相等行都是由之前不等行转换过来的
         1、统计不转换相等行数, 作为最大相等行数的初始值
         2、遍历所有不想等的行数, 反转列让其相等, 记录相等行号, 统计一共多少, 如果超过最大行数则替换
         */

        int maxEqualNum = 0;
        Set<Integer> doneSet = new HashSet<>();
        maxEqualNum = calMaxEqualRows(matrix, doneSet);

        while (doneSet.size() < matrix.length) {
            // 选择任意一列不想等数
            int targetRow = 0;
            for (int i = 0; i < matrix.length; i++) {
                if (!doneSet.contains(i)) {
                    targetRow = i;
                    break;
                }
            }
            // 变换
            int baseNum = matrix[targetRow][0];
            for (int i = 1; i < matrix[targetRow].length; i++) {
                if (matrix[targetRow][i] != baseNum) {
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[j][i] == 0) {
                            matrix[j][i] = 1;
                        } else {
                            matrix[j][i] = 0;
                        }
                    }
                }
            }

            // 计算变换后的相等列
            int equalNum = calMaxEqualRows(matrix, doneSet);
            if (equalNum > maxEqualNum) {
                maxEqualNum = equalNum;
            }
        }

        return maxEqualNum;
    }

    private int calMaxEqualRows(int[][] matrix, Set<Integer> doneSet) {
        int maxEqualNum = 0;
        for (int i = 0; i < matrix.length; i++) {
            boolean equal = true;
            int baseNum = matrix[i][0];
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != baseNum) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
                doneSet.add(i);
                maxEqualNum++;
            }
        }

        return maxEqualNum;
    }
}
