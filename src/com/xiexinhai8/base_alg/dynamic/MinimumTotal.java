package com.xiexinhai8.base_alg.dynamic;

import java.util.Arrays;
import java.util.List;

/**
 * 120. 三角形最小路径和
 *
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i +
 * 1 。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-18
 */
public class MinimumTotal {

    public int minimumTotal(List<List<Integer>> tri) {
        int[] minPath = new int[tri.size()];
        Arrays.fill(minPath, (int)1e5);
        minPath[0] = tri.get(0).get(0);

        for (int row = 1; row < tri.size(); row++) {
            int[] minPathTemp = new int[tri.size()];
            Arrays.fill(minPathTemp, (int)1e5);
            for (int col = 0; col < tri.get(row).size(); col++) {
                minPathTemp[col] = minPath[col];
                if ((col - 1 >= 0)) {
                    minPathTemp[col] = Math.min(minPath[col - 1], minPath[col]);
                }
                minPathTemp[col] +=  tri.get(row).get(col);

            }
            minPath = minPathTemp;
            /**for (int i = 0; i < minPath.length; i++) {
             System.out.print(minPath[i] + ", ");
             }
             System.out.println(); */
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < minPath.length; i++) {
            min = Math.min(min, minPath[i]);
            // System.out.print(minPath[i] + ", ");
        }
        return min;
    }
}
