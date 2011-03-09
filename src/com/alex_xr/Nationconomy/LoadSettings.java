package com.alex_xr.Nationconomy;

public class LoadSettings {
	static int startingBalance;
	static String credit;

	public static void loadMain() {
		String propertiesFile = MoneyBukkit.maindirectory
				+ "MainConfig.properties";
		PluginProperties properties = new PluginProperties(propertiesFile);
		properties.load();

		startingBalance = properties.getInteger("Starting-Balance", 1000);
		credit = properties.getString("Currency", "coins");
		properties.save("===MoneyBukkit Main Configuration===");

	}

}
