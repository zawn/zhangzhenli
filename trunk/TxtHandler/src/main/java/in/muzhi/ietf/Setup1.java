/*
 * Copyright 2013 ZhangZhenli <zhangzhenli@live.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.muzhi.ietf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.*;
import java.util.LinkedList;

/**
 * 去除文件页眉页脚
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class Setup1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
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
          ex.printStackTrace();
        }

        while (!queue.isEmpty()) {
          printWriter.println(queue.remove());
        }
      } catch (FileNotFoundException ex) {
        ex.printStackTrace();
      } finally {
        try {
          in.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } finally {
      printWriter.close();
    }
  }
}
