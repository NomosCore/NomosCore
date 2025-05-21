package github.dave1610.nomosCore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl benutzen!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            // Selbst heilen
            if (!p.hasPermission("nomoscore.feed.self")) {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }

            feed(p);
            p.sendMessage(ChatColor.GREEN + "Your hunger has been fully satisfied!");
        } else {
            // Anderen heilen
            if (!p.hasPermission("nomoscore.feed.others")) {
                p.sendMessage(ChatColor.RED + "No permission feed other players!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.RED + "Player \"" + args[0] + "\" was not found!");
                return true;
            }

            feed(target);

            if (target.equals(p)) {
                p.sendMessage(ChatColor.GREEN + "Your hunger has been fully satisfied!");
            } else {
                target.sendMessage(ChatColor.GREEN + "You have been fed by " + p.getName() + ".");
                p.sendMessage(ChatColor.GREEN + "You fed the player " + target.getName() + ".");
            }
        }

        return true;
    }

    private void feed(Player player) {
        player.setFoodLevel(20);
        player.setSaturation(20);
    }
}
