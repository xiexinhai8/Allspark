package com.xiexinhai8.base_alg.two_pointers;

import java.util.Arrays;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-16
 */
public class ThreeSum {
    public static void main(String[] args) {
        new ThreeSum().tri(new int[] {0, 1 , 3 , -4, 5});
    }

    public void tri(int[] nums) {
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];

            int head = i + 1, tail = nums.length - 1;
            while (head < nums.length && head < tail) {
                if (nums[head] + nums[tail] == target) {
                    System.out.print("[" + nums[i] + ", " + nums[head] + ", " + nums[tail] + "]");
                    head++;
                    tail--;
                }
                if (nums[head] + nums[tail] < target) {
                    head++;
                }
                if (nums[head] + nums[tail] > target) {
                    tail--;
                }
            }
        }
    }
}
