/**          (C) Copyright 2011 Contex <contexmoh@gmail.com>
	
	This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
**/
package com.authdb.scripts.forum;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import com.authdb.util.Config;
import com.authdb.util.Encryption;
import com.authdb.util.Util;
import com.authdb.util.databases.MySQL;


public class SMF {
	
	public static boolean check(int checkid)
	{
		if(checkid == 1)
		{
			String name = Config.Script2_name;
			String latest = Config.Script2_latest;
			String[] versions = new String[] {Config.Script2_versions};
			String Version = Util.CheckVersion(name,latest, 3);
			if(Arrays.asList(versions).contains(Version))
			{
				if(Config.debug_enable) Util.Debug("Version: "+Version+" is in the list over supported versions of this script ("+name+")");
				return true;
			}
			else 
			{ 
				Util.Log("warning","Version: "+Version+" is NOT in the list over supported versions of this script ("+name+")"); 
				return false;
			}
		}
		else if(checkid == 2)
		{
			String name = Config.Script2_name;
			String latest = Config.Script2_latest2;
			String[] versions = new String[] {Config.Script2_versions2};
			String Version = Util.CheckVersion(name,latest, 3);
			if(Arrays.asList(versions).contains(Version))
			{
				if(Config.debug_enable) Util.Debug("Version: "+Version+" is in the list over supported versions of this script ("+name+")");
				return true;
			}
			else 
			{ 
				Util.Log("warning","Version: "+Version+" is NOT in the list over supported versions of this script ("+name+")"); 
				return false;
			}
		}
		return false;
	}
  public static void adduser(String player, String email, String password, String ipAddress) throws SQLException
  {
	long timestamp = System.currentTimeMillis()/1000;
	if(check(1))
	{
		String hash = hash(player,password);
		int userid;
		//
		PreparedStatement ps;
		//
		ps = MySQL.mysql.prepareStatement("INSERT INTO `"+Config.database_prefix+"members"+"` (`memberName`,`dateRegistered`,`lastLogin`,`realName`,`passwd`,`emailAddress`,`memberIP`,`memberIP2`,`lngfile`,`buddy_list`,`pm_ignore_list`,`messageLabels`,`personalText`,`websiteTitle`,`websiteUrl`,`location`,`ICQ`,`MSN`,`signature`,`avatar`,`usertitle`,`secretQuestion`,`additionalGroups`)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 1);
	    ps.setString(1, player); //memberName
	    ps.setLong(2, timestamp); //dateRegistered
	    ps.setLong(3, timestamp); //lastLogin
	    ps.setString(4, player); //realName
		ps.setString(5, hash); //passwd
		ps.setString(6, email); //emailAddress
		ps.setString(7, ipAddress); //memberIP
		ps.setString(8, ipAddress); //memberIP2
		///need to add these, it's complaining about not default is set.
		ps.setString(9, ""); //lngfile
		ps.setString(10, ""); //buddy_list
		ps.setString(11, ""); //pm_ignore_list
		ps.setString(12, ""); //messageLabels
		ps.setString(13, ""); //personalText
		ps.setString(14, ""); //websiteTitle
		ps.setString(15, ""); //websiteUrl
		ps.setString(16, ""); //location
		ps.setString(17, ""); //ICQ
		ps.setString(18, ""); //MSN
		ps.setString(19, ""); //signature
		ps.setString(20, ""); //avatar
		ps.setString(21, ""); //usertitle
		ps.setString(22, ""); //secretQuestion
		ps.setString(23, ""); //additionalGroups
		ps.executeUpdate();
		
		userid = MySQL.countitall(Config.database_prefix+"members");
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + player + "' WHERE `variable` = 'latestRealName'");
		ps.executeUpdate();
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + userid + "' WHERE `variable` = 'latestMember'");
		ps.executeUpdate();
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + timestamp + "' WHERE `variable` = 'memberlist_updated'");
		ps.executeUpdate();
	    ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = value+1 WHERE `variable` = 'totalMembers'");
	    ps.executeUpdate();
	}
	else if(check(2))
	{
		String hash = hash(player,password);
		int userid;
		//
		PreparedStatement ps;
		///
		ps = MySQL.mysql.prepareStatement("INSERT INTO `"+Config.database_prefix+"members"+"` (`member_name`,`date_registered`,`last_login`,`real_name`,`passwd`,`email_address`,`member_ip`,`member_ip2`,`lngfile`,`buddy_list`,`pm_ignore_list`,`message_labels`,`personal_text`,`website_title`,`website_url`,`location`,`icq`,`msn`,`signature`,`avatar`,`usertitle`,`secret_question`,`additional_groups`,`openid_uri`,`ignore_boards`)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 1);
	    ps.setString(1, player); //member_name
	    ps.setLong(2, timestamp); //date_registered
	    ps.setLong(3, timestamp); //last_login
	    ps.setString(4, player); //real_name
		ps.setString(5, hash); //passwd
		ps.setString(6, email); //email_address
		ps.setString(7, ipAddress); //memberIP
		ps.setString(8, ipAddress); //memberIP2
		//need to add these, it's complaining about not default is set.
		ps.setString(9, ""); //lngfile
		ps.setString(10, ""); //buddy_list
		ps.setString(11, ""); //pm_ignore_list
		ps.setString(12, ""); //message_labels
		ps.setString(13, ""); //personal_text
		ps.setString(14, ""); //website_title
		ps.setString(15, ""); //website_url
		ps.setString(16, ""); //location
		ps.setString(17, ""); //ICQ
		ps.setString(18, ""); //MSN
		ps.setString(19, ""); //signature
		ps.setString(20, ""); //avatar
		ps.setString(21, ""); //usertitle
		ps.setString(22, ""); //secret_question
		ps.setString(23, ""); //additional_groups
		ps.setString(24, ""); //openid_uri
		ps.setString(25, ""); //ignore_boards
		ps.executeUpdate();
		
		userid = MySQL.countitall(Config.database_prefix+"members");
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + player + "' WHERE `variable` = 'latestRealName'");
		ps.executeUpdate();
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + userid + "' WHERE `variable` = 'latestMember'");
		ps.executeUpdate();
		ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = '" + timestamp + "' WHERE `variable` = 'memberlist_updated'");
		ps.executeUpdate();
	    ps = MySQL.mysql.prepareStatement("UPDATE `"+Config.database_prefix+"settings"+"` SET `value` = value+1 WHERE `variable` = 'totalMembers'");
	    ps.executeUpdate();
	}
  }
	
  public static String hash(String player, String password) {
	try {
		return Encryption.SHA1(player+password);
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	return "fail";
  }

	public static boolean check_hash(String passwordhash, String hash)
	{
		if(passwordhash.equals(hash)) return true;
		else return false;
	}
}