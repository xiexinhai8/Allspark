package com.xiexinhai8.base_alg.two_pointers;

/**
 * 2460. 对数组执行操作
 *
 * 给你一个下标从 0 开始的数组 nums ，数组大小为 n ，且由 非负 整数组成。
 *
 * 你需要对数组执行 n - 1 步操作，其中第 i 步操作（从 0 开始计数）要求对 nums 中第 i 个元素执行下述指令：
 *
 * 如果 nums[i] == nums[i + 1] ，则 nums[i] 的值变成原来的 2 倍，nums[i + 1] 的值变成 0 。否则，跳过这步操作。
 * 在执行完 全部 操作后，将所有 0 移动 到数组的 末尾 。
 *
 * 例如，数组 [1,0,2,0,0,1] 将所有 0 移动到末尾后变为 [1,2,1,0,0,0] 。
 * 返回结果数组。
 *
 * 注意 操作应当 依次有序 执行，而不是一次性全部执行。
 * @author xxh
 * Created on 2023-06-05
 */
public class ApplyOperations {

    public int[] applyOperations(int[] nums) {
        /** 思路: 模拟操作
         1、模拟操作, 将数组进行转换, 注意: 模拟时从0开始, 到倒数第二位结束
         2、采用双指针 一个指向填充位置,一个指向遍历位置, 每次将遍历位置非0的值填充到填充位置, 同时双指针往后移, 直到所有遍历位置到达数组末尾, 遍历位置结束
         3、如果遍历位置结束, 填充位置开始填充0, 知道填充位置到达数组末尾
         4、返回数组
         */
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;
                nums[i + 1] = 0;
            }
        }

        int fill = 0, scan = 0;
        while (fill < nums.length) {
            if (scan < nums.length) {
                int scanValue = nums[scan++];
                if (scanValue != 0) {
                    nums[fill++] = scanValue;
                }
            } else {
                nums[fill++] = 0;
            }
        }

        return nums;
    }
}
