package com.xiexinhai8.base_alg.entity;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-18
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
