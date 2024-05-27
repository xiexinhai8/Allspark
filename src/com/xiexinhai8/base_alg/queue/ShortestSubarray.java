package com.xiexinhai8.base_alg.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 862. 和至少为 K 的最短子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * 子数组 是数组中 连续 的一部分。
 * @author xxh
 * Created on 2024-05-27
 */
public class ShortestSubarray {

    /**
     * 数组中有负数, 导致无法使用滑动窗口解决问题, 参考 209. 长度最小的子数组
     * 最小队列
     * nums[i, j]中元素和为prefix[j + 1] - prefix[i];
     * 以j结尾的最短子数组为prefix[j + 1] - min{prefix[x]} 其中 x < j; 另外如果x作为j的最短数组开始位置
     * 假如后面的prefix[k] > prefix[j], x不可能是题目答案
     * 假如后面的prefix[k] < prefix[j], x也不可能是题目答案
     */
    public int shortestSubarray(int[] nums, int k) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int minLen = Integer.MAX_VALUE;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < prefix.length; i++) {
            while (!queue.isEmpty() && prefix[i] - prefix[queue.getFirst()] >= k) {
                int idx = queue.removeFirst();
                minLen = Math.min(minLen, i - idx);
            }
            while (!queue.isEmpty() && prefix[queue.getLast()] >= prefix[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}
