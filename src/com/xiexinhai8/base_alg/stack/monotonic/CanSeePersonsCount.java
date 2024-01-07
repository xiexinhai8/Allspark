package com.xiexinhai8.base_alg.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1944. 队列中可以看到的人数
 *
 * 有 n 个人排成一个队列，从左到右 编号为 0 到 n - 1 。给你以一个整数数组 heights ，每个整数 互不相同，heights[i] 表示第 i 个人的高度。
 *
 * 一个人能 看到 他右边另一个人的条件是这两人之间的所有人都比他们两人 矮 。更正式的，第 i 个人能看到第 j 个人的条件是 i < j 且 min(heights[i], heights[j]) > max
 * (heights[i+1], heights[i+2], ..., heights[j-1]) 。
 *
 * 请你返回一个长度为 n 的数组 answer ，其中 answer[i] 是第 i 个人在他右侧队列中能 看到 的 人数 。
 *
 * @author xxh
 * Created on 2024-01-08
 */
public class CanSeePersonsCount {

    public int[] canSeePersonsCount(int[] heights) {
        int[] result = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = heights.length - 1; i >= 0; i--) {
            int dis = 0;
            while (!stack.isEmpty() && heights[i] > heights[stack.peek()]) {
                dis++;
                stack.pop();
            }

            if (!stack.isEmpty()) {
                dis++;
            }
            result[i] = dis;
            stack.push(i);
        }

        return result;
    }
}
