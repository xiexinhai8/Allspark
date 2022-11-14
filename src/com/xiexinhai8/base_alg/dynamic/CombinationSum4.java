package com.xiexinhai8.base_alg.dynamic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xxh
 * Created on 2022-07-24
 */
public class CombinationSum4 {

    public static void main(String[] args) {
        Map<Integer, Long> numMap = new HashMap<>();
        List<Integer> list = numMap.keySet().stream().sorted().collect(Collectors.toList());

        int[] nums = {1, 2, 3};
        int target = 4;
        combinationSum4(nums, 4);
    }

    public static int combinationSum4(int[] nums, int target) {
        int[] d = new int[target + 1];

        d[0] = 1;
        for (int i = 1; i < target + 1; i++) {

            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] < 0) {
                    continue;
                }
                d[i] += d[i - nums[j]];
            }
        }
        return d[target];
    }
}
