package com.xiexinhai8.data_structure.tree.segment_tree;

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
 * 线段树
 *
 * 使用线段树处理可变数组的区间和
 */
class NumArray {
    int[] treeArray;
    int[] nums;

    public NumArray(int[] nums) {
        treeArray = new int[4 * nums.length];
        this.nums = nums;
        initTree(0, 0, nums.length - 1, nums);
    }

    // 建立线断树
    private void initTree(int pos, int start, int end, int[] nums) {
        if (start > end) {
            return;
        }
        if (start == end) {
            treeArray[pos] = nums[start];
            return;
        }

        int mid = (start + end) / 2;
        initTree(pos * 2 + 1, start, mid, nums);
        initTree((pos + 1) * 2, mid + 1, end, nums);

        treeArray[pos] = treeArray[pos * 2 + 1] + treeArray[(pos + 1) * 2];
    }

    // 更新线断树
    public void update(int index, int val) {
        update(index, val, 0, 0, nums.length - 1);
        nums[index] = val;

    }

    private void update(int index, int val, int pos, int start, int end) {
        if (start == end) {
            // System.out.println("pos1 = " + start + " val1 = " + treeArray[start]);
            treeArray[pos] = val;
            return;
        }

        this.treeArray[pos] -= nums[index];
        this.treeArray[pos] += val;
        // System.out.println("pos = " + pos + " val = " + treeArray[pos]);
        int mid = (start + end) / 2;
        if (index >= start && index <= mid) {
            update(index, val, pos * 2 + 1, start, mid);
        } else {
            update(index, val, (pos + 1) * 2, mid + 1, end);
        }
    }

    public int sumRange(int left, int right) {
        return sumRange(left, right, 0, nums.length - 1, 0);
    }

    private int sumRange(int left, int right, int start, int end, int pos) {
        if (left <= start && right >= end) {
            return treeArray[pos];
        }

        int sum = 0;
        int mid = (start + end) / 2;
        if (left <= mid) {
            sum += sumRange(left, right, start, mid, pos * 2 + 1);
        }
        if (mid + 1 <= right) {
            sum += sumRange(left, right, mid + 1, end, (pos + 1) * 2);
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
