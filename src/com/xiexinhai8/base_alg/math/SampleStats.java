package com.xiexinhai8.base_alg.math;

/**
 * 1093. 大样本统计
 *
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 在样本中出现的次数。
 *
 * 计算以下统计数据:
 *
 * minimum ：样本中的最小元素。
 * maximum ：样品中的最大元素。
 * mean ：样本的平均值，计算为所有元素的总和除以元素总数。
 * median ：
 * 如果样本的元素个数是奇数，那么一旦样本排序后，中位数 median 就是中间的元素。
 * 如果样本中有偶数个元素，那么中位数median 就是样本排序后中间两个元素的平均值。
 * mode ：样本中出现次数最多的数字。保众数是 唯一 的。
 * 以浮点数数组的形式返回样本的统计信息 [minimum, maximum, mean, median, mode] 。与真实答案误差在 10-5 内的答案都可以通过。
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
