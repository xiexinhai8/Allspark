package com.xiexinhai8.base_alg.dynamic.combination;

/**
 * 2786. 访问数组中的位置使分数最大
 *
 * 给你一个下标从 0 开始的整数数组 nums 和一个正整数 x 。
 *
 * 你 一开始 在数组的位置 0 处，你可以按照下述规则访问数组中的其他位置：
 *
 * 如果你当前在位置 i ，那么你可以移动到满足 i < j 的 任意 位置 j 。
 * 对于你访问的位置 i ，你可以获得分数 nums[i] 。
 * 如果你从位置 i 移动到位置 j 且 nums[i] 和 nums[j] 的 奇偶性 不同，那么你将失去分数 x 。
 * 请你返回你能得到的 最大 得分之和。
 *
 * 注意 ，你一开始的分数为 nums[0] 。
 * @author xxh
 * Created on 2024-06-18
 */
public class MaxScore {

    /**
     * 正确思想:
     * 题目含义是需要求数组中访问某些元素的最大得分, 我们假设最后访问元素为i的最大得分为f[i];
     * 而f[i]肯定是由前面某个最大得分想转移过来的, 不过由于题目的特殊性(分奇偶), 我们需要将前缀最大值也分奇偶保存
     */
    public long maxScore(int[] nums, int x) {
        long[] f = { Integer.MIN_VALUE, Integer.MIN_VALUE };

        f[nums[0] & 1] = nums[0];
        long maxScore = nums[0];
        for (int i = 1; i < nums.length; i++) {
            long score = f[nums[i] & 1] + nums[i];
            score = Math.max(score, f[1 - (nums[i] & 1)] + nums[i] - x);
            maxScore = Math.max(maxScore, score);
            f[nums[i] & 1] = Math.max(f[nums[i] & 1], score);
        }

        return maxScore;
    }

    /**
     * 前面选 + 后面选
     * 前面选 + 后面不选
     * 前面不选 + 后面选
     * 前面不选 + 后面不选
     */
    /**
     * 错误思想:
     * 1. 对任意一个元素来说, 在分数最大的结果序列中都有选和不选的可能, 所以使用一个二维数组表达[0, i]数组中最大分数为
     * 不选i f[0][i] = Math.max(f[1][i - 1], f[0][i - 1])
     * 选 i f[1][i] = Math.max(f[1][i - 1] - x[不同奇偶], f[0][i - 1] - x[不同奇偶]) +
     * nums[i]
     * 问题: 选i时, 需要判断不同奇偶, 如果 i - 1也选比较好判断, 但是一旦i - 1不选, 则需要便利前面所有已经计算过的数据
     *
     * 2. 题目含义是需要求数组中访问某些元素的最大得分, 我们假设最后访问元素为i的最大得分为f[i];
     * f[i] = Math.max(f[k] - x[不同奇偶性]) + nums[i] 其中 0 <= k < i
     * 使用这种方法,时间复杂度为0(n^2)， 而且整体答案也是错误的
     */
    public long maxScore_wrong2(int[] nums, int x) {
        int[] f = new int[nums.length];
        f[0] = nums[0];

        int max = f[0];
        for (int i = 1; i < nums.length; i++) {
            f[i] = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if ((nums[i] & 1) == (nums[j] & 1)) {
                    f[i] = Math.max(f[i], f[j] + nums[i]);
                } else {
                    f[i] = Math.max(f[i], f[j] + nums[i] - x);
                }
            }
            max = Math.max(f[i], max);
        }

        return max;
    }
}
