package github.nomosPlugins.nomosCore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DeveloperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Nur Spieler können diesen Befehl benutzen!");
            return true;
        }
        if (!sender.hasPermission("nomoscore.dev.*")){
            sender.sendMessage("Du bist kein Developer!");
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            p.sendMessage(ChatColor.RED + "Du musst eingeben was du testen möchtest!");
            return true;
        }

        if(args[0].equalsIgnoreCase("heal")){
            p.setHealth(1);
            p.setFoodLevel(1);
            p.setSaturation(1);
            p.sendMessage(ChatColor.GREEN + "Bereit zum testen von: HealCommand!");
            return true;
        }else if(args[0].equalsIgnoreCase("feed")){
            p.setFoodLevel(1);
            p.setSaturation(1);
            p.sendMessage(ChatColor.GREEN + "Bereit zum testen von: FeedCommand!");
        }else if(args[0].equalsIgnoreCase("maxhealth")){
            double maxHealth = Double.parseDouble(args[1]);
            p.setMaxHealth(maxHealth);
            p.sendMessage(ChatColor.GREEN + "Bereit zum testen von: maxHealthMethode");
        }

        return true;
    }
}
