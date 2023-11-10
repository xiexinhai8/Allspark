package com.xiexinhai8.base_alg.bin_search;

import java.util.Arrays;

/**
 * 2300. 咒语和药水的成功对数
 *
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 *
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 *
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-11-10
 */
public class SuccessfulPairs {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] pairs = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; i++) {
            int start = 0, end = potions.length - 1;
            // 确认是否存在答案
            if (spells[i] * 1L * potions[end] < success) {
                pairs[i] = 0;
                continue;
            }

            /**存在答案的前提下, 我们希望求的是高位状态的分界线
             原因:
             1. mid = (start + end) / 2, 在end = start + 1的情况下, mid永远等于start
             2. 而在中间的变换过程中, 我们保持的是end和高位状态一致, start也要往高位上靠拢; 最终导致的情况是可能出现 start 和 end的状态都是高位状态
             结合1和2 可以得出 最后start == end时是高地位状态的分界线
             */
            while (start < end) {
                int mid = (start + end) / 2;
                if (spells[i] * 1L  * potions[mid] < success) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            pairs[i] = potions.length - end;

        }

        return pairs;
    }
}
