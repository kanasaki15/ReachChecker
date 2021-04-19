package xyz.n7mn.dev.reachchecker.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.n7mn.dev.reachchecker.ReachChecker;

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
        sender.sendMessage("§c§n§o注意！このデータはサーバーが起動されてからの物です！");
        sender.sendMessage("§b======= 結果 "+player.getName()+" =======");
        sender.sendMessage("§a検出回数(A): "+ ReachChecker.VLA.get(player.getUniqueId()));
        sender.sendMessage("§a検出回数(B): "+ReachChecker.VLB.get(player.getUniqueId()));
        sender.sendMessage("§e最大リーチ(目安): "+ReachChecker.MaxReach.get(player.getUniqueId()));
        sender.sendMessage("§e最終リーチ(目安): "+ReachChecker.LastReach.get(player.getUniqueId()));
        sender.sendMessage("§b======= 結果 "+player.getName()+" =======");
    return true;}
}
