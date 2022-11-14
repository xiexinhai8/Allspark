package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-07-17
 */
public class MaxSideLength {
    public static void main(String[] args) {
        int[][] mat = {{1,1,3,2,4,3,2},{1,1,3,2,4,3,2},{1,1,3,2,4,3,2}};
        maxSideLength(mat, 4);
    }
    public static int maxSideLength(int[][] mat, int threshold) {
        int maxSideLength = Math.min(mat.length, mat[0].length);
        int[][] d = new int[mat.length + 1][mat[0].length + 1];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                d[i + 1][j + 1] = d[i + 1][j] + d[i][j + 1] + mat[i][j] - d[i][j];
            }
        }

        boolean[] ra = new boolean[maxSideLength];
        int r = 0;
        for (int k = 0; k < maxSideLength; k++) {

            for (int i = k; i < mat.length; i++) {

                for (int j = k; j < mat[0].length; j++) {
                    int sum = d[i + 1][j + 1] - d[i - k][j + 1] - d[i + 1][j - k] + d[i - k][j - k];
                    if (sum <= threshold) {
                        r = k + 1;
                        ra[k] = true;
                        break;
                    }
                }
                if (ra[k]) {
                    break;
                }
            }
        }
        return r;

    }

}
