/**
(C) Copyright 2011 CraftFire <dev@craftfire.com>
Contex <contex@craftfire.com>, Wulfspider <wulfspider@craftfire.com>

This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/
or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
**/

package com.authdb.util;

import java.io.File;

import org.bukkit.util.config.Configuration;

//import com.ensifera.animosity.craftirc.CraftIRC;

public class Config {
    public static boolean database_ison, authdb_enabled = true;
    public static boolean has_badcharacters;
    public static boolean hasForumBoard,capitalization;
    public static boolean hasBackpack = false, hasSpout = false, hasBuildr = false;
    public static boolean onlineMode = true;

    public static boolean database_keepalive;
    public static String database_type, database_username,database_password,database_port,database_host,database_database,dbDb;

    public static boolean autoupdate_enable,debug_enable,usagestats_enabled,logging_enabled;
    public static String language_commands, language_messages, logformat;

    public static String script_name,script_version,script_salt,script_tableprefix;
    public static boolean script_updatestatus;

    public static String custom_table,custom_userfield,custom_passfield,custom_encryption,custom_emailfield;
    public static boolean custom_enabled,custom_autocreate,custom_salt, custom_emailrequired;

    public static boolean register_enabled,register_force;
    public static String register_delay_length,register_delay_time,register_timeout_length,register_timeout_time,register_show_length,register_show_time;
    public static int register_delay,register_timeout,register_show;

    public static boolean login_enabled;
    public static String login_method,login_tries,login_action,login_delay_length,login_delay_time,login_timeout_length,login_timeout_time,login_show_length,login_show_time;
    public static int login_delay,login_timeout,login_show;

    public static boolean link_enabled,link_rename;

    public static boolean unlink_enabled,unlink_rename;

    public static String username_minimum,username_maximum;

    public static String password_minimum,password_maximum;

    public static boolean session_protect, session_enabled;
    public static String session_time,session_thelength,session_start;
    public static int session_length;

    public static boolean guests_commands,guests_movement,guests_inventory,guests_drop,guests_pickup,guests_health,guests_mobdamage,guests_interact,guests_build,guests_destroy,guests_chat,guests_mobtargeting,guests_pvp;

    public static boolean protection_notify, protection_freeze;
    public static int protection_notify_delay, protection_freeze_delay;
    public static String protection_notify_delay_time, protection_notify_delay_length, protection_freeze_delay_time, protection_freeze_delay_length;

    public static String filter_action,filter_username,filter_password,filter_whitelist="";

    public static boolean geoip_enabled;

    public static boolean CraftIRC_enabled;
    public static String CraftIRC_tag,CraftIRC_prefix;

    public static boolean CraftIRC_messages_enabled,CraftIRC_messages_welcome_enabled,CraftIRC_messages_register_enabled,CraftIRC_messages_unregister_enabled,CraftIRC_messages_login_enabled,CraftIRC_messages_email_enabled,CraftIRC_messages_username_enabled,CraftIRC_messages_password_enabled,CraftIRC_messages_idle_enabled;

    public static String commands_user_register,commands_user_link,commands_user_unlink,commands_user_login,commands_user_logout;
    public static String commands_admin_login, commands_admin_logout, commands_admin_reload;
    public static String aliases_user_register,aliases_user_link,aliases_user_unlink,aliases_user_login,aliases_user_logout;
    public static String aliases_admin_login, aliases_admin_logout, aliases_admin_reload;

      public static Configuration template = null;

      public Config(String config, String directory, String filename) {
          template = new Configuration(new File(directory, filename));
          template.load();
            if (config.equalsIgnoreCase("basic")) {

                language_commands = getConfigString("plugin.language.commands", "English");
                language_messages = getConfigString("plugin.language.messages", "English");
                autoupdate_enable = getConfigBoolean("plugin.autoupdate", true);
                debug_enable = getConfigBoolean("plugin.debugmode", false);
                usagestats_enabled = getConfigBoolean("plugin.usagestats", true);
                logformat = getConfigString("plugin.logformat", "yyyy-MM-dd");
                logging_enabled = getConfigBoolean("plugin.logging", true);

                database_type =  getConfigString("database.type", "mysql");
                database_username =  getConfigString("database.username", "root");
                database_password =  getConfigString("database.password", "");
                database_port =  getConfigString("database.port", "3306");
                database_host =  getConfigString("database.host", "localhost");
                database_database = getConfigString("database.name", "forum");
                database_keepalive = getConfigBoolean("database.keepalive", false);
                dbDb = "jdbc:mysql://" + database_host + ":" + database_port + "/" + database_database;

                script_name = getConfigString("script.name", "phpbb").toLowerCase();
                script_version = getConfigString("script.version", "3.0.8");
                script_tableprefix = getConfigString("script.tableprefix", "");
                script_updatestatus = getConfigBoolean("script.updatestatus", true);
                script_salt = getConfigString("script.salt", "");

            } else if (config.equalsIgnoreCase("advanced")) {

                custom_enabled = getConfigBoolean("customdb.enabled", false);
                custom_autocreate = getConfigBoolean("customdb.autocreate", true);
                custom_emailrequired = getConfigBoolean("customdb.emailrequired", false);
                custom_table = getConfigString("customdb.table", "authdb_users");
                custom_userfield = getConfigString("customdb.userfield", "username");
                custom_passfield = getConfigString("customdb.passfield", "password");
                custom_emailfield = getConfigString("customdb.emailfield", "email");
                custom_encryption = getConfigString("customdb.encryption", "md5").toLowerCase();

                register_enabled = getConfigBoolean("register.enabled", true);
                register_force = getConfigBoolean("register.force", true);
                register_delay_length = Util.split(getConfigString("register.delay", "4 seconds"), " ")[0];
                register_delay_time = Util.split(getConfigString("register.delay", "4 seconds"), " ")[1];
                register_delay = Util.toTicks(register_delay_time,register_delay_length);
                register_show_length = Util.split(getConfigString("register.show", "10 seconds"), " ")[0];
                register_show_time = Util.split(getConfigString("register.show", "10 seconds"), " ")[1];
                register_show = Util.toSeconds(register_show_time,register_show_length);
                register_timeout_length = Util.split(getConfigString("register.timeout", "3 minutes"), " ")[0];
                register_timeout_time = Util.split(getConfigString("register.timeout", "3 minutes"), " ")[1];
                register_timeout = Util.toTicks(register_timeout_time,register_timeout_length);

                login_method = getConfigString("login.method", "prompt");
                login_tries = getConfigString("login.tries", "3");
                login_action = Util.getAction(getConfigString("filter.action", "kick").toLowerCase());
                login_delay_length = Util.split(getConfigString("login.delay", "4 seconds"), " ")[0];
                login_delay_time = Util.split(getConfigString("login.delay", "4 seconds"), " ")[1];
                login_delay = Util.toTicks(login_delay_time,login_delay_length);
                login_show_length = Util.split(getConfigString("login.show", "10 seconds"), " ")[0];
                login_show_time = Util.split(getConfigString("login.show", "10 seconds"), " ")[1];
                login_show = Util.toSeconds(login_show_time,login_show_length);
                login_timeout_length = Util.split(getConfigString("login.timeout", "3 minutes"), " ")[0];
                login_timeout_time = Util.split(getConfigString("login.timeout", "3 minutes"), " ")[1];
                login_timeout = Util.toTicks(login_timeout_time,login_timeout_length);

                link_enabled = getConfigBoolean("link.enabled", true);
                link_rename = getConfigBoolean("link.rename", true);

                unlink_enabled = getConfigBoolean("unlink.enabled", true);
                unlink_rename = getConfigBoolean("unlink.rename", true);

                username_minimum = getConfigString("username.minimum", "3");
                username_maximum = getConfigString("username.maximum", "16");

                password_minimum = getConfigString("password.minimum", "6");
                password_maximum = getConfigString("password.maximum", "16");

                session_enabled = getConfigBoolean("session.enabled", false);
                session_protect = getConfigBoolean("session.protect", true);
                session_thelength = Util.split(getConfigString("session.length", "1 hour"), " ")[0];
                session_time = Util.split(getConfigString("session.length", "1 hour"), " ")[1];
                session_length = Util.toSeconds(session_time,session_thelength);
                session_start = Util.checkSessionStart(getConfigString("session.start", "login"));

                guests_commands = getConfigBoolean("guest.commands", false);
                guests_movement = getConfigBoolean("guest.movement", false);
                guests_inventory = getConfigBoolean("guest.inventory", false);
                guests_drop = getConfigBoolean("guest.drop", false);
                guests_pickup = getConfigBoolean("guest.pickup", false);
                guests_health = getConfigBoolean("guest.health", false);
                guests_mobdamage = getConfigBoolean("guest.mobdamage", false);
                guests_interact = getConfigBoolean("guest.interactions", false);
                guests_build = getConfigBoolean("guest.building", false);
                guests_destroy = getConfigBoolean("guest.destruction", false);
                guests_chat = getConfigBoolean("guest.chat", false);
                guests_mobtargeting = getConfigBoolean("guest.mobtargeting", false);
                guests_pvp = getConfigBoolean("guest.pvp", false);

                protection_freeze = getConfigBoolean("protection.freeze.enabled", true);
                protection_freeze_delay_length = Util.split(getConfigString("protection.freeze.delay", "2 seconds"), " ")[0];
                protection_freeze_delay_time = Util.split(getConfigString("protection.freeze.delay", "2 seconds"), " ")[1];
                protection_freeze_delay = Util.toSeconds(protection_freeze_delay_time, protection_freeze_delay_length);
                protection_notify = getConfigBoolean("protection.notify.enabled", true);
                protection_notify_delay_length = Util.split(getConfigString("protection.notify.delay", "3 seconds"), " ")[0];
                protection_notify_delay_time = Util.split(getConfigString("protection.notify.delay", "3 seconds"), " ")[1];
                protection_notify_delay = Util.toSeconds(protection_notify_delay_time, protection_notify_delay_length);

                filter_action = Util.getAction(getConfigString("filter.action", "kick").toLowerCase());
                filter_username = getConfigString("filter.username", "`~!@#$%^&*()-= + {[]}|\\:;\"<,>.?/");
                filter_password = getConfigString("filter.password", "$&\"\\");
                filter_whitelist= getConfigString("filter.whitelist", "");
            } else if (config.equalsIgnoreCase("plugins")) {

                CraftIRC_enabled = getConfigBoolean("CraftIRC.enabled", true);
                CraftIRC_tag = getConfigString("CraftIRC.tag", "admin");
                CraftIRC_prefix = getConfigString("CraftIRC.prefix", "%b%%green%[{PLUGIN}]%k%%b%");
/*
                CraftIRC_messages_enabled = getConfigBoolean("CraftIRC.messages.enabled", true);
                CraftIRC_messages_welcome_enabled = getConfigBoolean("CraftIRC.messages.welcome", true);
                CraftIRC_messages_register_enabled = getConfigBoolean("CraftIRC.messages.register", true);
                CraftIRC_messages_unregister_enabled = getConfigBoolean("CraftIRC.messages.unregister", true);
                CraftIRC_messages_login_enabled = getConfigBoolean("CraftIRC.messages.login", true);
                CraftIRC_messages_email_enabled = getConfigBoolean("CraftIRC.messages.email", true);
                CraftIRC_messages_username_enabled = getConfigBoolean("CraftIRC.messages.username", true);
                CraftIRC_messages_password_enabled = getConfigBoolean("CraftIRC.messages.password", true);
                CraftIRC_messages_idle_enabled = getConfigBoolean("CraftIRC.messages.idle", true);
                */

            } else if (config.equalsIgnoreCase("messages")) {

                Messages.time_millisecond = Config.getConfigString("Core.time.millisecond.", "millisecond");
                Messages.time_milliseconds = Config.getConfigString("Core.time.milliseconds", "milliseconds");
                Messages.time_second = Config.getConfigString("Core.time.second.", "second");
                Messages.time_seconds = Config.getConfigString("Core.time.seconds", "seconds");
                Messages.time_minute = Config.getConfigString("Core.time.minute.", "minute");
                Messages.time_minutes = Config.getConfigString("Core.time.minutes", "minutes");
                Messages.time_hour = Config.getConfigString("Core.time.hour.", "hour");
                Messages.time_hours = Config.getConfigString("Core.time.hours", "hours");
                Messages.time_day = Config.getConfigString("Core.time.day", "day");
                Messages.time_days = Config.getConfigString("Core.time.days", "days");

                Messages.AuthDB_message_reload_success = Config.getConfigString("Core.reload.success", "AuthDB has successfully reloaded!");

                Messages.AuthDB_message_database_failure = Config.getConfigString("Core.database.failure", "{RED}database connection failed! Access is denied! Contact admin.");

                Messages.AuthDB_message_register_welcome = (String)Config.getConfigString("Core.register.welcome", "{YELLOW}Welcome {WHITE}guest{YELLOW}! Please use {REGISTERCMD} password email");
                Messages.AuthDB_message_register_success = Config.getConfigString("Core.register.success", "{RED}You have been registered!");
                Messages.AuthDB_message_register_failure = Config.getConfigString("Core.register.failure", "{RED}Registration failed!");
                Messages.AuthDB_message_register_offline = Config.getConfigString("Core.register.offline", "{RED}Database is unavailable. Unable to verify password!");
                Messages.AuthDB_message_register_exists = Config.getConfigString("Core.register.exists", "{RED}You are already registered!");
                Messages.AuthDB_message_register_disabled = Config.getConfigString("Core.register.disabled", "{RED}Registration not allowed!");
                Messages.AuthDB_message_register_usage = Config.getConfigString("Core.register.usage", "{RED}Correct usage is: /register password email");
                Messages.AuthDB_message_register_timeout = Config.getConfigString("Core.register.timeout", "Kicked because you failed to register within {REGISTERTIMEOUT}.");
                Messages.AuthDB_message_register_processing = Config.getConfigString("Core.register.processing", "{YELLOW}Processing registration...");

                Messages.AuthDB_message_unregister_success = Config.getConfigString("Core.unregister.success", "{BRIGHTGREEN}Unregistered successfully!");
                Messages.AuthDB_message_unregister_failure = Config.getConfigString("Core.unregister.failure", "{RED}An error occurred while unregistering!");
                Messages.AuthDB_message_unregister_usage = Config.getConfigString("Core.unregister.usage", "{RED}Correct usage is: /unregister password");

                Messages.AuthDB_message_login_normal = Config.getConfigString("Core.login.normal", "{YELLOW}Welcome back {WHITE}{PLAYER}{YELLOW}! Please use /login password");
                Messages.AuthDB_message_login_prompt = Config.getConfigString("Core.login.prompt", "{WHITE}Welcome {TEAL}{PLAYER}{WHITE}! Please enter your password:");
                Messages.AuthDB_message_login_success = Config.getConfigString("Core.login.success", "{BRIGHTGREEN}Password accepted. Welcome!");
                Messages.AuthDB_message_login_failure = Config.getConfigString("Core.login.failure", "{RED}Password incorrect, please try again.");
                Messages.AuthDB_message_login_offline = Config.getConfigString("Core.login.offline", "{RED}Database is unavailable. Unable to verify password!");
                Messages.AuthDB_message_login_authorized = Config.getConfigString("Core.login.authorized", "{BRIGHTGREEN}You are already logged in!");
                Messages.AuthDB_message_login_notregistered = Config.getConfigString("Core.login.notregistered", "{RED}You are not registered yet!");
                Messages.AuthDB_message_login_timeout = Config.getConfigString("Core.login.timeout", "Kicked because you failed to login within {LOGINTIMEOUT}.");
                Messages.AuthDB_message_login_admin = Config.getConfigString("Core.login.admin", "You have been logged in by an admin.");
                Messages.AuthDB_message_login_admin_success = Config.getConfigString("Core.login.admin.success", "Successfully logged in player, {PLAYER}.");
                Messages.AuthDB_message_login_admin_failure = Config.getConfigString("Core.login.adminfailure", "You cannot login player {PLAYER}! That player is already logged in.");
                Messages.AuthDB_message_login_admin_notfound = Config.getConfigString("Core.login.adminnotfound", "Could not find player, {PLAYER}! Please try again.");
                Messages.AuthDB_message_login_usage = Config.getConfigString("Core.login.usage", "{RED}Correct usage is: /login password");
                Messages.AuthDB_message_login_processing = Config.getConfigString("Core.login.processing", "{YELLOW}Processing login...");
                
                Messages.AuthDB_message_logout_success = Config.getConfigString("Core.logout.success", "Successfully logged out!");
                Messages.AuthDB_message_logout_failure = Config.getConfigString("Core.logout.failure", "You are not logged in!");
                Messages.AuthDB_message_logout_admin = Config.getConfigString("Core.logout.admin", "You have been logged out by an admin.");
                Messages.AuthDB_message_logout_admin_success = Config.getConfigString("Core.logout.adminsuccess", "Successfully logged out player, {PLAYER}.");
                Messages.AuthDB_message_logout_admin_failure = Config.getConfigString("Core.logout.adminfailure", "You cannot logout player, {PLAYER}! That player is not logged in");
                Messages.AuthDB_message_logout_admin_notfound = Config.getConfigString("Core.logout.adminnotfound", "Could not find player, {PLAYER}! Please try again.");
                Messages.AuthDB_message_logout_usage = Config.getConfigString("Core.logout.usage", "{YELLOW}Correct usage is: {WHITE}{LOGOUTCMD}");
                Messages.AuthDB_message_logout_processing = Config.getConfigString("Core.logout.processing", "{YELLOW}Attempting to logout...");

                Messages.AuthDB_message_link_welcome = (String)Config.getConfigString("Core.link.welcome", "or {LINKCMD} otherusername password");
                Messages.AuthDB_message_link_success = Config.getConfigString("Core.link.success", "{BRIGHTGREEN}You have successfully linked!. You are now logged in");
                Messages.AuthDB_message_link_failure = Config.getConfigString("Core.link.failure", "{RED}Error while linking!");
                Messages.AuthDB_message_link_exists = Config.getConfigString("Core.link.exists", "{RED}You are already linked to a username!");
                Messages.AuthDB_message_link_duplicate = Config.getConfigString("Core.link.duplicate", "{RED}Username is already linked to another player!");
                Messages.AuthDB_message_link_registered = Config.getConfigString("Core.link.registered", "{RED}You cannot link as this username is already registered!");
                Messages.AuthDB_message_link_invaliduser = Config.getConfigString("Core.link.invaliduser", "{RED}You cannot link with yourself!");
                Messages.AuthDB_message_link_renamed = Config.getConfigString("Core.link.renamed", "{YELLOW}{PLAYER} has been renamed to {DISPLAYNAME}.");
                Messages.AuthDB_message_link_usage = Config.getConfigString("Core.link.usage", "{RED}Correct usage is: /link otherusername password");
                Messages.AuthDB_message_link_processing = Config.getConfigString("Core.link.processing", "{YELLOW}Attempting to link username...");

                Messages.AuthDB_message_unlink_success = Config.getConfigString("Core.unlink.success", "{BRIGHTGREEN}You have successfully unlinked!");
                Messages.AuthDB_message_unlink_failure = Config.getConfigString("Core.unlink.failure", "{RED}Error while unlinking!");
                Messages.AuthDB_message_unlink_nonexist = Config.getConfigString("Core.unlink.nonexist", "{RED}You do not have a linked username!");
                Messages.AuthDB_message_unlink_invaliduser = Config.getConfigString("Core.unlink.invaliduser", "{RED}Invalid linked username!");
                Messages.AuthDB_message_unlink_invalidpass = Config.getConfigString("Core.unlink.invalidpass", "{RED}Invalid linked password!");
                Messages.AuthDB_message_unlink_renamed = Config.getConfigString("Core.unlink.renamed", "{YELLOW}{DISPLAYNAME} has been renamed to {PLAYER}.");
                Messages.AuthDB_message_unlink_usage = Config.getConfigString("Core.unlink.usage", "{RED}Correct usage is: /unlink otherusername password");
                Messages.AuthDB_message_unlink_processing = Config.getConfigString("Core.unlink.processing", "{YELLOW}Attempting to unlink username...");
               
                Messages.AuthDB_message_email_required = Config.getConfigString("Core.email.required", "{RED}Email required for registration!");
                Messages.AuthDB_message_email_invalid = Config.getConfigString("Core.email.invalid", "{RED}Invalid email! Please try again!");
                Messages.AuthDB_message_email_badcharacters = Config.getConfigString("Core.email.badcharacters", "{RED}Email contains bad characters! {BADCHARACTERS}!");

                Messages.AuthDB_message_filter_renamed = Config.getConfigString("Core.filter.renamed", "{RED}{PLAYER} renamed to {PLAYERNEW} due to bad characters: {USERBADCHARACTERS}.");
                Messages.AuthDB_message_filter_username = Config.getConfigString("Core.filter.username", "{RED}Username contains bad characters: {USERBADCHARACTERS}!");
                Messages.AuthDB_message_filter_password = Config.getConfigString("Core.filter.password", "{RED}Password contains bad characters: {PASSBADCHARACTERS}!");
                Messages.AuthDB_message_filter_whitelist = Config.getConfigString("Core.filter.whitelist", "{BRIGHTGREEN}{PLAYER} is on the filter {WHITE}whitelist{BRIGHTGREEN}, bypassing restrictions!");

                Messages.AuthDB_message_username_minimum = Config.getConfigString("Core.username.minimum", "{RED}Your username does not meet the minimum requirement of {USERMIN} characters!");
                Messages.AuthDB_message_username_maximum = Config.getConfigString("Core.username.maximum", "{RED}Your username does not meet the maximum requirement of {USERMAX} characters!");

                Messages.AuthDB_message_password_minimum = Config.getConfigString("Core.password.minimum", "{RED}Your password does not meet the minimum requirement of {PASSMIN} characters!");
                Messages.AuthDB_message_password_maximum = Config.getConfigString("Core.password.maximum", "{RED}Your password does not meet the maximum requirement of {PASSMAX} characters!");
                Messages.AuthDB_message_password_success = Config.getConfigString("Core.password.success", "{BRIGHTGREEN}Password changed successfully!");
                Messages.AuthDB_message_password_failure = Config.getConfigString("Core.password.failure", "{RED}Error! Password change failed!");
                Messages.AuthDB_message_password_notregistered = Config.getConfigString("Core.password.notregistered", "{RED}Register first!");
                Messages.AuthDB_message_password_usage = Config.getConfigString("Core.password.usage", "{RED}Correct usage is: /password oldpassword password");

                Messages.AuthDB_message_session_valid = Config.getConfigString("Core.session.valid", "{BRIGHTGREEN}Hey, I remember you! You are logged in!");
                Messages.AuthDB_message_session_protected = Config.getConfigString("Core.session.protected", "{RED}Sorry, a player with that name is already logged in on this server.");

                Messages.AuthDB_message_protection_denied = Config.getConfigString("Core.protection.denied", "You do not have permission to use that command.");
                Messages.AuthDB_message_protection_notauthorized = Config.getConfigString("Core.protection.notauthorized", "{RED}You are not authorized to do that!");

                Messages.CraftIRC_message_status_join = Config.getConfigString("Plugins.CraftIRC.status.join", "{PLAYER} has joined the server.");
                Messages.CraftIRC_message_status_quit = Config.getConfigString("Plugins.CraftIRC.status.quit", "{PLAYER} has quit the server.");

                Messages.CraftIRC_message_register_success = Config.getConfigString("Plugins.CraftIRC.register.success", "{PLAYER} just registered successfully!");
                Messages.CraftIRC_message_register_failure = Config.getConfigString("Plugins.CraftIRC.register.failure", "{PLAYER} had some errors while registering!");
                Messages.CraftIRC_message_register_registered = Config.getConfigString("Plugins.CraftIRC.register.registered", "{PLAYER} had a lapse in memory and tried to register again.");

                Messages.CraftIRC_message_password_success = Config.getConfigString("Plugins.CraftIRC.password.success", "{PLAYER} logged in successfully!");
                Messages.CraftIRC_message_password_failure = Config.getConfigString("Plugins.CraftIRC.password.failure", "{PLAYER} tried to login with the wrong password!");

                Messages.CraftIRC_message_idle_kicked = Config.getConfigString("Plugins.CraftIRC.idle.kicked", "{PLAYER} was kicked due to bad characters in username!");
                Messages.CraftIRC_message_idle_whitelist = Config.getConfigString("Plugins.CraftIRC.idle.whitelist", "{PLAYER} is on the on bad characters whitelist, bypassing restictions!");

                Messages.CraftIRC_message_filter_renamed = Config.getConfigString("Plugins.CraftIRC.filter.renamed", "{PLAYER} renamed to {PLAYERNEW} due to bad characters.");
                Messages.CraftIRC_message_filter_kicked = Config.getConfigString("Plugins.CraftIRC.filter.kicked", "{PLAYER} was kicked due to bad characters in username!");
                Messages.CraftIRC_message_filter_whitelist = Config.getConfigString("Plugins.CraftIRC.filter.whitelist", "{PLAYER} is on the on bad characters whitelist, bypassing restictions!");

            } else if (config.equalsIgnoreCase("commands")) {
                commands_user_register = Config.getConfigString("Core.commands.user.register", "/register");
                commands_user_link = Config.getConfigString("Core.commands.user.link", "/link");
                commands_user_unlink = Config.getConfigString("Core.commands.user.unlink", "/unlink");
                commands_user_login = Config.getConfigString("Core.commands.user.login", "/login");
                commands_user_logout = Config.getConfigString("Core.commands.user.logout", "/logout");
                commands_admin_login = Config.getConfigString("Core.commands.admin.login", "/authdb login");
                commands_admin_logout = Config.getConfigString("Core.commands.admin.logout", "/authdb logout");
                commands_admin_reload = Config.getConfigString("Core.commands.admin.reload", "/authdb reload");

                aliases_user_register = Config.getConfigString("Core.aliases.user.register", "/reg");
                aliases_user_link = Config.getConfigString("Core.aliases.user.link", "/li");
                aliases_user_unlink = Config.getConfigString("Core.aliases.user.unlink", "/ul");
                aliases_user_login = Config.getConfigString("Core.aliases.user.login", "/l");
                aliases_user_logout = Config.getConfigString("Core.aliases.user.logout", "/lo");
                aliases_admin_login = Config.getConfigString("Core.aliases.admin.login", "/al");
                aliases_admin_logout = Config.getConfigString("Core.aliases.admin.logout", "/alo");
                aliases_admin_reload = Config.getConfigString("Core.aliases.admin.reload", "/ar");
            }
      }

      public static String getConfigString(String key, String defaultvalue) {
        return template.getString(key, defaultvalue);
      }

      public static boolean getConfigBoolean(String key, boolean defaultvalue) {
        return template.getBoolean(key, defaultvalue);
      }

      public void deleteConfigValue(String key) {
        template.removeProperty(key);
      }

      public String raw(String key, String line) {
        return template.getString(key, line);
      }

      public void save(String key, String line) {
          template.setProperty(key, line);
      }
}
