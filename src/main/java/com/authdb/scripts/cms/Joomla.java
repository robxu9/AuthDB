/**
(C) Copyright 2011 CraftFire <dev@craftfire.com>
Contex <contex@craftfire.com>, Wulfspider <wulfspider@craftfire.com>

This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/
or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
**/

package com.authdb.scripts.cms;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.authdb.util.Config;
import com.authdb.util.Util;
import com.authdb.util.encryption.Encryption;
import com.authdb.util.databases.MySQL;

public class Joomla {
    public static String Name = "joomla";
    public static String ShortName = "jos";
    public static String VersionRange = "1.5.0-1.5.23";
    public static String VersionRange2 = "1.6.0-1.6.1";
    public static String LatestVersionRange = VersionRange2;

    public static void adduser(int checkid, String player, String email, String password, String ipAddress) throws SQLException {
        long timestamp = System.currentTimeMillis()/1000;
        if (checkid == 1) {
            String hash = hash(player, password);
            String passworddate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date (timestamp*1000));
            //
            PreparedStatement ps;
            //
            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.script_tableprefix + "users" + "` (`name`, `username`, `email`, `password`, `usertype`, `block`, `gid`, `registerDate`, `lastvisitDate`, `params`)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
            ps.setString(1, player); // name
            ps.setString(2, player); // username
            ps.setString(3, email); // email
            ps.setString(4, hash); // password
            ps.setString(5, "Registered"); // usertype
            ps.setInt(6, 0); // block
            ps.setInt(7, 18); // gid
            ps.setString(8, passworddate); // registerDate
            ps.setString(9, passworddate); // lastvisitDate
            // fake:
            ps.setString(10, ""); // params
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();

            int userid = MySQL.countitall(Config.script_tableprefix + "users");

            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.script_tableprefix + "core_acl_aro" + "` (`section_value`, `value`, `name`)  VALUES (?, ?, ?)", 1);
            ps.setString(1, "users"); // section_value
            ps.setInt(2, userid); // value
            ps.setString(3, player); // name
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();

            int aroid = MySQL.countitall(Config.script_tableprefix + "core_acl_aro");
            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.script_tableprefix + "core_acl_groups_aro_map" + "` (`group_id`, `aro_id`)  VALUES (?, ?)", 1);
            ps.setInt(1, 18); // group_id
            ps.setInt(2, aroid); // aro_id
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();
        } else if (checkid == 2) {
            String hash = hash(player, password);
            String passworddate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date (timestamp*1000));
            //
            PreparedStatement ps;
            //
            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.script_tableprefix + "users" + "` (`name`, `username`, `email`, `password`, `usertype`, `block`, `registerDate`, `lastvisitDate`, `params`)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", 1);
            ps.setString(1, player); // name
            ps.setString(2, player); // username
            ps.setString(3, email); // email
            ps.setString(4, hash); // password
            ps.setString(5, ""); // usertype
            ps.setInt(6, 0); // block
            ps.setString(7, passworddate); // registerDate
            ps.setString(8, passworddate); // lastvisitDate
            // fake:
            ps.setString(9, ""); // params
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();

            int userid = MySQL.countitall(Config.script_tableprefix + "users");

            ps = MySQL.mysql.prepareStatement("INSERT INTO `" + Config.script_tableprefix + "user_usergroup_map" + "` (`user_id`, `group_id`)  VALUES (?, ?)", 1);
            ps.setInt(1, userid); // user_id
            ps.setInt(2, 2); // group_id
            Util.logging.mySQL(ps.toString());
            ps.executeUpdate();
            ps.close();
        }
    }

    public static boolean check_hash(String passwd, String dbEntry) {
        if (passwd == null || dbEntry == null || dbEntry.length() == 0)
        throw new IllegalArgumentException();
        String[] arr = dbEntry.split(":", 2);
        if (arr.length == 2) {
            // new format as {HASH}:{SALT}
            String cryptpass = arr[0];
            String salt = arr[1];
            return Encryption.md5(passwd + salt).equals(cryptpass);
        } else {
            // old format as {HASH} just like PHPbb and many other apps
            String cryptpass = dbEntry;
            return Encryption.md5(passwd).equals(cryptpass);
        }
    }

    static Random _rnd;

    public static String hash(String username, String passwd) {
        StringBuffer saltBuf = new StringBuffer();
        if (_rnd == null) _rnd = new SecureRandom(); {
            int i;
            for (i = 0; i < 32; i++) {
                saltBuf.append(Integer.toString(_rnd.nextInt(36), 36));
            }
            String salt = saltBuf.toString();

            return Encryption.md5(passwd + salt) + ":" + salt;
        }
    }
}
