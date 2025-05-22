//Team-Only Command
//permission: nomoscore.adminpanel
package github.nomosPlugins.nomosCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdminPanel implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if(!sender.hasPermission("nomoscore.adminpanel")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "NomosCore " + ChatColor.RESET + ChatColor.GRAY + "››" + ChatColor.RESET + ChatColor.RED + " The adminpanel is in development..");
        //wenn fertig mit testen und plugin noch nicht fertig dann return true; wieder einfügen
        return true;

        //AdminPanel richtigen code ab hier hinzufügen und wenn fertig Zeile 26 und 27 löschen

    }
}
