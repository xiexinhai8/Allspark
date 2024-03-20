package com.xiexinhai8.base_alg.two_pointers;

/**
 *
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * @author xxh
 * Created on 2024-03-20
 */
public class MoveZeroes {

    /**
     用zero指针将数组分开成非0和0的两个部分
     1. 首先要找到第一个0, 然后确定边界
     2. 如果找到非0的元算和zero指向的元算进行交换
     */
    public void moveZeroes(int[] nums) {
        int zero = -1;

        for (int i = 0; i < nums.length; i++) {
            if (zero < 0 && nums[i] == 0) {
                zero = i;
            } else if (zero >= 0 && nums[i] != 0) {
                int tmp = nums[i];
                nums[i] = nums[zero];
                nums[zero] = tmp;
                zero++;
            }
        }
    }
}
