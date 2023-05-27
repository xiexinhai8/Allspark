package com.xiexinhai8.base_alg.sort;

import java.util.Arrays;

/**
 * 基础的统计信息 （此问题出于一个看错的题 【1093. 大样本统计】）
 * 1.min
 * 2.max
 * 3.mean
 * 4.median
 * 5.mode
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-27
 */
public class BaseStatisticInfo {

    public double[] sampleStats(int[] numbers) {
        /** 思路1: 排序
         1、 排序后 最小、最大值、中位数可以直接获取
         2、 平均值: 遍历进行计算
         3、 众数: 也在遍历中获取, 初始化众数为负数, 频数也为负数
         */
        Arrays.sort(numbers);
        double[] staticsInfo = new double[5];
        staticsInfo[0] = numbers[0];
        staticsInfo[1] = numbers[numbers.length - 1];

        int mode = -1;
        int modeCount = -1;

        int sum = 0;
        int curMode = numbers[0];
        int curModeCount = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == curMode) {
                curModeCount++;
            } else {
                if (curModeCount > modeCount) {
                    mode = curMode;
                    modeCount = curModeCount;

                    curMode = numbers[i];
                    curModeCount = 1;
                }
            }
            sum += numbers[i];
        }

        staticsInfo[2] = sum * 1.0 / numbers.length;
        int median = numbers[numbers.length / 2];
        if (numbers.length % 2 == 0) {
            median += numbers[numbers.length / 2 - 1];
        } else {
            median *= 2;
        }
        staticsInfo[3] = median / 2.0;
        staticsInfo[4] = mode;

        return staticsInfo;
    }
}
