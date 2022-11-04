package com.xiexinhai8.test;

/**
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2022-11-02
 */
public class StackOverflowErroTest {

    private static long count = 0;

    private static void demo() {
        System.out.println(count++);
        demo();
    }

    public static void main(String[] args) {
        StackOverflowErroTest.demo();
    }
}

