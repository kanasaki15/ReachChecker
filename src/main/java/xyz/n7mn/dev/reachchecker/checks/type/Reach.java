package xyz.n7mn.dev.reachchecker.checks.type;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.n7mn.dev.reachchecker.PlayerData;
import xyz.n7mn.dev.reachchecker.ReachChecker;
import xyz.n7mn.dev.reachchecker.checks.Check;
import xyz.n7mn.dev.reachchecker.util.raytrace.AABB;
import xyz.n7mn.dev.reachchecker.util.raytrace.Ray;

public class Reach extends Check implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(ReachChecker.getPlugin(), () -> {
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

                //暇があるときPingで除外させる

                Player player = (Player) e.getDamager();

                if (player.getGameMode() == GameMode.CREATIVE) return;

                Player target = (Player) e.getEntity();

                double reach = ReachDisplay(player, target, 7.0);

                PlayerData data = ReachChecker.getPlayerData(player);
                if (reach != -1) data.addDoubles(reach);


                //通常なら15だが、ダメージを与えたときにしか反応しないので7で十分
                if (data.getDoubles().size() > 7) {

                    double minReach = data.getDoubles().stream().mapToDouble(i -> i).min().orElse(-1);

                    data.remove(0);

                    if (minReach > 3.16) {

                        data.increaseBuffer(0.7);

                        if (data.getBuffer() > 1) {
                            //data.setBuffer(data.getBuffer() / 2);
                            data.setBuffer(0.7);
                            alert(player, "Reach", String.format("Reach=%s RecordReach=%s", reach, minReach));
                        }
                    } else {
                        data.decreaseBuffer(.2);
                    }
                }
            }
        });
    }

    private static double ReachDisplay(Player player, Player target, double max) {
        Ray ray = Ray.from(player);
        return AABB.from(target).collidesD(ray, 0, max);
    }
}
