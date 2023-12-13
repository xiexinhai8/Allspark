package com.xiexinhai8.base_alg.zijie;

import java.util.ArrayList;
import java.util.List;

/**
 * 498. 对角线遍历
 * 
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-12-13
 */


public class FindDiagonalOrder {
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        int[][] mat = {{1, 2, 3, 4,  5},
                       {4, 5, 6, 7,  8},
                       {7, 8, 9, 10, 11}};
        int[] ans = findDiagonalOrder(mat);

        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i] + ", ");
        }

    }

    private static int[] findDiagonalOrder(int[][] mat) {

        if (mat == null) {
            return null;
        }
        int[] ans = new int[mat.length * mat[0].length];

        int ansIndex = 0;
        for (int i = 0; i < mat.length + mat[0].length - 1; i++) {
            int[] curAns = getAns(mat, i);
            for (int j = 0; j < curAns.length; j++) {
                if (i % 2 == 0) {
                    ans[ansIndex++] = curAns[j];
                } else {
                    ans[ansIndex++] = curAns[curAns.length - 1 - j];
                }

            }
        }

        return ans;
    }

    private static int[] getAns(int[][] mat, int i) {
        List<Integer> arr = new ArrayList<>();

        int row = 0, col = 0;
        if (i < mat.length) {
            row = i;
            col = 0;
        } else {
            row = mat.length - 1;
            col = i - mat.length + 1;
        }
        while (row >= 0 && row < mat.length && col >= 0 && col < mat[0].length) {
            arr.add(mat[row--][col++]);
        }

        int[] tmpAns = new int[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            tmpAns[j] = arr.get(j);
        }
        return tmpAns;
    }
}
