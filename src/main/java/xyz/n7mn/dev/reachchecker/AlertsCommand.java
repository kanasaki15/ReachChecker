package xyz.n7mn.dev.reachchecker;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AlertsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerData data = ReachChecker.playerdataHashMap.get(player.getUniqueId()); //入ったときに必ずあるはずなので

            if (data.isAlert()) {
                data.setIsalert(false);
                player.sendMessage("§e[ReachChecker] §rアラートを§cOFF§rにしました");
            } else {
                data.setIsalert(true);
                player.sendMessage("§e[ReachChecker] §rアラートを§aON§rにしました");
            }
        }
        return true;
    }
}