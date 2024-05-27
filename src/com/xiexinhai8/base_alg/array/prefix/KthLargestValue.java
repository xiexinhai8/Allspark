package com.xiexinhai8.base_alg.array.prefix;

import java.util.PriorityQueue;

/**
 * 1738. 找出第 K 大的异或坐标值
 *
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * @author xxh
 * Created on 2024-05-27
 */
public class KthLargestValue {
    /**
     * 二维数组前缀和
     * newMat[i][j] = newMat[i - 1][j] ^ newMat[i][j - 1] ^ newMat[i][j] ^
     * matrix[i][j]
     */
    public int kthLargestValue(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (a - b));
        int[][] newMat = new int[matrix.length + 1][matrix[0].length + 1];

        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                newMat[i][j] = newMat[i - 1][j] ^ newMat[i][j - 1] ^ newMat[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                queue.offer(newMat[i][j]);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.poll();
    }
}
