/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mimail.txthandler;

import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

/**
 *
 * @author Zawn
 */
public class NewClass {

    public static void main(String... args) throws UnsupportedEncodingException {
        try {
            //        String str = "1.0030.111";
            //        String[] split = str.split("\\.");
            //        int versionInt = 0;
            //        try {
            //            for (int i = 0; i < split.length; i++) {
            //                if (split[i].length()<=3) {
            //                    versionInt += (int) (Integer.parseInt(split[i]) * Math.pow(1000, i));
            //                }else{
            //                    throw new NumberFormatException();
            //                }
            //            }
            //        } catch (NumberFormatException ex) {
            //            versionInt = 0;
            //            Logger.getLogger(Setup1.class.getName()).log(Level.SEVERE, null, ex);
            //        }
            //        if (versionInt == 0) {
            ////            return errormessage;
            //        }
            //        
            //
            //        out.println(Arrays.toString(split));
            //        out.println(versionInt);
            //        out.println(StringTools.byteArrayToHexString(str.getBytes()));
                    String name2 = "a张振利";
                    String name = Hex.encodeHexString(Base64.encodeBase64(name2.getBytes("UTF-8")));
                    byte[] hexStringToByteArray2 = Hex.decodeHex(name.toCharArray());
                    byte[] decodeBase642 = Base64.decodeBase64(Hex.decodeHex(name.toCharArray()));
                    out.println(StringUtils.newStringUtf8(Base64.decodeBase64(Hex.decodeHex(name.toCharArray()))));
        } catch (DecoderException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
