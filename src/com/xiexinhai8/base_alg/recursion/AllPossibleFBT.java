package com.xiexinhai8.base_alg.recursion;

import java.util.ArrayList;
import java.util.List;

import com.xiexinhai8.base_alg.entity.TreeNode;

/**
 * 894. 所有可能的真二叉树
 *
 * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
 *
 * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
 *
 * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
 * @author xxh
 * Created on 2024-05-10
 */
public class AllPossibleFBT {

    /**
     * 递归
     * 方法: 采用递归的方法解n个节点可以生成的真二叉树
     * 如果n == 1则只返回一个root节点的列表;
     * 如果n > 1 则将n 拆解成 2i 和 n - 2i - 1 分别为此数的左右子树的节点数, 并去生成对应子树的真二叉树列表
     * 并且将左右子树生成的二叉树列表进行组合后返回
     *
     * 步骤:
     * 首先判断节点数量, 如果节点数量为偶数则直接返回空解
     * 在判断是否n == 1
     * 如果是则生成一个根节点后返回列表
     * 如果不是则将n 拆解成 2i 和 n - 2i - 1 分别为此数的左右子树的节点数, 并去生成对应子树的真二叉树列表
     * 并且将左右子树生成的二叉树列表进行组合后返回
     * 返回组合后的真二叉树列表
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n % 2 == 0) {
            return result;
        }

        TreeNode root = new TreeNode(0);
        if (n == 1) {
            result.add(root);
            return result;
        }
        for (int k = 1; k < n; k += 2) {
            List<TreeNode> lefts = allPossibleFBT(k);
            List<TreeNode> rights = allPossibleFBT(n - 1 - k);
            for (int i = 0; i < lefts.size(); i++) {
                root.left = lefts.get(i);
                for (int j = 0; j < rights.size(); j++) {
                    root.right = rights.get(j);
                    result.add(deepCopy(root));
                }
            }
        }
        return result;

    }


    TreeNode root1;
    int nodeNum;
    List<TreeNode> result1 = new ArrayList<>();
    public List<TreeNode> allPossibleFBT1(int n) {

        if (n % 2 == 0) {
            return result1;
        }
        root1 = new TreeNode(0);
        nodeNum = n - 1;
        build(root1);

        return result1;
    }

    private void build(TreeNode curRoot) {
        if (nodeNum <= 0) {
            return;
        }
        // 怎么体现不build

        // build
        curRoot.left = new TreeNode(0);
        curRoot.right = new TreeNode(0);
        nodeNum -= 2;
        if (nodeNum == 0) {
            result1.add(deepCopy(root1));
        }
        build(curRoot.left);
        build(curRoot.right);

        curRoot.left = null;
        curRoot.right = null;
        nodeNum += 2;

    }
    private TreeNode deepCopy(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);

        newRoot.left = deepCopy(root.left);
        newRoot.right = deepCopy(root.right);
        return newRoot;
    }
}
