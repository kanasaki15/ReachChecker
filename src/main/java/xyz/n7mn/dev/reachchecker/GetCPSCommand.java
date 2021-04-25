package xyz.n7mn.dev.reachchecker;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;


public class GetCPSCommand implements CommandExecutor {

    private final Plugin plugin;
    public GetCPSCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length != 1) {
                return true;
            }
            Player s = (Player) sender;
            if (args[0].equals("disable") || args[0].equals("reset")) {
                ReachChecker.playerdataHashMap.get(s.getUniqueId()).setViewcps(false);
                s.sendMessage("ActionBarの表示を中止させました。");
            } else {
                Player p = Bukkit.getPlayerExact(args[0]);
                if (p == null) {
                    return true;
                }
                ReachChecker.playerdataHashMap.get(s.getUniqueId()).setViewcps(true);
                ActionBar(s, p);
            }
        }
        return true;
    }
    public void ActionBar(Player sender,Player target) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!ReachChecker.playerdataHashMap.get(sender.getUniqueId()).isViewcps() || !sender.isOnline() || !target.isOnline()) {
                    sender.sendMessage("§c何らかの理由でActionBarの表示は中止されました");
                    cancel();
                }else {
                    String message = "§b"+target.getName()+"のCPS: "+ReachChecker.playerdataHashMap.get(target.getUniqueId()).getCps();
                    sender.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                }
            }
        }.runTaskTimer(plugin,0,20);
    }
}
