/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Yutian
 */
public class SHA2 {

    public static void main(String... args) {
        System.err.println(DigestUtils.sha256Hex("刘小朵").toUpperCase());

//        System.err.println(Crypt.crypt("secret", "12345678"));
//        System.err.println(Crypt.crypt("secret", "12777777"));
//        System.err.println(Crypt.crypt("secret", "13388888"));
    }
}
