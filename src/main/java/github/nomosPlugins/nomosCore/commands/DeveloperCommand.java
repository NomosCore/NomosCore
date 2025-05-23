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
            sender.sendMessage("Only player can execute this command!");
            return true;
        }
        if (!sender.hasPermission("nomoscore.dev.*")){
            sender.sendMessage( ChatColor.RED + "You are not a developer!");
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            p.sendMessage(ChatColor.RED + "You have to enter what you want to test!");
            return true;
        }

        if(args[0].equalsIgnoreCase("heal")){
            p.setHealth(1);
            p.setFoodLevel(1);
            p.setSaturation(1);
            p.sendMessage(ChatColor.GREEN + "Ready to test: HealCommand!");
            return true;
        }else if(args[0].equalsIgnoreCase("feed")){
            p.setFoodLevel(1);
            p.setSaturation(1);
            p.sendMessage(ChatColor.GREEN + "Ready to test: FeedCommand!");
        }else if(args[0].equalsIgnoreCase("maxhealth")){
            double maxHealth = Double.parseDouble(args[1]);
            p.setMaxHealth(maxHealth);
            p.sendMessage(ChatColor.GREEN + "Ready to test: maxHealthMethode");
        }

        return true;
    }
}
