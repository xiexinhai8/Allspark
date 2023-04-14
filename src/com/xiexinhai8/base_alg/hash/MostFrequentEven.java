package com.xiexinhai8.base_alg.hash;

import java.util.Arrays;

/**
 * 2404. 出现最频繁的偶数元素
 *
 * 给你一个整数数组 nums ，返回出现最频繁的偶数元素。
 * 如果存在多个满足条件的元素，只需要返回 最小 的一个。如果不存在这样的元素，返回 -1 。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-13
 */
public class MostFrequentEven {

    public int mostFrequentEven(int[] nums) {
        FreEvenInfo mostFreEvenInfo = new FreEvenInfo();

        if (nums == null || nums.length <= 0) {
            return mostFreEvenInfo.even;
        }
        Arrays.sort(nums);

        FreEvenInfo curFreEvenInfo = new FreEvenInfo();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                if (curFreEvenInfo.even != nums[i]) {
                    if (curFreEvenInfo.fre > mostFreEvenInfo.fre) {
                        mostFreEvenInfo = curFreEvenInfo;
                    }
                    curFreEvenInfo = new FreEvenInfo();
                    curFreEvenInfo.even = nums[i];
                }

                curFreEvenInfo.fre++;
            }
        }

        if (curFreEvenInfo.fre > mostFreEvenInfo.fre) {
            mostFreEvenInfo = curFreEvenInfo;
        }
        return mostFreEvenInfo.even;
    }

    static class FreEvenInfo {
        int even = -1;
        int fre = 0;
    }
}
