package github.nomosPlugins.nomosCore.commands;

import github.nomosPlugins.nomosCore.NomosCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminPanel implements CommandExecutor {

    private final NomosCore plugin;

    public AdminPanel(NomosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("onlyPlayer"));
            return true;
        }

        if (!sender.hasPermission("nomoscore.adminpanel")) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("noPermission.adminPanel"));
            return true;
        }

        sender.sendMessage(ChatColor.RED + plugin.getMessage("adminPanel.inDevelopment"));
        return true;
    }
}
