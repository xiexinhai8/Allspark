package com.xiexinhai8.base_alg.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1090. 受标签影响的最大值
 *
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。
 *
 * 从 n 个元素中选择一个子集 s :
 *
 * 子集 s 的大小 小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 *
 * 返回子集 s 的最大 分数 。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-23
 */
public class LargestValsFromLabels {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        /** 思路1: 将value 和 label 做成 pair, 对其按 value倒序排序, 采用贪心优先选择大的
         1、 做pair
         2、排序
         3、优先选大的, 如果label计数超过useLimit 跳过
         4、直到选取numWanted个数为止
         */
        Data[] datas = new Data[values.length];
        for (int i = 0; i < values.length; i++) {
            datas[i] = new Data(values[i], labels[i]);
        }

        Arrays.sort(datas, (data1, data2) -> data2.value - data1.value);

        int total = 0;
        Map<Integer, Integer> labelCounter = new HashMap<>();
        int counter = 0;

        for (int i = 0; i < datas.length && counter < numWanted; i++) {
            int labelCount = labelCounter.getOrDefault(datas[i].label, 0);
            if (labelCount >= useLimit) {
                continue;
            }
            total += datas[i].value;
            labelCounter.put(datas[i].label, labelCount + 1);
            counter++;
        }

        return total;
    }

    static class Data {
        int value;
        int label;

        public Data(int value, int label) {
            this.value = value;
            this.label = label;
        }
    }
}
