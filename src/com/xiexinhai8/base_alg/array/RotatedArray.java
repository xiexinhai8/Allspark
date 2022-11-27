package com.xiexinhai8.base_alg.array;

/**
 * 1752. 检查数组是否经排序和轮转得到
 *
 * 给你一个数组 nums 。nums 的源数组中，所有元素与 nums 相同，但按非递减顺序排列。
 *
 * 如果 nums 能够由源数组轮转若干位置（包括 0 个位置）得到，则返回 true ；否则，返回 false 。
 *
 * 源数组中可能存在 重复项 。
 * @author xxh
 * Created on 2022-11-28
 */
public class RotatedArray {

    public boolean check(int[] nums) {
        int diff = -1;
        int hit = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                diff = i;
                hit++;
            }
        }

        if (diff > 0 && (hit > 1 || nums[nums.length - 1] > nums[0])) {
            return false;
        }

        return true;
    }
}
