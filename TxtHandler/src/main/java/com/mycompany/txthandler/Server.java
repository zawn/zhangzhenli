package com.mycompany.txthandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import java.net.URISyntaxException;

/**
 *
 * @author Administrator
 */
public class Server {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        print(ClassLoader.getSystemResource("").getPath());
        print(ClassLoader.getSystemResource("").toURI());


        File dir = new File(ClassLoader.getSystemResource("").toURI());
        File f = new File(dir, "Server.bat");
        if (f.exists() && f.canExecute()) {
            out.println(f.getAbsoluteFile());
        }

        String path = f.getAbsolutePath();
        out.println(path);
        Runtime run = Runtime.getRuntime();
        try {
            Process process = run.exec("cmd /k start " + path);
            InputStream in = process.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
            in.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void print(Object resource) {
        System.out.println(resource);
    }
}
