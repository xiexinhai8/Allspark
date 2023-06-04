package com.xiexinhai8.base_alg.two_pointers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2465. 不同的平均值数目
 *
 * 给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。
 *
 * 只要 nums 不是 空数组，你就重复执行以下步骤：
 *
 * 找到 nums 中的最小值，并删除它。
 * 找到 nums 中的最大值，并删除它。
 * 计算删除两数的平均值。
 * 两数 a 和 b 的 平均值 为 (a + b) / 2 。
 *
 * 比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
 * 返回上述过程能得到的 不同 平均值的数目。
 *
 * 注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。
 * @author xxh
 * Created on 2023-06-04
 */
public class DistinctAverages {

    public int distinctAverages(int[] nums) {
        /** 思路: 排序 + 双指针
         1、 对nums数组进行排序
         2、采用首尾指针相向移动(结束: 首 > 尾)
         2.1 计算首尾和并和之前的收尾和比较, 如果不同则计数
         3、返回计数值
         */
        int distinctNums = 0;
        Set<Integer> preTotals = new HashSet<>();

        Arrays.sort(nums);

        int first = 0, end = nums.length - 1;
        while (first < end) {
            int total = nums[first++] + nums[end--];
            if (!preTotals.contains(total)) {
                preTotals.add(total);
                distinctNums++;
            }
        }

        return distinctNums;
    }
}
