package com.mycompany.txthandler;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZhangZhenli
 */
public class NewClass1 {

    public static void main(String[] args) {
        System.out.println("zhang123");
        for (int j = 0; j < 10; j++) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(NewClass1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int generateDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddkkmm");//其中yyyy-MM-dd是你要表示的格式
        //可以任意组合，不限个数和次序；具体表示为：MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;
        String ss = sdf.format(d).toString();
        System.out.println(ss);
        int sb = new Integer(ss);
        return sb;
    }
}
