package com.xiexinhai8.base_alg.hash;

/**
 * 447. 回旋镖的数量
 *
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k
 * 之间的欧式距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-08
 */
public class NumberOfBoomerangs {

    /**
     *  暴力方法，时间复杂度0(n^3)
     */
    public int numberOfBoomerangs(int[][] points) {
        int num = 0;

        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];

            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                int[] cycle1 = points[j];

                for (int k = 0; k < points.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    int[] cycle2 = points[k];

                    if (dis(cycle1, point) == dis(cycle2, point)) {
                        num += 1;
                    }
                }
            }
        }

        return num;
    }

    private int dis(int[] point1, int[] point2) {
        return (point2[0] - point1[0]) * (point2[0] - point1[0]) + (point2[1] - point1[1]) * (point2[1] - point1[1]);
    }
}
