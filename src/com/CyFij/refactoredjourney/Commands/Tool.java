package com.CyFij.refactoredjourney.Commands;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.CyFij.refactoredjourney.Utils.GeneralUtils;

import net.md_5.bungee.api.ChatColor;

public class Tool implements CommandExecutor,Listener{
	
	// Create variables to represent the positions
	public static Location posOne;
	public static Location posTwo;
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Check first if the sender is a player
		if (!(sender instanceof Player)) {
			sender.sendMessage(GeneralUtils.prefix + ChatColor.RED + "You must be a player to use this!");
			// Return so no more code executes
			return true;
		}
		// Create a player by turning the sender into a player
		Player p = (Player) sender;
		// If player doesn't have permission
		if (!(p.hasPermission("refactoredjourney.tool"))) {
			p.sendMessage(GeneralUtils.prefix + ChatColor.RED + "You do not have permission to use this!");
			return true;
		}
		// Save their old item
		ItemStack oldItem = p.getItemInHand();
		
		// Set the item in hand to the tool, and add the old item somewhere else
		p.setItemInHand(createTool());
		p.getInventory().addItem(oldItem);
		
		// Complete the command
		return true;
	}
	
	public static ItemStack createTool() {
		// Create the actual item
		ItemStack i = new ItemStack(Material.IRON_AXE, 1);
		// Get the meta from the item
		ItemMeta iMeta = i.getItemMeta();
		// Set name and description
		iMeta.setDisplayName(ChatColor.BLUE + "Tool");
		iMeta.setLore(Arrays.asList(ChatColor.AQUA + "Mark II"));
		// Set the meta after changes
		i.setItemMeta(iMeta);
		// Return with the tool
		return i;
	}
	
	// Create an event for interactions with the tool
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		// Just create a player variable
		Player p = e.getPlayer();
		
		// If the item is NOT the tool
		if (!(e.getItem().equals(createTool()))) {
			// Just return
			return;
		}
		
		// If the player doesn't have the position, stop them
		if (!(p.hasPermission("refactoredjourney.tool"))) {
			p.sendMessage(GeneralUtils.prefix + ChatColor.RED + "You do not have permission to use this!");
			return;
		}
		
		// If the tool right clicks a block
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			// Cancel the event, so nothing happens
			e.setCancelled(true);
			p.sendMessage(GeneralUtils.prefix + ChatColor.YELLOW + "Set position two!");
			// Set position two to the block's location
			posTwo = e.getClickedBlock().getLocation();
			return;
		}
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			// Cancel the event, so nothing happens, in this case, block break
			e.setCancelled(true);
			p.sendMessage(GeneralUtils.prefix + ChatColor.YELLOW + "Set position one!");
			// Set position one to the block's location
			posOne = e.getClickedBlock().getLocation();
			return;
		}
	}
}
