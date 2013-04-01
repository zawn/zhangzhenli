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
public class C implements Runnable {

    private final Object blockLock;

    C(Object blockLock) {
        this.blockLock = blockLock;
    }

    public void run() {
//        throw new UnsupportedOperationException("Not supported yet.");
        synchronized (blockLock) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(C.class.getName()).log(Level.SEVERE, null, ex);
            }
            blockLock.notifyAll();
            System.out.println("getUserProfile 2 " + Thread.currentThread().getName() + "  " + (new Timestamp(System.currentTimeMillis()).toString()));

        }
    }
}
