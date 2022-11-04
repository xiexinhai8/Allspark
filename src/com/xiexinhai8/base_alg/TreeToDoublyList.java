package com.xiexinhai8.base_alg;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-09-09
 */
public class TreeToDoublyList {

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        // [27,-99,55,null,-34,null,58,null,-8,null,59,null,8,null,68]
        Node four = new Node(4);
        Node two = new Node(2);
        Node five = new Node(5);
        Node one = new Node(1);
        Node three = new Node(3);
        four.left = two;
        four.right = five;
        two.left = one;
        two.right = three;
        Node head = treeToDoublyList(four);
        while (head != null) {
            System.out.println(head.val);
            head = head.right;
        }

    }

    public static Node treeToDoublyList(Node root) {

        Node[] r = midTra(root);
        Node head = r[0];
        Node tail = r[1];
        while (head.left != null) {
            head = head.left;
        }
        while (tail.right != null) {
            tail = tail.right;
        }
        head.left = tail;
        tail.right = head;
        return head;

    }

    private static Node[] midTra(Node root) {
        Node[] r = new Node[2];
        if (root == null) {
            return r;
        }
        Node[] left = midTra(root.left);
        Node[] right = midTra(root.right);

        if (left[0] != null) {
            root.left = left[0];
            left[0].right = root;
        }
        if (right[1] != null) {
            root.right = right[1];
            right[1].left = root;
        }

        if (right[0] != null) {
            r[0] = right[0];
        } else {
            r[0] = root;
        }

        if (left[1] != null) {
            r[1] = left[1];
        } else {
            r[1] = root;
        }
        return r;
    }
}
