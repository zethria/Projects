package com.zethria.core;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Nightvision implements CommandExecutor {

private Main plugin;

public static List<String> nightvision = new ArrayList<>();
	
	public Nightvision(Main plugin) {
		this.plugin = plugin;
	}
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Nightvision") && !nightvision.contains(player.getName()) && player.hasPermission("zCore.nightvision")) {
				nightvision.add(player.getName());
				player.addPotionEffect(new PotionEffect(
						PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, plugin.getConfig().getInt("Settings.Nightvision")));
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") 
						+ " &7You have enabled &bnightvision!"));
			} else if(nightvision.contains(player.getName())) {
				nightvision.remove(player.getName());
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") 
						+ " &7You have disabled &bnightvision!"));
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			} else if(!player.hasPermission("zCore.nightvision")) {
				player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		return false;
	}

}
