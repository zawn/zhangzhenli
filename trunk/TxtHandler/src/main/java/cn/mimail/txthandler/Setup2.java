/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import java.io.*;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 去无用换行
 *
 * @author Zawn
 */
public class Setup2 {

    // 行长度
    private static int LENGTH = 72;

    public static void main(String... args) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("C:/Users/Yutian/Desktop/rfc6749_setup2.txt");
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader("C:/Users/Yutian/Desktop/rfc6749_setup1.txt"));
                LinkedList queue = new LinkedList();
                ArrayList<int[]> analysis = new ArrayList<int[]>();
                String line;
                int i = 0;
                try {
                    while ((line = in.readLine()) != null) {
                        queue.add(line);
                        byte[] bytes = line.getBytes();
                        if (LENGTH < bytes.length) {
                            LENGTH = bytes.length;
                        }
                        int[] j = {0, 0, 0};
                        while (j[0] < bytes.length && bytes[j[0]] == 0x20) {
                            j[0]++;
                        }
                        j[1] = j[0];
                        while (j[1] < bytes.length && bytes[j[1]] != 0x20) {
                            j[1]++;
                        }
                        j[1] = j[1] - j[0];
                        j[2] = bytes.length;
                        analysis.add(j);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Setup2.class.getName()).log(Level.SEVERE, null, ex);
                }
                Object[] toArray = analysis.toArray();
                LinkedList newQueue = new LinkedList();
                String[] tempStrings = {"", ""};
                for (int j = 0; j < toArray.length; j++) {
                    tempStrings[1] = (String) queue.remove();
                    out.println("----------------------------------------------------------------------------------------------------------- ");
                    out.println(" " + tempStrings[1]);
//                    out.println(StringTools.byteArrayToHexString(tempStrings[1].getBytes()));
                    int[] jpreIntegers = (int[]) toArray[j - 1 >= 0 ? j - 1 : 0];
                    int[] jIntegers = (int[]) toArray[j];
                    out.println(Arrays.toString(jIntegers));
                    out.println("*" + tempStrings[0]);
                    String firstword = tempStrings[1].substring(jIntegers[0], jIntegers[0] + jIntegers[1]);
                    out.println("&" + firstword);
                    boolean matches = firstword.matches("^[A-Za-z0-9-,.():/_\'\"\\[\\]]+$");
                    out.println("&" + matches);
                    if (jIntegers[0] >= jpreIntegers[0] && jIntegers[0] != 0 && jIntegers[1] + jpreIntegers[2] > 71 && matches && !firstword.matches("^[0-9.]+$")) {
                        out.println("_" + tempStrings[1].substring(jIntegers[0]));

                        tempStrings[0] += " " + tempStrings[1].substring(jIntegers[0]);
                    } else {
                        newQueue.add(tempStrings[0]);
                        tempStrings[0] = tempStrings[1];
                    }
                }
                while (!newQueue.isEmpty()) {
                    printWriter.println(newQueue.remove());
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
            Logger.getLogger(Setup2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            printWriter.close();
        }
    }
}
