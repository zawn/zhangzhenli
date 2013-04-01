/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.txthandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZhangZhenli
 */
public class NewClass {

    public static void main(String... args) {
        BufferedReader in = null;
        try {
            String cmd = "ping ";
            String param = "www.baidu.com";
            Process child = Runtime.getRuntime().exec(cmd + param);
            // 获得ping的输出
            in = new BufferedReader(new InputStreamReader(child.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
