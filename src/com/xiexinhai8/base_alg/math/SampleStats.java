package com.xiexinhai8.base_alg.math;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-27
 */
public class SampleStats {

    public static void main(String[] args) {
        int[] count = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        for (double x : new SampleStats().sampleStats(count)) {
            System.out.print(x + ", ");
        }

    }

    public double[] sampleStats(int[] count) {
        /** 思路1: 排序
         1、 最小、最大值在最前和最后
         2、 平均值: 遍历进行计算 sum / total
         3、 中位数: 先统计样本数, 后计算位置, 通过位置计算中位数
         3、 众数: 频次最高的数
         */
        double[] statisticInfo = new double[5];
        statisticInfo[0] = -1;

        int sampleCount = 0;
        double sum = 0;
        int maxFreq = 0;
        int mode = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                // 众数
                if (count[i] > maxFreq) {
                    mode = i;
                    maxFreq = count[i];
                }
                // 最小值
                if (statisticInfo[0] == -1) {
                    statisticInfo[0] = i;
                }
                // 总数
                sum += (long)i * count[i] / 1000000.0;

                // 抽样数
                sampleCount += count[i];

                // 最大数
                statisticInfo[1] = i;
            }
        }

        int[] medianRang = new int[2];
        if (sampleCount % 2 == 0) {
            medianRang[0] = sampleCount / 2;
        } else {
            medianRang[0] = sampleCount / 2 + 1;
        }
        medianRang[1] = sampleCount / 2 + 1;

        int cur = 0;
        int median = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] <= 0) {
                continue;
            }
            cur += count[i];
            if (cur >= medianRang[0] && median == 0) {
                median += i;
            }
            if (cur >= medianRang[1]) {
                median += i;
                break;
            }
        }

        statisticInfo[2] = (sum / sampleCount) * 1000000;
        statisticInfo[3] = median / 2.0;
        statisticInfo[4] = mode;
        return statisticInfo;
    }
}
