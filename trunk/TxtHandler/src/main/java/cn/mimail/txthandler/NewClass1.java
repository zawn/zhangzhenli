/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

/**
 *
 * @author Zawn
 */
public class NewClass1 {
    public static void main(String...args){
        StringBuilder sb = new StringBuilder();
        int i=0x00;
        System.out.println(String.format("%1$#010x", i));
        sb.append(String.format("%1$#010x", i));
        System.out.println(sb.toString());
        
    }
    
}
