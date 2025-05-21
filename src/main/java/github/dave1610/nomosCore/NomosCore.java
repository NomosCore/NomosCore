package github.dave1610.nomosCore;

import github.dave1610.nomosCore.commands.AdminPanel;
import github.dave1610.nomosCore.commands.DeveloperCommand;
import github.dave1610.nomosCore.commands.FeedCommand;
import github.dave1610.nomosCore.commands.HealCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class NomosCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("adminpanel").setExecutor(new AdminPanel());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("develop").setExecutor(new DeveloperCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getLogger().info("NomosCore is successfully enabled!");

    }

    @Override
    public void onDisable() {
       getLogger().info("NomosCore is now disabled");
    }
}
