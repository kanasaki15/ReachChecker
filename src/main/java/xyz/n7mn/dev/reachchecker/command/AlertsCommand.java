package xyz.n7mn.dev.reachchecker.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.n7mn.dev.reachchecker.PlayerData;
import xyz.n7mn.dev.reachchecker.ReachChecker;

public class AlertsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            PlayerData data = ReachChecker.getPlayerData(player);

            data.setAlert(!data.isAlert());
            sender.sendMessage(ChatColor.YELLOW + "[!] " + data.isAlert() + "にしました");
        }
        return true;
    }
}