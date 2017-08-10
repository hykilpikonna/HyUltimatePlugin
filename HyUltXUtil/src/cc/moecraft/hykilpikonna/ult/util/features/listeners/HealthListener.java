package cc.moecraft.hykilpikonna.ult.util.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HealthListener extends Listener
{
    public HealthListener()
    {
        super(Util.getInstance());
    }

    /**
     * 监控玩家被伤害事件
     * @param event 事件
     */
    @EventHandler
    public void onHealthEvent(EntityDamageEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();
            if (Features.getHealth().getDatabase().contains(String.format("Players.%s.HealthLock.Enabled", player.getName())) && Features.getHealth().getDatabase().getBoolean(String.format("Players.%s.HealthLock.Enabled", player.getName())))
            {
                event.setCancelled(true);
                player.setHealth(Features.getHealth().getDatabase().getDouble(String.format("Players.%s.HealthLock.Value", player.getName())));
            }
        }
    }
}
