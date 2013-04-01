/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import java.io.*;
import static java.lang.System.out;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  去除页眉,页脚
 * @author Zawn
 */
public class Setup1 {

    public static void main(String... args) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("C:/Users/Yutian/Desktop/rfc6749_setup1.txt");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader("C:/Users/Yutian/Desktop/rfc6749.txt"));
                LinkedList queue = new LinkedList();
                String line;
                int i = 0;
                try {
                    while ((line = in.readLine()) != null) {
                        queue.add(line);
                        if (line.startsWith("Hardt                        Standards Track")) {
                            out.println("D__" + queue.removeLast());
                            out.println("D__" + queue.removeLast());
                            out.println("D__" + queue.removeLast());
                            out.println("D__" + queue.removeLast());
                            out.println("D__" + (line = in.readLine()));
                            out.println("D__" + (line = in.readLine()));
                            out.println("D__" + (line = in.readLine()));
                            out.println("D__" + (line = in.readLine()));
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Setup1.class.getName()).log(Level.SEVERE, null, ex);
                }

                while (!queue.isEmpty()) {
                    printWriter.println(queue.remove());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Setup1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            printWriter.close();
        }
    }
}
