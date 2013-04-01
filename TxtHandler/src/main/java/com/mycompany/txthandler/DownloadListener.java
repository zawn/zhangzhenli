/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.txthandler;

/**
 *
 * @author ZhangZhenli
 */
public interface DownloadListener {  
    public void onDownloading(int progress); //下载过程中的处理函数  
    public void onDownloaded(); //下载完成的处理函数  
}