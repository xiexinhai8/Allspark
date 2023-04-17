package com.xiexinhai8.base_alg.math;

/**
 * 2409. 统计共同度过的日子数
 *
 * Alice 和 Bob 计划分别去罗马开会。
 *
 * 给你四个字符串 arriveAlice ，leaveAlice ，arriveBob 和 leaveBob 。Alice 会在日期 arriveAlice 到 leaveAlice 之间在城市里（日期为闭区间），而 Bob
 * 在日期 arriveBob 到 leaveBob 之间在城市里（日期为闭区间）。每个字符串都包含 5 个字符，格式为 "MM-DD" ，对应着一个日期的月和日。
 *
 * 请你返回 Alice和 Bob 同时在罗马的天数。
 *
 * 你可以假设所有日期都在 同一个 自然年，而且 不是 闰年。每个月份的天数分别为：[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31] 。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-17
 */
public class CountDaysTogether {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 到达时间选最大的, 离开时间选最小的 (大小判断通过先月再日的方式)
        int commonArrive = getCommonDay(arriveAlice, arriveBob, dayOfMonth, true);
        int commonLeave = getCommonDay(leaveAlice, leaveBob, dayOfMonth, false);
        // System.out.println("arrive=" + commonArrive + " leave=" + commonLeave);

        // 如果离开时间 < 到达时间 返回 0
        if (commonLeave - commonArrive < 0) {
            return 0;
        }
        return commonLeave - commonArrive + 1;
    }

    private int getCommonDay(String dayOfMonth1, String dayOfMonth2, int[] dayOfMonth, boolean isArrive) {
        int days1 = getDays(dayOfMonth1, dayOfMonth);
        int days2 = getDays(dayOfMonth2, dayOfMonth);
        // System.out.println("isArrive = " + isArrive + " day1 = " + days1 + " day2 = " + days2);
        if (isArrive) {
            return Math.max(days1, days2);
        } else {
            return Math.min(days1, days2);
        }
    }

    private int getDays(String dayOfMonthStr, int[] dayOfMonth) {
        String[] monthDay = dayOfMonthStr.split("-");
        int month = Integer.parseInt(monthDay[0]);
        int day = Integer.parseInt(monthDay[1]);
        // System.out.println(" day = " + day + " month = " + month);
        for (int i = 0; i < month - 1; i++) {
            day += dayOfMonth[i];
        }
        return day;
    }
}
