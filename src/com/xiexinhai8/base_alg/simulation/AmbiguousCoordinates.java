package com.xiexinhai8.base_alg.simulation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-07
 */
public class AmbiguousCoordinates {

    public static void main(String[] args) {
        System.out.println(new AmbiguousCoordinates().ambiguousCoordinates("012340"));
    }

    public List<String> ambiguousCoordinates(String s) {

        List<String> ambiguousCoordinates = new ArrayList<>();
        /** 分出的字串不能全是0, 除非只有一个 */
        for (int j = 1; j < s.length(); j++) {
            String pre = s.substring(0, j);
            String post = s.substring(j, s.length());
            if (validate(pre) && validate(post)) {

                List<String> preList = generateList(pre);
                List<String> postList = generateList(post);

                combin(preList, postList, ambiguousCoordinates);
            }
        }

        return ambiguousCoordinates;
    }

    private void combin(List<String> preList, List<String> postList, List<String> ambiguousCoordinates) {
        for (int i = 0; i < preList.size(); i++) {

            for (int j = 0; j < postList.size(); j++) {
                ambiguousCoordinates.add("(" + preList.get(i) + ", " + postList.get(j) + ")");
            }
        }
    }

    /** 不能全为0,除非只有一位 . 不能全为0
     1、首位非0且尾数非0小数点可以随便加
     2、首位是0小数点只能加在第一个0后
     */
    private List<String> generateList(String candiate) {
        List<String> result = new ArrayList<>();
        if (candiate.charAt(0) == '0') {
            if (candiate.length() > 1) {
                result.add(candiate.substring(0, 1) + "." + candiate.substring(1, candiate.length()));
            } else {
                result.add(candiate);
            }
            return result;
        }
        result.add(candiate);

        if (candiate.charAt(candiate.length() - 1) == '0') {
            return result;
        }
        for (int i = candiate.length() - 1; i > 0; i--) {
            result.add(candiate.substring(0, i) + "." + candiate.substring(i, candiate.length()));
        }
        return result;
    }

    /**
     * 1、分出的字串不能全是0, 除非只有一个
     * 2、如果首位为0， 末位不能为0*/
    private boolean validate(String candiate) {
        if (candiate.length() == 1) {
            return true;
        }

        if (candiate.charAt(0) == '0') {
            return candiate.charAt(candiate.length() - 1) != '0';

        }
        return true;
    }
}
