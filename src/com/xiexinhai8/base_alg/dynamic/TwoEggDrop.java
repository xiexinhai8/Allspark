package com.xiexinhai8.base_alg.dynamic;

/**
 * 1884. 鸡蛋掉落-两枚鸡蛋
 *
 * 给你 2 枚相同 的鸡蛋，和一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都 会碎 ，从 f 楼层或比它低 的楼层落下的鸡蛋都 不会碎 。
 *
 * 每次操作，你可以取一枚 没有碎 的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2024-01-11
 */
public class TwoEggDrop {

    /**
     f(n, 1) = n;
     f(n, 2) = min{max{f(k - 1, 1), f(n - k, 2)}} + 1

     f(0 , 2) = 0;
     */
    public int twoEggDrop(int n) {
        int[][] f = new int[n + 1][3];

        for (int i = 0; i < f.length; i++) {
            f[i][1] = i;
        }
        f[0][2] = 0;
        f[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            f[i][2] = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                f[i][2] = Math.min(Math.max(f[j - 1][1], f[i - j][2]), f[i][2]);
            }
            f[i][2] += 1;
        }

        return f[n][2];
    }
}
