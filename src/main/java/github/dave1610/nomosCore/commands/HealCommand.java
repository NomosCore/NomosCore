package github.dave1610.nomosCore.commands;

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
            sender.sendMessage("Nur Spieler können diesen Befehl benutzen!");
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            // Selbst heilen
            if (!p.hasPermission("nomoscore.heal.self")) {
                p.sendMessage(ChatColor.RED + "Keine Berechtigung, dich selbst zu heilen.");
                return true;
            }

            heal(p);
            p.sendMessage(ChatColor.GREEN + "Du hast dich erfolgreich geheilt!");
        } else {
            // Anderen heilen
            if (!p.hasPermission("nomoscore.heal.others")) {
                p.sendMessage(ChatColor.RED + "Keine Berechtigung, andere zu heilen.");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null || !target.isOnline()) {
                p.sendMessage(ChatColor.RED + "Spieler \"" + args[0] + "\" wurde nicht gefunden.");
                return true;
            }

            heal(target);

            if (target.equals(p)) {
                p.sendMessage(ChatColor.GREEN + "Du hast dich erfolgreich geheilt!");
            } else {
                target.sendMessage(ChatColor.GREEN + "Du wurdest von " + p.getName() + " geheilt!");
                p.sendMessage(ChatColor.GREEN + "Du hast " + target.getName() + " geheilt.");
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
