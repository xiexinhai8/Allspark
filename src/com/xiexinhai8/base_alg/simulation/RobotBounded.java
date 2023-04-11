package com.xiexinhai8.base_alg.simulation;

/**
 * 1041. 困于环中的机器人
 *
 * 在无限的平面上，机器人最初位于 (0, 0) 处，面朝北方。注意:
 *
 * 北方向 是y轴的正方向。
 * 南方向 是y轴的负方向。
 * 东方向 是x轴的正方向。
 * 西方向 是x轴的负方向。
 * 机器人可以接受下列三条指令之一：
 *
 * "G"：直走 1 个单位
 * "L"：左转 90 度
 * "R"：右转 90 度
 * 机器人按顺序执行指令 instructions，并一直重复它们。
 *
 * 只有在平面中存在环使得机器人永远无法离开时，返回 true。否则，返回 false。
 *
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-11
 */
public class RobotBounded {

    public boolean isRobotBounded(String instructions) {
        RobotStatus rs = new RobotStatus();

        for (int i = 0; i < instructions.length(); i++) {
            rs.processInstruction(instructions.charAt(i));
        }

        return rs.bounded();

    }

    static class RobotStatus {
        int x;
        int y;
        int dir = 0;

        public void processInstruction(char i) {
            if (i == 'G') {
                if (dir == 0) {
                    y++;
                }
                if (dir == 1) {
                    x++;
                }
                if (dir == 2) {
                    y--;
                }
                if (dir == 3) {
                    x--;
                }
            } else {
                int dirProc = i == 'R'? 1 : -1;
                dir = (dir + 4 + dirProc) % 4;
            }
        }

        public boolean bounded() {
            if (x == 0 && y == 0) {
                return true;
            }
            if (dir != 0) {
                return true;
            }
            return false;
        }
    }
}
