/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.txthandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZhangZhenli
 */
public class StartServer {

    public static void main(String... args) throws IOException {
        try {
            File dir = new File(ClassLoader.getSystemResource("").toURI());
            System.out.println(StartServer.class.getResource("/"));
            System.out.println(ClassLoader.getSystemResource(""));
            File f = new File(dir, "Server.bat");
            if (!f.exists() || !f.canExecute()) {
                throw new IOException("文件不存在或不可执行");
            }
            BufferedReader in = null;
            try {
                String cmd = "cmd /k start ";
                String param = f.getAbsolutePath();
                Runtime.getRuntime().exec(cmd + param);
//                Process exec = Runtime.getRuntime().exec(cmd + param);
//                // 获得输出
//                in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
//                String line;
//                while ((line = in.readLine()) != null) {
//                    out.println(line);
//                }
//                in.close();
            } catch (IOException ex) {
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                try {
//                    in.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
