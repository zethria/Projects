package com.zethria.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class BlessCommand implements CommandExecutor {
	
private Main plugin;
	
	public BlessCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	private String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("Bless") && player.hasPermission("zCore.bless")) {
				if(player.hasPotionEffect(PotionEffectType.BLINDNESS)) {
					player.removePotionEffect(PotionEffectType.BLINDNESS);
				} if(player.hasPotionEffect(PotionEffectType.CONFUSION)) {
					player.removePotionEffect(PotionEffectType.CONFUSION);
				} if(player.hasPotionEffect(PotionEffectType.POISON)) {
					player.removePotionEffect(PotionEffectType.POISON);
				} if(player.hasPotionEffect(PotionEffectType.SLOW)) {
					player.removePotionEffect(PotionEffectType.SLOW);
				} if(player.hasPotionEffect(PotionEffectType.SLOW_DIGGING)) {
					player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
				} if(player.hasPotionEffect(PotionEffectType.WITHER)) {
					player.removePotionEffect(PotionEffectType.WITHER);
				} if(player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
					player.removePotionEffect(PotionEffectType.WEAKNESS);
				}
				player.sendMessage(color(plugin.getConfig().getString("Messages.Prefix") + " &7You have been blessed! all negative effects removed"));
			} else if(!player.hasPermission("zCore.bless")) {
				player.sendMessage(color(plugin.getConfig().getString("Messages.Error")));
			}
		}
		
		return false;
	}

}
