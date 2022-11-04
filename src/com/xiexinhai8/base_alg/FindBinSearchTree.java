package com.xiexinhai8.base_alg;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-10-11
 */
public class FindBinSearchTree {

    public static void main(String[] args) {

    }
    static class Node {
        int val;
        Node left;
        Node right;
        Node parent;

    }

    public Node query(Node target) {
        Node pre = null;
        search(pre, target);
        return null;
    }

    private Node search(Node pre, Node target) {
        if (target == null) {
            return target;
        }

        if (target.right != null && target.right != pre) {
            target = target.right;
            while (target.left != null) {
                target = target.left;
            }
            return target;
        }

        pre = target;
        target = target.parent;
        return search(pre, target);
    }
}
