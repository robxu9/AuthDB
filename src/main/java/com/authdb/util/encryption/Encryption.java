/**
(C) Copyright 2011 CraftFire <dev@craftfire.com>
Contex <contex@craftfire.com>, Wulfspider <wulfspider@craftfire.com>

This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/
or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
**/

package com.authdb.util.encryption;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import com.authdb.util.Config;
import com.authdb.util.Util;

public class Encryption {
    public static String encrypt(String encryption,String toencrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Whirlpool whirpool = new Whirlpool();
        if (encryption.equalsIgnoreCase("md5")) {
            return md5(toencrypt);
        } else if (encryption.equalsIgnoreCase("sha1")) {
            return SHA1(toencrypt);
        } else if (encryption.equalsIgnoreCase("sha512") || encryption.equalsIgnoreCase("sha2")) {
            return SHA512(toencrypt);
        } else if (encryption.equalsIgnoreCase("whirlpool") || encryption.equalsIgnoreCase("xauth")) {
            byte[] digest = new byte[Whirlpool.DIGESTBYTES];
            whirpool.NESSIEinit();
            whirpool.NESSIEadd(toencrypt);
            whirpool.NESSIEfinalize(digest);
            String done = Whirlpool.display(digest);
            return done;
        }
        if (Config.debug_enable) {
            Util.logging.Info("Could not find encryption method: " + Config.custom_encryption + ", using default: md5");
        }
        Config.custom_encryption = "md5";
        return md5(toencrypt);
    }

    public static String hash(int length, String charset,int RangeFrom, int RangeTo) {
        if (charset.equalsIgnoreCase("none")) {
            StringBuffer salt = new StringBuffer();
            for (int i = 0; i < length; i++) {
                salt.append((char)(Util.randomNumber(RangeFrom, RangeTo)));
            }
            return salt.toString();
        } else {
            Random rand = new Random(System.currentTimeMillis());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int pos = rand.nextInt(charset.length());
                sb.append(charset.charAt(pos));
            }
            return sb.toString();
        }
    }

    public static String md5(String data) {
        try {
            byte[] bytes = data.getBytes("ISO-8859-1");
            MessageDigest md5er = MessageDigest.getInstance("MD5");
            byte[] hash = md5er.digest(bytes);
            return Util.bytes2hex(hash);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash;
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return Util.convertToHex(sha1hash);
    }

    public static String SHA256(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0'); {
                    hexString.append(hex);
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Util.logging.StackTrace(e.getStackTrace(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getFileName());
        }
        return text;
    }

    public static String SHA512(String text) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(text.getBytes("UTF-8"));
            byte[] digestBytes = messageDigest.digest();

            String hex = null;

            for (int i = 0; i < digestBytes.length; i++) {
                hex = Integer.toHexString(0xFF & digestBytes[i]);
                if (hex.length() < 2) {
                    sb.append("0");
                }
                sb.append(hex);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return new String(sb);
    }

    public static String pack(String hex) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < hex.length(); i += 2) {
            char c1 = hex.charAt(i);
            char c2 = hex.charAt(i + 1);
            char packed = (char) (Util.hexToInt(c1) * 16 + Util.hexToInt(c2));
            buf.append(packed);
        }
        return buf.toString();
    }
}
