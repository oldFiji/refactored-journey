package com.CyFij.refactoredjourney;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.CyFij.refactoredjourney.Commands.Tool;
import com.CyFij.refactoredjourney.Utils.GeneralUtils;


public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		Logger.getLogger(GeneralUtils.prefix + ChatColor.YELLOW + "Starting up!");
		// Get rtool command, and make the Tool class execute it
		getCommand("rtool").setExecutor(new Tool());
	}
	@Override
	public void onDisable() {
		Logger.getLogger(GeneralUtils.prefix + ChatColor.YELLOW + "Shutting down!");
	}
}
