package com.zethria.core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LFF implements CommandExecutor {
	
	private Main plugin;
	
	public LFF(Main plugin) {
		this.plugin = plugin;
	}
	
	private String return_placeholder(String s, Player p) {
        return s.contains("%player%") ? s.replace("%player%", p.getName()) : s;
    }
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Lff") && player.hasPermission("zCore.lff")) {
				Bukkit.broadcastMessage(color("&8&l&m--------------------"));
				Bukkit.broadcastMessage(color(return_placeholder("&c%player% &7is looking for a Faction!", player)));
				Bukkit.broadcastMessage(color("&8&l&m--------------------"));
			} else if(!player.hasPermission("zCore.lff")){
				player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		return false;
	}

}
