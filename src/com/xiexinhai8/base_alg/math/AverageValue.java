package com.xiexinhai8.base_alg.math;

/**
 * 2455. 可被三整除的偶数的平均值
 *
 * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
 *
 * 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-29
 */
public class AverageValue {

    public int averageValue(int[] nums) {
        /** 思路:直接计算
         1、配置两个值sum 和 counter
         2、定义一个函数, 验证数字是否合法
         3、求平均值
         */

        int sum = 0;
        int counter = 0;
        for(int num : nums) {
            if (validateNum(num)) {
                sum += num;
                counter++;
            }
        }

        return counter > 0 ? sum / counter : 0;
    }

    private boolean validateNum(int num) {
        return num % 3 == 0 && num % 2 == 0;
    }
}
