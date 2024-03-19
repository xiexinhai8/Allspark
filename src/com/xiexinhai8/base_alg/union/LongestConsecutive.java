package com.xiexinhai8.base_alg.union;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 *
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * @author xxh
 * Created on 2024-03-19
 */
public class LongestConsecutive {

    /**
     * 核心思想： 每次统计我们都只从最小的元素开始， 这样可以避免重复计算
     * 方法：
     *  1. 将数组的元素进行hash映映射
     *  2. 依次便利数组，找到当前元素-1的元素， 如果找不到则开始便利统计
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i] - 1)) {
                continue;
            }
            int len = 1;
            int val = nums[i] + 1;
            while (set.contains(val)) {
                len++;
                val++;
            }
            if (len > maxLen) {
                maxLen = len;
            }
        }

        return maxLen;
    }

    /**
     * 先排序， 排序后依次便利统计最长的连续序列
     * @param nums
     * @return
     */
    public int longestConsecutive1(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxLen = 1;

        int curLen = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                curLen++;
            } else if (nums[i] == nums[i - 1]) {
                continue;
            } else {
                curLen = 1;
            }
            if (maxLen < curLen) {
                maxLen = curLen;
            }
        }
        return maxLen;
    }
}
