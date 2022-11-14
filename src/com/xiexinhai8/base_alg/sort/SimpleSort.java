package com.xiexinhai8.base_alg.sort;

/**
 * @author xxh
 * Created on 2022-07-04
 */
public class SimpleSort {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        System.out.println(print(sortedNums(nums)));
        twoSum(sortedNums(nums), 6);
    }

    static String print(int[] nums) {
        StringBuffer c = new StringBuffer();
        for(int i = 0; i < nums.length; i++) {
            c.append(new Integer(nums[i]).toString()).append(',');
        }
        return c.toString();
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int[] sortedNums = sortedNums(nums);
        for (int i = 0, j = sortedNums.length - 1; i < j;) {
            if (sortedNums[i] + sortedNums[j] == target) {
                result[0] = i;
                result[1] = j;
                break;
            }
            if (sortedNums[i] + sortedNums[j] > target) {
                j--;
            } else if (sortedNums[i] + sortedNums[j] < target) {
                i++;
            }
        }
        return result;
    }

    static int[] sortedNums(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            int swap = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[i] < nums[j]; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = swap;
        }
        return nums;
    }
}
