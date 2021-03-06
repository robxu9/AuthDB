/**
(C) Copyright 2011 CraftFire <dev@craftfire.com>
Contex <contex@craftfire.com>, Wulfspider <wulfspider@craftfire.com>

This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/
or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
**/

package com.authdb.scripts;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.authdb.util.Config;
import com.authdb.util.encryption.Encryption;
import com.authdb.util.Util;
import com.authdb.util.databases.MySQL;

public class Custom {
    public static void adduser(String player, String email, String password, String ipAddress) throws SQLException {
        if (!Config.database_keepalive) { 
            Util.databaseManager.connect();
        }
        PreparedStatement ps;
        if (Config.custom_encryption != null) {
            try {
                password = Encryption.encrypt(Config.custom_encryption, password);
            } catch (NoSuchAlgorithmException e) {
                Util.logging.StackTrace(e.getStackTrace(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getFileName());
            } catch (UnsupportedEncodingException e) {
                Util.logging.StackTrace(e.getStackTrace(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getFileName());
            }
        }
        //
        String query;
        if (Config.custom_emailrequired) {
            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.custom_table + "` (`" + Config.custom_userfield + "`,`" + Config.custom_passfield + "`,`" + Config.custom_emailfield + "`)  VALUES (?,?,?)", 1);
            ps.setString(1, player); //username
            ps.setString(2, password); // password
            ps.setString(3, email); // email
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();
        } else {
            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.custom_table + "` (`" + Config.custom_userfield + "`,`" + Config.custom_passfield + "`)  VALUES (?,?)", 1);
            ps.setString(1, player); //username
            ps.setString(2, password); // password
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();
        }
        if (!Config.database_keepalive) { 
            Util.databaseManager.close();
        }
    }

    public static String hash(String player, String password) {
        return password;
    }

    public static boolean check_hash(String passwordhash, String hash) {
        try {
            if (Encryption.encrypt(Config.custom_encryption, passwordhash).equals(hash)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
        Util.logging.StackTrace(e.getStackTrace(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getFileName());
    } catch (UnsupportedEncodingException e) {
            Util.logging.StackTrace(e.getStackTrace(), Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(), Thread.currentThread().getStackTrace()[1].getClassName(), Thread.currentThread().getStackTrace()[1].getFileName());
        }
        return false;
    }

    public static String saltIt(String password) {
        return password;
    }
}
