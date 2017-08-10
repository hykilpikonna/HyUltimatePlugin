package cc.moecraft.hykilpikonna.ult.util.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FoodLevelListener extends Listener
{
    public FoodLevelListener()
    {
        super(Util.getInstance());
    }

    /**
     * 监控玩家饱食度减少事件
     * @param event 事件
     */
    @EventHandler
    public void onFoodEvent(FoodLevelChangeEvent event)
    {
        if (event.getEntity() instanceof Player)
        {
            Player player = (Player) event.getEntity();
            if (Features.getFoodLevel().getDatabase().contains(String.format("Players.%s.SatLock.Enabled", player.getName())) && Features.getFoodLevel().getDatabase().getBoolean(String.format("Players.%s.SatLock.Enabled", player.getName())))
            {
                event.setCancelled(true);
                player.setFoodLevel(Features.getFoodLevel().getDatabase().getInt(String.format("Players.%s.SatLock.Value", player.getName())));
            }
        }
    }
}
