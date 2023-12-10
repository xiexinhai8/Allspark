package com.xiexinhai8.base_alg.dynamic;

import java.util.Arrays;

/**
 *
 * 2008. 出租车的最大盈利
 *
 * 你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
 *
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
 *
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。
 *
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
 *
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 * @author xxh
 * Created on 2023-12-11
 */
public class MaxTaxiEarnings {

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a1, a2) -> (a1[1] - a2[1]));

        long[] f = new long[n + 1];

        int cur = 0;
        for (int i = 1; i <= n; i++) {

            f[i] = f[i - 1];
            while (cur < rides.length && rides[cur][1] == i) {
                f[i] = Math.max(f[i], f[rides[cur][0]] + rides[cur][1] - rides[cur][0] + rides[cur][2]);
                cur++;
            }

        }

        return f[n];
    }
}
