/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.txthandler;

/**
 *
 * @author ZhangZhenli
 */
public class DownloadUI {

    public static void main(String[] args) {
        try {
            System.out.println(Thread.currentThread());
            DownloadUtils.instance().download(new MyDownloadListener());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyDownloadListener implements DownloadListener {

        @Override
        public void onDownloading(int progress) {
            System.out.println(Thread.currentThread());
            System.out.println("下载进度是：" + progress);
        }

        @Override
        public void onDownloaded() {
            System.out.println(Thread.currentThread());
            System.out.println("下载完成");
        }
    }
}
