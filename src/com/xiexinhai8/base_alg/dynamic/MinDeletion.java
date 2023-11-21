package com.xiexinhai8.base_alg.dynamic;

/**
 * 2216. 美化数组的最少删除数
 *
 * 给你一个下标从 0 开始的整数数组 nums ，如果满足下述条件，则认为数组 nums 是一个 美丽数组 ：
 *
 * nums.length 为偶数
 * 对所有满足 i % 2 == 0 的下标 i ，nums[i] != nums[i + 1] 均成立
 * 注意，空数组同样认为是美丽数组。
 *
 * 你可以从 nums 中删除任意数量的元素。当你删除一个元素时，被删除元素右侧的所有元素将会向左移动一个单位以填补空缺，而左侧的元素将会保持 不变 。
 *
 * 返回使 nums 变为美丽数组所需删除的 最少 元素数目。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-21
 */
public class MinDeletion {
    /**
     * f(k) = f(k - 2)          a(k) != a(k - 1)
     * f(k) = f(k - 1) + 1      a(k) == a(k - 1)
     *
     * 其中 f(0) = 1, f(1) = a(0) == a(1) ? 0 : 2;
     * @param nums
     * @return
     */
    public int minDeletion(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] f = new int[nums.length];
        f[0] = 1;
        f[1] = nums[1] != nums[0] ? 0 : 2;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                f[i] = f[i - 2];
            } else {
                f[i] = f[i - 1] + 1;
            }
        }

        return f[nums.length - 1];
    }
}
