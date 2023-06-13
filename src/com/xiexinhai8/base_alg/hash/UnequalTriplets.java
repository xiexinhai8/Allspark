package com.xiexinhai8.base_alg.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 2475. 数组中不等三元组的数目
 *
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 *
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-06-13
 */
public class UnequalTriplets {
    /**
     * 更好的解法参考： https://leetcode.cn/problems/number-of-unequal-triplets-in-array/solutions/1981413/fei-bao-li-zuo-fa-by-endlesscheng-9ekp/
     */

    /**
     * 暴力
     * @param nums
     * @return
     */
    public int unequalTriplets(int[] nums) {
        int counter = 0;

        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < nums.length; i++) {
            //Set<Integer> set = new HashSet<>();
            set.add(nums[i]);
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(nums[j])) {
                    continue;
                }
                set.add(nums[j]);
                for (int k = j + 1; k < nums.length; k++) {
                    if (set.contains(nums[k])) {
                        continue;
                    }
                    counter++;
                }
                set.remove(nums[j]);
            }
            set.remove(nums[i]);
        }

        return counter;
    }
}
