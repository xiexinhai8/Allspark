package com.xiexinhai8.base_alg.bin_search.advance;

import java.util.Arrays;

/**
 * 719. 找出第 K 小的数对距离
 *
 * 数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。
 *
 * 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。返回 所有数对距离中 第 k 小的数对距离。
 * @author xxh
 * Created on 2024-04-18
 */
public class SmallestDistancePair {

    /**
     两种方法:
     第一种: 采用大顶堆的方式
     保持堆大小为k, 如果元素小于堆顶,则出堆
     第二种: 二分查找
     先排序, 获取最大距离
     采用二分的方式探索固定距离下的对数
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0, end = nums[nums.length - 1] - nums[0];
        //print(nums);
        while (start < end) {
            int target = (start + end) / 2;

            int lowTargetNum = findLowTargetNum(target, nums);
            // System.out.println("target = " + target + " total = " + lowTargetNum);
            if (lowTargetNum < k) {
                start = target + 1;
            } else {
                end = target;
            }
        }

        return start;
    }

    private int findLowTargetNum(int target, int[] nums) {
        int total = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int start = i + 1, end = nums.length - 1;
            int curTarget = target + nums[i];
            if (nums[start] > curTarget) {
                continue;
            }
            while (start < end) {
                int mid = (start + end + 1) / 2;
                if (nums[mid] > curTarget) {
                    end = mid - 1;
                } else {
                    start = mid;
                }
            }
            total += start - i;
        }
        return total;
    }
}
