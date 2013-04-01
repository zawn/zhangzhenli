/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.protocol.HTTP;
import sun.nio.cs.ext.ISCII91;

/**
 *
 * @author ZhangZhenli
 */
public class NewClass2 {

    public static void main(String... args) throws UnsupportedEncodingException {
//        final String value = "张";
//        final String encodedValue = value != null ? (URLEncoder.encode(value, HTTP.DEFAULT_PROTOCOL_CHARSET)) : "";
//        final String encoding = "GBK";
//        String encode = URLEncoder.encode(value,
//                encoding != null ? encoding : HTTP.DEFAULT_CONTENT_CHARSET);
//        System.out.println(encode);
//        System.out.println(URLDecoder.decode(encode, encoding));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            User user = new User();
            user.setPhoneNumber("13812345678");
            user.setUserName("张亚军");
            oos.writeObject(user);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(NewClass2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(StringTools.byteArrayToHexStringPrint(baos.toByteArray()));
        String byteArrayToHexString = StringTools.byteArrayToHexString(baos.toByteArray());
        System.out.println(byteArrayToHexString);

        ByteArrayInputStream bais = new ByteArrayInputStream(StringTools.hexStringToByteArray(byteArrayToHexString));
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            try {
                User u = (User) ois.readObject();
                System.err.println(u.toString());
                ois.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(NewClass2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(NewClass2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
