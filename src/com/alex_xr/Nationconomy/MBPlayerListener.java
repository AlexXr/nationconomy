package com.alex_xr.Nationconomy;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

public class MBPlayerListener extends PlayerListener {
	public static MoneyBukkit plugin;

	public MBPlayerListener(MoneyBukkit instance) {
		plugin = instance;
	}

	public void onPlayerCommand(PlayerChatEvent event) {

		String[] split = event.getMessage().split(" ");

		Player player = event.getPlayer();

		if ((split[0].equalsIgnoreCase("/money"))) {
			boolean HasAccount = Accounting.containskey(player,
					MoneyBukkit.Accounts);
			if (HasAccount == false) {
				Accounting.write(player, LoadSettings.startingBalance,
						MoneyBukkit.Accounts);
			} else if (split.length == 1) {
				int balance = Accounting.getBalance(player,
						MoneyBukkit.Accounts);
				player.sendMessage(ChatColor.GREEN + "Money: " + balance
						+ LoadSettings.credit);
			}
		}
		if (split.length >= 4) {
			if (split[0].equalsIgnoreCase("/money")
					&& split[1].equalsIgnoreCase("pay")) {
				String toPlayer = split[2];
				boolean HasAccount = Accounting.containskey(player,
						MoneyBukkit.Accounts);

				if (HasAccount == true) {
					int balance = Accounting.getBalance(player,
							MoneyBukkit.Accounts);
					int amount = Integer.parseInt(split[3]);
					List<Player> players = plugin.getServer().matchPlayer(
							split[2]);
					if (players.size() == 0) {
						player.sendMessage(ChatColor.RED
								+ "No matching player.");
					} else if (players.size() != 1) {
						player.sendMessage(ChatColor.RED
								+ "Matched more than one player!  Be more specific!");
					} else {
						Player reciever = players.get(0);
						boolean ToHasAccount = Accounting.containskey(reciever,
								MoneyBukkit.Accounts);
						if (HasAccount == true && ToHasAccount == true
								&& balance >= amount) {
							int newBalance = balance - amount;
							Accounting.write(player, newBalance,
									MoneyBukkit.Accounts);

							int toBalance = Accounting.getBalance(reciever,
									MoneyBukkit.Accounts);
							int newToBalance = toBalance + amount;
							Accounting.write(reciever, newToBalance,
									MoneyBukkit.Accounts);
							player.sendMessage(ChatColor.GREEN + "" + amount
									+ LoadSettings.credit
									+ " has been successfully transacted. To "
									+ toPlayer);
							reciever.sendMessage(ChatColor.GREEN
									+ player.getName() + " has sent you "
									+ amount + LoadSettings.credit);
						} else {
							player.sendMessage(ChatColor.RED
									+ "Not enough cash.");
						}

					}

				} else {
					player.sendMessage(ChatColor.RED
							+ "You need an account to send money!");
				}
			}
		}
	}

	public void onPlayerLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		int startingvalue = LoadSettings.startingBalance;
		boolean HasAccount = Accounting.containskey(player,
				MoneyBukkit.Accounts);
		if (HasAccount == false) {
			Accounting.write(player, startingvalue, MoneyBukkit.Accounts);
		}
	}

}
