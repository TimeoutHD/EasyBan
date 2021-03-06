package de.timeout.bukkit.ban.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissibleBase;

import de.timeout.bukkit.ban.BanGUI;
import de.timeout.bukkit.ban.gui.MuteCommandGUI;

public class MuteCommand implements CommandExecutor {
	
	private BanGUI main = BanGUI.plugin;
	
	private String prefix = main.getLanguage("prefix");
	private String permissions = main.getLanguage("error.permissions");
	private String self = main.getLanguage("mute.self");
	private String ignore = main.getLanguage("mute.ignore");

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("easyban.mute")) {
				if(args.length == 1) {
					String name = args[0];
					if(!p.getName().equalsIgnoreCase(name)) {
						OfflinePlayer t = Bukkit.getServer().getOfflinePlayer(name);
						PermissibleBase base = new PermissibleBase(t);
						if(!base.hasPermission("easyban.ignoremute"))MuteCommandGUI.openMuteMenu(p, name);
						else p.sendMessage(prefix + ignore);
					} else p.sendMessage(prefix + self);
				}
			} else p.sendMessage(prefix + permissions);
		}
		return false;
	}

}
