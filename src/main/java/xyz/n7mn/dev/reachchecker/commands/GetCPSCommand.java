package xyz.n7mn.dev.reachchecker.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.n7mn.dev.reachchecker.ReachChecker;


public class GetCPSCommand implements CommandExecutor {

    private final Plugin plugin;
    public GetCPSCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            return true;
        }
        Player s = (Player) sender;
        if (args[0].equals("disable") || args[0].equals("reset")) {
            ReachChecker.ActionBar.remove(s.getUniqueId());
            s.sendMessage("リセットしました");
        }else {
            Player p = Bukkit.getPlayerExact(args[0]);
            if (p == null) {
                return true;
            }
            ReachChecker.ActionBar.put(s.getUniqueId(), true); //なんかmapに保存しなくてもいい気もしてきたけどめんどくさいから
            ActionBar(s, p);
        }
        return true;
    }
    public void ActionBar(Player sender,Player target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!ReachChecker.ActionBar.containsKey(sender.getUniqueId()) || !sender.isOnline() || !target.isOnline()) {
                    cancel();
                }else {
                    String message = "§bCPS: "+ReachChecker.PreviewCPS.get(target.getUniqueId())+" (+1足したら正確)";
                    sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }
            }
        }.runTaskTimer(plugin,0,20);
    }
}
