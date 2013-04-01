/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.test;

/**
 *
 * @author Yutian
 */
public class A {

    private static final String NAME = "zhangzhenli";
    private static volatile String zhangString = NAME;

    static {
        System.out.println("static A");
    }

    public static String getZhangString() {
        return zhangString;
    }

    public static void setZhangString(String zhangString) {
        A.zhangString = zhangString;
    }

    public A() {
        System.out.println("构造函数A");
    }
}
