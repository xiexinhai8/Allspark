package com.xiexinhai8.base_alg.dynamic;

/**
 * @author xxh
 * Created on 2022-08-07
 */
public class NthUglyNumber {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1600));
    }

    public static int nthUglyNumber(int n) {
        int[] uglyNumber = new int[n * 3 + 1];
        uglyNumber[0] = 1;
        int[] uglySeed = {2, 3, 5};

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < uglySeed.length; j++) {
                int ugly = uglyNumber[i] * uglySeed[j];
                addUglyNumber(uglyNumber, ugly);
            }
        }

        return uglyNumber[n - 1];
    }

    private static void addUglyNumber(int[] uglyNumber, int ugly) {
        int i = -1;
        for (int k = uglyNumber.length - 1; k >= 0; k--) {
            if(uglyNumber[k] == 0) {
                continue;
            }
            if (uglyNumber[k] == ugly) {
                break;
            }
            if (uglyNumber[k] < ugly) {
                i = k + 1;
                break;
            }
        }
        if (i > 0) {
            for (int k = uglyNumber.length - 1; k >= i; k--) {
                if(uglyNumber[k] == 0) {
                    continue;
                }
                if (uglyNumber[k] > ugly) {
                    uglyNumber[k + 1] = uglyNumber[k];
                }
            }
            uglyNumber[i] = ugly;
        }
    }
}
