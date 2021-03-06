package de.timeout.bungee.ban.api;

import java.util.UUID;

import de.timeout.bungee.ban.BanSystem;
import de.timeout.bungee.ban.filemanager.DecidationManager;
import de.timeout.utils.Reason;
import de.timeout.utils.Reason.Stage;

public class MuteEvent extends PunishEvent {
	
	private static BanSystem main = BanSystem.plugin;
	private static String perma = main.getLanguage("mute.perma");
	private static String custom = main.getLanguage("mute.custom");

	public MuteEvent(String ip, UUID uuid, String name, String banner) {
		super(ip, uuid, name, banner, null, perma, -1L);
	}
	
	public MuteEvent(String ip, UUID uuid, String name, String banner, Reason reason) {
		super(ip, uuid, name, banner, reason, reason.getName(), 0L);
		long violences = DecidationManager.getPlayerViolences(uuid);
		Stage stage = reason.getValidStage(violences);
		if(stage == Stage.THIRD)duration = reason.getThirdtime();
		else if(stage == Stage.SECOND)duration = reason.getSecondtime();
		else duration = reason.getFirsttime();
		
	}
	
	public MuteEvent(String ip, UUID uuid, String name, String banner, long duration) {
		super(ip, uuid, name, banner, null, custom, duration);
	}
}
