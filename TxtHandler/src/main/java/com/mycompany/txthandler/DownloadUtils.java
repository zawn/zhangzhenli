/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.txthandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZhangZhenli
 */
public class DownloadUtils {

    private static DownloadUtils instance = null;

    private DownloadUtils() {
    }

    public static synchronized DownloadUtils instance() {
        if (instance == null) {
            instance = new DownloadUtils();
        }
        return instance;
    }
    private boolean isDownloading = true;
    private int progress = 0;

    // 实际开发中这个函数需要传入url作为参数，以获取服务器端的安装包位置  
    public void download(final DownloadListener listener) throws InterruptedException {

        Runnable r = new Runnable() {

            public void run() {
                while (isDownloading) {
                    try {
                        System.out.println(Thread.currentThread());
                        listener.onDownloading(progress);
                        // 下载过程的简单模拟  
                        Thread.sleep(1000);
                        progress += 10;
                        if (progress >= 100) {
                            isDownloading = false;
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DownloadUtils.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // 下载完成  
                listener.onDownloaded();
            }
        };
        new Thread(r).start();
    }
}
