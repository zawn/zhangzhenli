/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Sha2Crypt;

/**
 *
 * @author Yutian
 */
public class SHA2Test {

    private static int STREAM_BUFFER_LENGTH = 1024;

    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream data = new FileInputStream("D:\\UCD设计\\arrow.png");
        FileInputStream data1 = new FileInputStream("D:\\UCD设计\\arrow.png");
        FileInputStream data2 = new FileInputStream("D:\\UCD设计\\arrow.png");

        byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

        while (read > -1) {
            md.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
        }
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println(StringTools.byteArrayToHexString(mdbytes));

        System.out.println(DigestUtils.sha256Hex(data2));

    }
}
