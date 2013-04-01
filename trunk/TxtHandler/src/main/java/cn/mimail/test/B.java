/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.test;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yutian
 */
public class B extends A {

    static {
        System.out.println("static B");
        setZhangString("zhangyajun");
    }

    public B() {
        super();
        System.out.println("构造函数B");
    }

    public int getUserProfile() {
        Object blockLock = new Object();
        synchronized (blockLock) {
            System.out.println("getUserProfile 2 " + Thread.currentThread().getName() + "  " + (new Timestamp(System.currentTimeMillis()).toString()));
            new Thread(new C(blockLock), "线程C").start();
            try {
                blockLock.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(B.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("getUserProfile 2 " + Thread.currentThread().getName() + "  " + (new Timestamp(System.currentTimeMillis()).toString()));
            System.out.println("getUserProfile 2 " + Thread.currentThread().getName() + "  " + (new Timestamp(System.currentTimeMillis()).toString()));
            return 1;
        }
    }
}
