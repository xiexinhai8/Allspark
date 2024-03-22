package com.xiexinhai8.base_alg.bin_search;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * @author xxh
 * Created on 2024-03-22
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums == null || nums.length <= 0) {
            return result;
        }
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (nums[low] != target) {
            return result;
        }
        result[0] = low;

        low = 0;
        high = nums.length - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        result[1] = low;
        return result;
    }
}
