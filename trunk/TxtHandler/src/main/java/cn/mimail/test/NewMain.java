/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yutian
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        B b = new B();
//        b.getUserProfile();
//        System.out.println(B.getZhangString());
        File f = new File("G:/Android_3.7.2/YiTest/assets/databases/UserData_Create_3.sql");
        try {
            String sql = convertStreamToString(new FileInputStream(f));
            List<String> ss = preExeSQL(sql);
            for (Iterator<String> it = ss.iterator(); it.hasNext();) {
                String string = it.next();
                System.out.println("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
                System.out.println(string);
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static List<String> preExeSQL(String sql) {
        List<String> sqlList = new LinkedList<String>();
        String[] temp = sql.split(";");
        String t = null;
        for (int i = 0; i < temp.length; i++) {
            if (t == null) {
                t = temp[i];
            } else {
                t = t + ";" + temp[i];
            }
            System.out.println(t);
            if (t.matches("[\\s\\S]*[\\W]+BEGIN[\\W]+[\\s\\S]*")) {
                if (t.matches("[\\s\\S]*[\\W]+END[\\W]*[\\s\\S]*")) {
                    sqlList.add(t.trim());
                    t = null;
                }
            } else {
                sqlList.add(t.trim());
                t = null;
            }
        }
        return sqlList;
    }

    private static String convertStreamToString(InputStream is) {
        return new Scanner(is).useDelimiter("\\A").next();
    }
}
