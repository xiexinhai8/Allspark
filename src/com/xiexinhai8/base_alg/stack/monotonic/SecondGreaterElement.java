package com.xiexinhai8.base_alg.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * TODO 2454. 下一个更大元素 IV
 *
 * 给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。
 *
 * 如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：
 *
 * j > i
 * nums[j] > nums[i]
 * 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
 * 如果不存在 nums[j] ，那么第二大整数为 -1 。
 *
 * 比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
 * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-12
 */
public class SecondGreaterElement {

    public int[] secondGreaterElement(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] ans = new int[nums.length];
        // Arrays.setAll(ans, a -> -1);
        Arrays.fill(ans, -1);

        for (int i = 0; i < nums.length; i++) {

            while (!queue.isEmpty() && nums[queue.peek()[0]] < nums[i]) {
                int[] index = queue.poll();
                ans[index[0]] = nums[i];
            }

            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                queue.offer(new int[]{index, nums[index]});
            }

            stack.push(i);
        }

        return ans;
    }

}
