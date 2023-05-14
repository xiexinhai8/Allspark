package com.xiexinhai8.base_alg.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 1054. 距离相等的条形码
 *
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 *
 * 请你重新排列这些条形码，使其中任意两个相邻的条形码不能相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * @author xxh
 * Created on 2023-05-14
 */
public class RearrangeBarcodes {

    public static void main(String[] args) {
        int[] barcodes = {1,1,1,1,2,2,3,3};
        System.out.println(new RearrangeBarcodes().rearrangeBarcodes(barcodes));
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        /** 思路1:
         统计数组的频率, 根据不同的元素, 每次出一个不一样的

         问题: 最后平铺的元素不一定重复
         */
        /** 思路2:统计频率, 根据频率排序, 每次消耗频率最多的元素

         */
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < barcodes.length; i++) {
            int freq = freqMap.getOrDefault(barcodes[i], 0);
            freqMap.put(barcodes[i], freq + 1);
        }

        int[][] entrySet = new int[freqMap.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> es : freqMap.entrySet()) {
            int[] tmp = new int[2];
            tmp[0] = es.getKey();
            tmp[1] = es.getValue();
            entrySet[index++] = tmp;
        }
        Arrays.sort(entrySet, (x1, x2) -> x2[1] - x1[1]);

        int first = 0, second = 1;
        index = 0;
        while (first < entrySet.length && index < barcodes.length) {
            if (second < entrySet.length) {
                if (entrySet[first][1] > 0 && entrySet[second][1] > 0) {
                    barcodes[index++] = entrySet[first][0];
                    barcodes[index++] = entrySet[second][0];
                    entrySet[first][1]--;
                    entrySet[second][1]--;
                } else if (entrySet[first][1] <= 0 && entrySet[second][1] > 0) {
                    first = second;
                    second++;
                    barcodes[index++] = entrySet[first][0];
                    barcodes[index++] = entrySet[second][0];
                    entrySet[first][1]--;
                    entrySet[second][1]--;
                } else if (entrySet[first][1] <= 0 && entrySet[second][1] <= 0) {
                    first = second + 1;
                    second = first + 1;
                    barcodes[index++] = entrySet[first][0];
                    barcodes[index++] = entrySet[second][0];
                    entrySet[first][1]--;
                    entrySet[second][1]--;
                } else {
                    second = second + 1;
                }
            } else {
                barcodes[index++] = entrySet[first][0];
                entrySet[first][1]--;
            }

        }

        return barcodes;
    }

    /**
     * 最大堆
     * @param barcodes
     * @return
     */
    public int[] rearrangeBarcodes1(int[] barcodes) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int b : barcodes) {
            if (!count.containsKey(b)) {
                count.put(b, 0);
            }
            count.put(b, count.get(b) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            pq.offer(new int[]{entry.getValue(), entry.getKey()});
        }
        int n = barcodes.length;
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int[] p = pq.poll();
            int cx = p[0], x = p[1];
            if (i == 0 || res[i - 1] != x) {
                res[i] = x;
                if (cx > 1) {
                    pq.offer(new int[]{cx - 1, x});
                }
            } else {
                int[] p2 = pq.poll();
                int cy = p2[0], y = p2[1];
                res[i] = y;
                if (cy > 1) {
                    pq.offer(new int[]{cy - 1, y});
                }
                pq.offer(new int[]{cx, x});
            }
        }
        return res;
    }
}
