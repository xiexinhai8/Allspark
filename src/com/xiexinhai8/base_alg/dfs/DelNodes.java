package com.xiexinhai8.base_alg.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xiexinhai8.base_alg.entity.TreeNode;

/**
 * 1110. 删点成林
 *
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-05-30
 */
public class DelNodes {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        /** 思路: 后续遍历, 先从底层节点开始删
         问题: 底层设置root=null后上层好像不认
         */
        List<TreeNode> trees = new ArrayList<>();
        Set<Integer> toDelete = new HashSet<Integer>();
        for (int num : to_delete) {
            toDelete.add(num);
        }

        root = dfs(root, toDelete, trees);

        if (root != null) {
            trees.add(root);
        }

        return trees;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> toDelete, List<TreeNode> trees) {
        if (root == null) {
            return root;
        }

        TreeNode left = dfs(root.left, toDelete, trees);
        if (left == null) {
            root.left = null;
        }

        TreeNode right = dfs(root.right, toDelete, trees);
        if (right == null) {
            root.right = null;
        }

        if (toDelete.contains(root.val)) {
            if (root.left != null) {
                trees.add(root.left);
            }
            if (root.right != null) {
                trees.add(root.right);
            }
            root = null;
        }
        return root;
    }
}
