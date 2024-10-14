package com.xiexinhai8.base_alg.dynamic;

import java.util.Arrays;

/**
 * 887. 鸡蛋掉落
 * <p>
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * <p>
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 * @author xxh
 * Created on 2024-10-14
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        SuperEggDrop s = new SuperEggDrop();
        int age = 100;
        long time = System.currentTimeMillis();
        for (int i = 0; i < age; i++) {
            s.superEggDrop(8, 100000);
        }
        System.out.println(System.currentTimeMillis() - time);
        /*time = System.currentTimeMillis();
        for (int i = 0; i < age; i++) {
            s.superEggDrop1(8, 100000);
        }
        System.out.println(System.currentTimeMillis() - time);*/
    }

    /**
     * f(1, k) = 1;
     * f(n, 1) = n;
     *
     * f(n, k) = Min{Max{f(n - x, k), f(x - 1, k - 1)} + 1};  1<x<n
     */
    public int superEggDrop(int k, int n) {
        int[][] f = new int[n + 1][k + 1];
        Arrays.fill(f[1], 1);
        f[1][0] = 0;
        for (int i = 0; i < f.length; i++) {
            f[i][1] = i;
        }
        for (int i = 2; i < f.length; i++) {
            for (int e = 2; e < f[0].length; e++) {
                f[i][e] = findMin(f, i, e);
            }
        }

        return f[n][k];
    }

    private int findMin(int[][] f, int level, int egg) {
        int slow = 1, high = level;

        while (slow < high) {
            int mid = (slow + high + 1) / 2;
            int k1 = f[mid - 1][egg - 1]; // 碎
            int k2 = f[level - mid][egg]; // 不碎
            if (k1 > k2) {
                high = mid - 1;
            } else {
                slow = mid;
            }
        }

        return Math.min(Math.max(f[slow - 1][egg - 1], f[level - slow][egg]),
                Math.max(f[slow][egg - 1], f[level - slow - 1][egg])) + 1;
    }

    public int superEggDrop1(int k, int n) {
        int[][] f = new int[n + 1][k + 1];
        Arrays.fill(f[1], 1);
        f[1][0] = 0;
        for (int i = 0; i < f.length; i++) {
            f[i][1] = i;
        }
        for (int i = 2; i < f.length; i++) {
            for (int e = 2; e < f[0].length; e++) {
                f[i][e] = i;
                for (int x = 1; x <= i; x++) {
                    f[i][e] = Math.min(f[i][e], Math.max(f[i - x][e], f[x - 1][e - 1]) + 1);
                }
            }
        }

        return f[n][k];
    }
}
