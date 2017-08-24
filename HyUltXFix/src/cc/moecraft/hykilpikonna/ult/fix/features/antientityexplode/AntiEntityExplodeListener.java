package cc.moecraft.hykilpikonna.ult.fix.features.antientityexplode;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AntiEntityExplodeListener extends Listener
{
    static boolean enableExplodingDamage;

    public AntiEntityExplodeListener()
    {
        super(Fix.getInstance());
    }

    @EventHandler(ignoreCancelled=true)
    public void onEntityExplode(EntityExplodeEvent event)
    {
        if (enableExplodingDamage)
        {
            List<Block> blocks = event.blockList();
            blocks.clear();
        }
        else event.setCancelled(true);
    }
}
