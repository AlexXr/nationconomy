package com.alex_xr.Nationconomy;

import java.io.File;
import java.io.IOException;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MoneyBukkit extends JavaPlugin {
	private final MBPlayerListener playerListener = new MBPlayerListener(this);
	static String maindirectory = "MoneyBukkit/";
	static File Accounts = new File(maindirectory + "Users.accounts");

	public MoneyBukkit(PluginLoader pluginLoader, Server instance,
			PluginDescriptionFile desc, File folder, File plugin,
			ClassLoader cLoader) {
		
	}

	public void onEnable() {
		new File(maindirectory).mkdirs();
		
		if (!Accounts.exists())
			try {
				Accounts.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		LoadSettings.loadMain();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_COMMAND, this.playerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_LOGIN, this.playerListener,
				Event.Priority.Normal, this);
		PluginDescriptionFile pdfFile = getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled!");
	}

	public void onDisable() {
	}

}