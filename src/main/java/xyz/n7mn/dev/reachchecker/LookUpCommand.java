package xyz.n7mn.dev.reachchecker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LookUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return true;
        }
        Player player = Bukkit.getPlayerExact(args[0]);
        if (player == null) {
            return true;
        }
        PlayerData data = ReachChecker.playerdataHashMap.get(player.getUniqueId());
        sender.sendMessage("§c§n§o注意！このデータはサーバーが起動されてからの物です！");
        sender.sendMessage("§b======= 結果 "+player.getName()+" =======");
        sender.sendMessage("§a検出回数(A): "+data.getVLA());
        sender.sendMessage("§a検出回数(B): "+data.getVLB());
        sender.sendMessage("§e最大リーチ(目安): "+data.getMaxcps());
        sender.sendMessage("§e最終リーチ(目安): "+data.getLastreach());
        sender.sendMessage("§e最大CPS: "+data.getMaxcps());
        sender.sendMessage("§b======= 結果 "+player.getName()+" =======");
    return true;}
}
