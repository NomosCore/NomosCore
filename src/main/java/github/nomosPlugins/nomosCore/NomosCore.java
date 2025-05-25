package github.nomosPlugins.nomosCore;

import github.nomosPlugins.nomosCore.commands.AdminPanel;
import github.nomosPlugins.nomosCore.commands.DeveloperCommand;
import github.nomosPlugins.nomosCore.commands.FeedCommand;
import github.nomosPlugins.nomosCore.commands.HealCommand;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class NomosCore extends JavaPlugin {

    private FileConfiguration lang;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadLanguageFile();

        getCommand("adminpanel").setExecutor(new AdminPanel(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("develop").setExecutor(new DeveloperCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getLogger().info("NomosCore is successfully enabled!");
    }

    public String getPrefix() {
        return ChatColor.GOLD + "" + ChatColor.BOLD + "NomosCore " +
                ChatColor.RESET + ChatColor.GRAY + "›› " + ChatColor.RESET;
    }

    private void loadLanguageFile() {
        String langCode = getConfig().getString("language", "en");
        File langFile = new File(getDataFolder(), "lang/" + langCode + ".yml");

        if (!langFile.exists()) {
            saveResource("lang/" + langCode + ".yml", false);
        }

        lang = YamlConfiguration.loadConfiguration(langFile);
    }

    public String getMessage(String path, Map<String, String> placeholders) {
        String raw = lang.getString(path, "Message not found: " + path);
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            raw = raw.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return ChatColor.translateAlternateColorCodes('&', getPrefix() + raw);
        //usage:
        // Map<String, String> placeholders = new HashMap<>();
        //placeholders.put("player", sender.getName());
        //placeholders.put("target", target.getName());
        //
        //target.sendMessage(plugin.getMessage("messages.feed.target", placeholders));
        //sender.sendMessage(plugin.getMessage("messages.feed.other", placeholders));
    }

    public String getMessage(String path) {
        return getMessage(path, new HashMap<>());
        //usage: sender.sendMessage(plugin.getMessage("messages.adminpanel"));
    }

    @Override
    public void onDisable() {
       getLogger().info("NomosCore is now disabled");
    }
}
