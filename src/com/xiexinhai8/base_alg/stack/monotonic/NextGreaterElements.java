package com.xiexinhai8.base_alg.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * 503. 下一个更大元素 II
 *
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-12
 */
public class NextGreaterElements {

    public int[] nextGreaterElements(int[] nums) {
        int[] nextBig = new int[nums.length];
        Arrays.setAll(nextBig, a -> -1);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length * 2; i++) {
            int curIndex = i % nums.length;
            if (stack.isEmpty()) {
                stack.push(curIndex);
                continue;
            }

            while(!stack.isEmpty() && nums[stack.peek()] < nums[curIndex]) {
                int little = stack.pop();
                nextBig[little] = nums[curIndex];
            }

            stack.push(curIndex);
        }

        return nextBig;
    }
}
