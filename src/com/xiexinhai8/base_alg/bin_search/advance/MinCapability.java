package com.xiexinhai8.base_alg.bin_search.advance;

/**
 * 2560. 打家劫舍 IV
 *
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 *
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 *
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 *
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 *
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 *
 * 返回小偷的 最小 窃取能力。
 * @author xxh
 * Created on 2024-06-27
 */
public class MinCapability {

    /**
     * 二分法:
     * 衡量偷盗能力的指标是一次偷盗行为单个房间所能偷的最大金额; 所以偷的房间越多最小的偷盗能力越大, 反之越少
     *
     * 这里我们假设最小偷盗能力为x时最多能偷多少间房间
     * 如果偷盗的房间数 大于等于k, 说明最小偷盗能力可能可以继续减少
     * 如果偷到的房间数 小于k, 说明偷盗能力不行, 需要增加
     */
    public int minCapability(int[] nums, int k) {
        int start = (int)1e9, end = 1;
        for (int i = 0; i < nums.length; i++) {
            start = Math.min(start, nums[i]);
            end = Math.max(end, nums[i]);
        }

        while (start < end) {
            int mid = (start + end) / 2;

            int maxRobNum = findMaxRobNum(nums, mid, k);
            if (maxRobNum < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    /**
     * 如果限制窃取能力为robLimit, 最多能偷多少间房
     *      nums[i] > roblimit: f(i) = f(i - 1)
     *      nums[i] <= robLimit: f(i) = Math.max(f(i - 1), f(i - 2) + 1)
     */
    private int findMaxRobNum(int[] nums, int robLimit, int minRobNum) {
        int[] f = new int[nums.length + 1];
        f[0] = 0;
        f[1] = nums[0] > robLimit ?  0 : 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > robLimit) {
                f[i + 1] = f[i];
            } else {
                f[i + 1] = Math.max(f[i], f[i - 1] + 1);
            }
        }

        return f[nums.length];
    }
}
