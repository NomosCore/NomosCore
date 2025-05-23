package github.nomosPlugins.nomosCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            // Selbst heilen
            if (!p.hasPermission("nomoscore.heal.self")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to heal yourself.");
                return true;
            }

            heal(p);
            p.sendMessage(ChatColor.GREEN + "You have successfully healed yourself!");
        } else {
            // Anderen heilen
            if (!p.hasPermission("nomoscore.heal.others")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to heal yother players.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.RED + "THe player \"" + args[0] + "\" was not found.");
                return true;
            }

            heal(target);

            if (target.equals(p)) {
                p.sendMessage(ChatColor.GREEN + "You have successfully healed yourself!");
            } else {
                target.sendMessage(ChatColor.GREEN + "You have been healed by " + p.getName() + "!");
                p.sendMessage(ChatColor.GREEN + "You healed " + target.getName() + ".");

            }
        }

        return true;
    }

    private void heal(Player player) {
       double playersHealth =  player.getMaxHealth();
        player.setHealth(playersHealth);
        player.setFoodLevel(20);
        player.setSaturation(20);
    }
}
