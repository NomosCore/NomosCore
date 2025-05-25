package github.nomosPlugins.nomosCore.commands;

import github.nomosPlugins.nomosCore.NomosCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {

    private final NomosCore plugin;

    public HealCommand(NomosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + plugin.getMessage("onlyPlayer"));
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            if (!p.hasPermission("nomoscore.heal.self")) {
                p.sendMessage(ChatColor.RED + plugin.getMessage("noPermission.heal.self"));
                return true;
            }

            heal(p);
            p.sendMessage(ChatColor.GREEN + plugin.getMessage("heal.self"));
        } else {
            if (!p.hasPermission("nomoscore.heal.others")) {
                p.sendMessage(ChatColor.RED + plugin.getMessage("noPermission.heal.others"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.RED + plugin.getMessage("playerNotFound") + ": " + args[0]);
                return true;
            }

            heal(target);

            if (target.equals(p)) {
                p.sendMessage(ChatColor.GREEN + plugin.getMessage("heal.self"));
            } else {
                target.sendMessage(ChatColor.GREEN + plugin.getMessage("heal.gotHealed") + p.getName());
                p.sendMessage(ChatColor.GREEN + plugin.getMessage("heal.other") + target.getName());
            }
        }

        return true;
    }

    private void heal(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(20);
    }
}
