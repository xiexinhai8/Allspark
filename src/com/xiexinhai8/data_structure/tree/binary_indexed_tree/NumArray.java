package com.xiexinhai8.data_structure.tree.binary_indexed_tree;

/**
 * 307. 区域和检索 - 数组可修改
 *
 * 给你一个数组 nums ，请你完成两类查询。
 *
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1],
 * ..., nums[right]）
 * @author xxh
 * Created on 2024-06-07
 */

/**
 * 树状数组
 *
 * 使用树状数组处理可变数组的区间和
 */
class NumArray {
    int[] c;
    int[] nums;

    public NumArray(int[] nums) {
        this.nums = nums;
        c = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            add(i + 1, nums[i]);
        }
    }

    private void add(int index, int val) {
        while (index < c.length) {
            c[index] += val;
            index += lowbit(index);
        }
    }

    private int lowbit(int x) {
        return -x & x;
    }

    // update(i, val) = add(i, val - nums[i])
    public void update(int index, int val) {
        int delta = val - nums[index];
        add(index + 1, delta);
        nums[index] = val;
    }


    // [l : r] = [0 : r] - [0 : l]
    public int sumRange(int left, int right) {
        int rightSum = getPrefix(right + 1);
        int leftSum = getPrefix(left);
        return rightSum - leftSum;
    }

    private int getPrefix(int x) {
        int sum = 0;
        while (x > 0) {
            sum += c[x];
            x = x - lowbit(x);
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
