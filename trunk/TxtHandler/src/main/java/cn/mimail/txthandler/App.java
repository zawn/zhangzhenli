package cn.mimail.txthandler;

import java.awt.List;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.System.out;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter("C:/Users/Zawn/Desktop/draft-ietf-oauth-v2-26_new.txt");
            BufferedReader in = null;
            try {
                System.out.println("Hello World!");
                in = new BufferedReader(new FileReader("C:/Users/Zawn/Desktop/draft-ietf-oauth-v2-26.txt"));
                try {
                    LinkedList queue = new LinkedList();
                    String line;
                    int i = 0;
                    while ((line = in.readLine()) != null) {
                        if (line.startsWith("Hammer, et al.          Expires November 2, 2012")) {
                            i = 3;
                            if (queue.size() > 3) {
                                out.println(queue.removeLast());
                                out.println(queue.removeLast());
                                out.println(queue.removeLast());
//                                out.println(queue.remove());
                            }
                            out.println(line);
//                            printWriter.println(line);
                        } else if (i > 0) {
                            i--;
                            out.println(line);
                        } else {
                            i = 0;
                            queue.add(line);
//                            printWriter.println(line);
//                            out.println(line);
                        }
                    }
                    while (!queue.isEmpty()) {
                        printWriter.println(queue.remove());

                    }
                } catch (IOException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            printWriter.close();
        }
    }
}
