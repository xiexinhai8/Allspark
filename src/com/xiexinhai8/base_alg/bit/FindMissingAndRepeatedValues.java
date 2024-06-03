package com.xiexinhai8.base_alg.bit;

/**
 * 2965. 找出缺失和重复的数字
 *
 * 给你一个下标从 0 开始的二维整数矩阵 grid，大小为 n * n ，其中的值在 [1, n2] 范围内。除了 a 出现 两次，b 缺失 之外，每个整数都 恰好出现一次 。
 *
 * 任务是找出重复的数字a 和缺失的数字 b 。
 *
 * 返回一个下标从 0 开始、长度为 2 的整数数组 ans ，其中 ans[0] 等于 a ，ans[1] 等于 b 。
 * @author xxh
 * Created on 2024-05-31
 */
public class FindMissingAndRepeatedValues {

    /**
     * 位运算
     *
     */
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int len = grid.length;
        int or = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                or ^= grid[i][j];
            }
        }
        for (int i = 1; i <= len * len; i++) {
            or ^= i;
        }

        int low = or & -or;

        int[] result = new int[2];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if ((grid[i][j] & low) != 0) {
                    result[0] ^= grid[i][j];
                } else {
                    result[1] ^= grid[i][j];
                }
            }
        }

        for (int i = 1; i <= len * len; i++) {
            if ((i & low) != 0) {
                result[0] ^= i;
            } else {
                result[1] ^= i;
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == result[0]) {
                    break;
                }
                if (grid[i][j] == result[1]) {
                    int tmp = result[0];
                    result[0] = result[1];
                    result[1] = tmp;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * 数学计算
     * 计算 1-n 的和为realSum, 以及grid中的数据和 sum
     * 假设grid中的数据 a 重复了, b缺失了; 则realSum - sum = a - b
     * 这时如果能够找出a + b就能求出a, b; 而 (a + b) * (a - b) = a^2 - b^2
     * 而求a^2 - b^2 只需要将1 - n 的平方和减去 grid中所有数据都求平方和即可
     */
    public int[] findMissingAndRepeatedValues1(int[][] grid) {
        int[] result = new int[2];
        int len = grid.length;
        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum2 += grid[i][j] * grid[i][j];
                sum += grid[i][j];
            }
        }

        int realSum = 0;
        int realSum2 = 0;
        for (int i = 1; i <= len * len; i++) {
            realSum += i;
            realSum2 += i * i;
        }
        int aLessb = sum - realSum;
        int a2Lessb2 = sum2 - realSum2;
        int aplusb = a2Lessb2 / aLessb;
        result[0] = (aplusb + aLessb) / 2;
        result[1] = (aplusb - aLessb) / 2;
        return result;
    }

    /**
     * 统计
     * 计算 1-n 的和为realSum, 以及grid中的数据和 sum
     * 假设grid中的数据 a 重复了, b缺失了; 则realSum - sum = a - b
     * 这时只需要找出a 或 b 就能算出a 和 b了
     */
    public int[] findMissingAndRepeatedValues0(int[][] grid) {
        boolean[] arr = new boolean[grid.length * grid[0].length];

        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                arr[grid[i][j] - 1] = true;
                sum += grid[i][j];
            }
        }

        int[] result = new int[2];
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) {
                result[1] = i + 1;
                break;
            }
        }
        result[0] = result[1] - (1 + arr.length) * arr.length / 2 + sum;
        return result;
    }
}
