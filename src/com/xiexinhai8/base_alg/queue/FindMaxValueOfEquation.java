package com.xiexinhai8.base_alg.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1499. 满足不等式的最大值
 *
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points
 * .length 的前提下， xi < xj 总成立。
 *
 * 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 *
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 * @author xxh
 * Created on 2024-05-27
 */
public class FindMaxValueOfEquation {

    /**
     * 假设: xi > xj, 则 yi + yj + |xi - xj| = xi + yi + yj - xj
     * 对于固定的j, 我们需要找到符合条件最大的yj - xj;
     * 从上我们知道可以采用最大队列这一结构实现
     */
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<Integer> queue = new ArrayDeque<>();

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++) {
            while (!queue.isEmpty() && points[i][0] - points[queue.getFirst()][0] > k) {
                queue.removeFirst();
            }
            if (!queue.isEmpty()) {
                int maxIdx = queue.getFirst();
                maxValue = Math.max(maxValue, points[i][1] + points[i][0] + points[maxIdx][1] - points[maxIdx][0]);
            }
            while (!queue.isEmpty()
                    && points[i][1] - points[i][0] > points[queue.getLast()][1] - points[queue.getLast()][0]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }

        return maxValue;
    }
}
