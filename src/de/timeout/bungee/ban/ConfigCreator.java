package de.timeout.bungee.ban;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.plugin.Plugin;

public class ConfigCreator {
	
	private static BanSystem main = BanSystem.plugin;
	
	public static void loadConfigurations() {
		loadResource(main, "bungee/config.yml");
		loadResource(main, "bungee/language/de_DE.yml");
		loadResource(main, "bungee/language/en_US.yml");
	}
	
	private static void loadResource(Plugin plugin, String resource) {
		File folder = main.getDataFolder();
		if(!folder.exists())folder.mkdirs();
		File resourceFile = new File(folder, resource);
		try {
			if(resource.contains("bungee/")) {
				String[] folders = resource.substring(7).split("/");
				resourceFile = plugin.getDataFolder();
				for(int i = 0; i < folders.length -1; i++) {
					resourceFile = new File(resourceFile, folders[i]);
					if(!resourceFile.exists())resourceFile.mkdirs();
				}
				resourceFile = new File(resourceFile, folders[folders.length -1]);
			}
			if(!resourceFile.exists()) {
				resourceFile.createNewFile();
				try(InputStream in = plugin.getResourceAsStream(resource);
						OutputStream out = new FileOutputStream(resourceFile)) {
							ByteStreams.copy(in, out);
				}
			}
		} catch(IOException e) {}
	}
}
