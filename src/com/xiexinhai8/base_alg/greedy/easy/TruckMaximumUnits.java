package com.xiexinhai8.base_alg.greedy.easy;

import java.util.Arrays;

/**
 * 1710. 卡车上的最大单元数
 *
 * 请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
 *
 * numberOfBoxesi 是类型 i 的箱子的数量。
 * numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
 * 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
 *
 * 返回卡车可以装载 单元 的 最大 总数。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-15
 */
public class TruckMaximumUnits {

    /**
     * 贪心算法、先将卡车上的箱子类型按性价比倒序排序
     * 优先取性价比最高的， 直到装满为止
     * @param boxTypes
     * @param truckSize
     * @return
     */
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (box1, box2) -> box2[1] - box1[1]);

        int maxUnit = 0;
        for (int i = 0; i < boxTypes.length && truckSize > 0; i++) {
            if (truckSize - boxTypes[i][0] >= 0) {
                maxUnit += (boxTypes[i][0] * boxTypes[i][1]);
                truckSize -= boxTypes[i][0];
            } else {
                maxUnit += (truckSize * boxTypes[i][1]);
                truckSize = 0;
            }
        }

        return maxUnit;
    }
}
