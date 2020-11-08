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


public class PoisonCommand implements CommandExecutor {
	
	private static PoisonCommand pcmd = null;
	public List<String> poison = new ArrayList<>();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("Poison")) {
			if(player.hasPermission("zCore.poison") && !poison.contains(player.getName())) {
				poison.add(player.getName());
				
				player.sendMessage(ChatColor.GREEN + "Sorry for the poison dumb beaner");
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, 1));
			} else if (poison.contains(player.getName())) {
				poison.remove(player.getName());
				player.sendMessage(ChatColor.RED + "Poison has been cured");
				player.removePotionEffect(PotionEffectType.POISON);
			}
		}
		return false;
	}
	
	public static PoisonCommand pcmd() {
		if(pcmd == null) {
			pcmd = new PoisonCommand();
		}
		return pcmd;
	}

}
