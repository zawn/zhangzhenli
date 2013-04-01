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
import static java.lang.System.out;
import java.util.Stack;

/**
 * 去除文件中的无用换行符
 *
 * @author ZhangZhenli <zhangzhenli@live.com>
 */
public class Setup2 {

  // 行长度
  private static int LENGTH = 72;
  private static String INFILE = "C:/Users/Yutian/Desktop/rfc6749_setup1.txt";
  private static String OUTFILE = "C:/Users/Yutian/Desktop/rfc6749_setup2.txt";

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws FileNotFoundException, IOException {
    LENGTH = getLineLength(INFILE);
    out.println(LENGTH);
    BufferedReader in = new BufferedReader(new FileReader(INFILE));
    PrintWriter pw = new PrintWriter("C:/Users/Yutian/Desktop/rfc6749_setup2.txt");
    String line;
    int length = 0;
    Stack<String> stack = new Stack();
    Stack<String> stackDist = new Stack();
    while ((line = in.readLine()) != null) {
      stack.add(line);
    }
    while (stack.size() >= 2) {
      String[] newLine = mergerInvalidLine(stack.pop(), stack.pop());
      stack.add(newLine[0]);
      if (newLine[1] != null) {
        stackDist.add(newLine[1]);
      }
    }
    stackDist.add(stack.pop());
    while (!stackDist.empty()) {
      pw.println(stackDist.pop());
    }
    pw.close();
  }

  private static int getLineLength(String inFile) throws FileNotFoundException, IOException {
    BufferedReader in = new BufferedReader(new FileReader(inFile));
    String line;
    int length = 0;
    while ((line = in.readLine()) != null) {
      length = line.length() > length ? line.length() : length;
    }
    return length;
  }

  private static String[] mergerInvalidLine(String s2, String s1) {
    out.println("______________________________________");
    out.print(s1);
    out.println("^");
    out.print(s2);
    out.println("^");
    String[] result = new String[2];
    int[] index1 = getLineFirstWordIndex(s1);
    int[] index2 = getLineFirstWordIndex(s2);
    if (index1[0] > index2[0] || (index1[2] + 1 + index2[1]) <= LENGTH) {
      return doNotMerger(s1, s2);
    } else {
      String f1 = s1.substring(index1[0], index1[0] + index1[1]);
      String f2 = s2.substring(index2[0], index2[0] + index2[1]);
      char charAt = f1.charAt(0);
      if (charAt < 0x41 || (charAt > 0x5A && charAt < 0x61) || charAt > 0x7A) {
        char charAt2 = f2.charAt(0);
        if (charAt2 < 0x41 || (charAt2 > 0x5A && charAt2 < 0x61) || charAt2 > 0x7A) {
          return doNotMerger(s1, s2);
        }
      }
      if (f1.contains(".") && f2.contains(".")) {
        String[] split1 = f1.split(".");
        String[] split2 = f2.split(".");
        if (split1.length == split2.length) {
          for (int i = 0; i < split1.length - 1; i++) {
            if (!split1[i].equals(split2[i])) {
              break;
            }
          }
          return doNotMerger(s1, s2);
        } else if (split1.length < split2.length) {
          for (int i = 0; i < split1.length; i++) {
            if (!split1[i].equals(split2[i])) {
              break;
            }
          }
          return doNotMerger(s1, s2);
        } else if (split1.length > split2.length) {
          for (int i = 0; i < split2.length - 1; i++) {
            if (!split1[i].equals(split2[i])) {
              break;
            }
          }
          return doNotMerger(s1, s2);
        }
      }
    }
    result[0] = s1 + " " + s2.substring(index2[0]);
    out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    return result;
  }

  private static String[] doNotMerger(String s1, String s2) {
    String[] result = new String[2];
    result[0] = s1;
    result[1] = s2;
    out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    return result;
  }

  private static int[] getLineFirstWordIndex(String s) {
    if (s == null) {
      return new int[]{0, 0, 0};
    }
    byte[] bytes = s.getBytes();
    int[] j = {0, 0, 0};
    while (j[0] < bytes.length && bytes[j[0]] <= 0x20) {
      j[0]++;
    }
    j[1] = j[0];
    while (j[1] < bytes.length && bytes[j[1]] > 0x20 && bytes[j[1]] <= 0x7E) {
      j[1]++;
    }
    j[1] = j[1] - j[0];
    j[2] = bytes.length;
    return j;
  }
}
