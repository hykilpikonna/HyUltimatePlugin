package cc.moecraft.hykilpikonna.ult.util.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.util.Util;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.java.JavaPlugin;
import static cc.moecraft.hykilpikonna.ult.util.features.calculations.FishModifierCalculations.*;

import java.util.Random;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierListener extends Listener
{
    public FishModifierListener()
    {
        super(Util.getInstance());
    }

    @EventHandler
    public void onFish(PlayerFishEvent event)
    {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH && event.getCaught() instanceof Item)
        {
            //判断世界
            if (staticEnabledWorldList.contains(event.getPlayer().getWorld().getName()))
            {
                //随机
                int randomNumber = new Random().nextInt((int) Math.round(chanceDivideBy));

                for (int i = 0; i < intChance.size(); i++)
                {
                    if (intChance.get(i) > randomNumber)
                    {
                        ((Item) event.getCaught()).setItemStack(fishList.get(i).sendFishToPlayer(event.getPlayer()));
                        return;
                    }
                }
            }
        }
    }
}
