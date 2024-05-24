package com.xiexinhai8.base_alg.array.window;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1673. 找出最具竞争力的子序列
 *
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 *
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 *
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5]
 * 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 * @author xxh
 * Created on 2024-05-24
 */
public class MostCompetitive {

    /**
     * 最小队列
     * 方法: 用一个固定窗口维护一个最小队列, 每次拿到一个结果, 往右挪一步
     */
    public int[] mostCompetitive(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();

        int[] result = new int[k];
        // 建立一个最小队列, 窗口大小为nums.length - k + 1
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && queue.getLast() > nums[i]) {
                queue.removeLast();
            }
            queue.addLast(nums[i]);
            // 当当前大于窗口大小w(nums.length - k + 1), 所以i=w - 1;
            if (i >= nums.length - k) {
                result[i - nums.length + k] = queue.getFirst();
                // 拿到当前窗口最小值后弹出最小值
                queue.removeFirst();
            }
        }

        return result;
    }
}
