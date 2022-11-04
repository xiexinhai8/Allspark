package com.xiexinhai8.base_alg.tow_pointers;

import java.util.ArrayList;
import java.util.List;

import com.xiexinhai8.common.util.Printer;

/**
 * 481. 神奇字符串
 *
 * 神奇字符串 s 仅由 '1' 和 '2' 组成，并需要遵守下面的规则：
 *
 * 神奇字符串 s 的神奇之处在于，串联字符串中 '1' 和 '2' 的连续出现次数可以生成该字符串。
 * s 的前几个元素是 s = "1221121221221121122……" 。如果将 s 中连续的若干 1 和 2 进行分组，可以得到 "1 22 11 2 1 22 1 22 11 2 11 22 ......" 。每组中 1
 * 或者 2 的出现次数分别是 "1 2 2 1 1 2 1 2 2 1 2 2 ......" 。上面的出现次数正是 s 自身。
 *
 * 给你一个整数 n ，返回在神奇字符串 s 的前 n 个数字中 1 的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/magical-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MagicalString {

    public static void main(String[] args) {
        System.out.println(new MagicalString().magicalString(100000));
    }

    /**
     * 初始化magic string
     * 初始化head tail指针
     * @param n
     * @return
     */
    public int magicalString(int n) {
        int oneCount = 1;
        if (n <= 3) {
            return oneCount;
        }
        List<Integer> magicList = new ArrayList<>();
        magicList.add(1);
        magicList.add(2);
        magicList.add(2);
        int head = 2, tail = 1;

        while (head < n) {
            int curCount = magicList.get(++tail);
            int curNum = 1;
            if (magicList.get(head) == curNum) {
                curNum = 2;
            }
            while (curCount > 0) {
                magicList.add(curNum);
                head++;
                if (head < n && curNum == 1) {
                    oneCount++;
                }
                curCount--;
            }
        }
        Printer.printList(magicList);
        return oneCount;
    }
}
