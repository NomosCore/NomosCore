package github.nomosPlugins.nomosCore.commands;

import github.nomosPlugins.nomosCore.NomosCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeveloperCommand implements CommandExecutor {

    private final NomosCore plugin;

    public DeveloperCommand(NomosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("onlyPlayer"));
            return true;
        }

        if (!sender.hasPermission("nomoscore.dev.*")) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("noPermission.dev"));
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + plugin.getMessage("devCommand.noArgs"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "heal":
                p.setHealth(1);
                p.setFoodLevel(1);
                p.setSaturation(1);
                p.sendMessage(ChatColor.GREEN + plugin.getMessage("devCommand.heal"));
                break;
            case "feed":
                p.setFoodLevel(1);
                p.setSaturation(1);
                p.sendMessage(ChatColor.GREEN + plugin.getMessage("devCommand.feed"));
                break;
            case "maxhealth":
                if (args.length < 2) {
                    p.sendMessage(ChatColor.RED + plugin.getMessage("devCommand.missingHealthValue"));
                    return true;
                }
                try {
                    double maxHealth = Double.parseDouble(args[1]);
                    p.setMaxHealth(maxHealth);
                    p.sendMessage(ChatColor.GREEN + plugin.getMessage("devCommand.maxHealth"));
                } catch (NumberFormatException e) {
                    p.sendMessage(ChatColor.RED + plugin.getMessage("devCommand.invalidHealthValue"));
                }
                break;
            default:
                p.sendMessage(ChatColor.RED + plugin.getMessage("devCommand.noArgs"));
                break;
        }

        return true;
    }
}
