package com.alex_xr.Nationconomy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.bukkit.entity.Player;

public class Accounting {
	public static void write(Player p, int startingB, File file) {
		Properties pro = new Properties();
		String startingvalue = (new Integer(startingB)).toString();
		String player = p.getName();
		try {
			FileInputStream in = new FileInputStream(file);
			pro.load(in);
			pro.setProperty(player, startingvalue);
			pro.store(new FileOutputStream(file), null);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static int getBalance(Player p, File file) {
		Properties pro = new Properties();
		String player = p.getName();
		try {
			FileInputStream in = new FileInputStream(file);
			pro.load(in);
			String string = pro.getProperty(player);
			int balance = Integer.parseInt(string);
			return balance;
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		return 0;
	}

	public static boolean containskey(Player p, File file) {
		Properties pro = new Properties();
		String player = p.getName();
		try {
			FileInputStream in = new FileInputStream(file);
			pro.load(in);

			if (pro.containsKey(player))
				return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
