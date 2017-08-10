package cc.moecraft.hykilpikonna.ult.util.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static cc.moecraft.hykilpikonna.ult.util.features.Features.getAutoRespawn;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AutoRespawnListener extends Listener
{
    public AutoRespawnListener()
    {
        super(Util.getInstance());
    }

    @EventHandler
    public void onEvent(PlayerDeathEvent event)
    {
        Player player = event.getEntity();
        if (getAutoRespawn().getConfig().getBoolean("UseDelay"))
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    process(player);
                }
            }.runTaskLater(Util.getInstance(), getAutoRespawn().getConfig().getInt("DelayInTicks"));
        }
        else process(player);
    }

    private void process(Player player)
    {
        if (player != null && player.isOnline())
        {
            if (getAutoRespawn().getPermissionsConfig().hasPermission(player, "hyult.autorespawn", false))
            {
                player.spigot().respawn();
                getAutoRespawn().getMessenger().sendMessage(player, "respawn_success");
            }
        }
    }
}
