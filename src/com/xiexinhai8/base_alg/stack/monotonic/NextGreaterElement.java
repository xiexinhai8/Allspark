package com.xiexinhai8.base_alg.stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 *
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素
 * 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-12
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> queryMap = createQueryMap(nums2);

        int[] nextBigTable = createNextBigTable(nums2);

        int[] nextBigs = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int nums2Index = queryMap.get(nums1[i]);
            nextBigs[i] = nextBigTable[nums2Index];
        }

        return nextBigs;
    }

    private Map<Integer, Integer> createQueryMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        return map;
    }

    private int[] createNextBigTable(int[] nums) {
        int[] bigs = new int[nums.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int index = stack.pop();
                bigs[index] = nums[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            bigs[stack.pop()] = -1;
        }

        return bigs;
    }
}
