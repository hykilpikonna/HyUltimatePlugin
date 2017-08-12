package cc.moecraft.hykilpikonna.ult.fix.features.listeners;

import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/12 创建!
 * Created by Hykilpikonna on 2017/08/12!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CurseOfVanishingFixListener extends Listener
{
    public CurseOfVanishingFixListener()
    {
        super(Fix.getInstance());
    }

    @EventHandler
    public void onEvent(PlayerDeathEvent event)
    {
        if (Features.getCurseOfVanishingFix().getPermissionsConfig().hasPermission(event.getEntity(), "hyult.covf.bypass", false)) return;
        for (int i = 0; i < 36; i++)
            if (event.getEntity().getInventory() != null && event.getEntity().getInventory().getItem(i) != null && event.getEntity().getInventory().getItem(i).getEnchantments() != null && event.getEntity().getInventory().getItem(i).getEnchantments().containsKey(Enchantment.VANISHING_CURSE))
                //TODO: 测试
                event.getEntity().getInventory().setItem(i, new ItemStack(Material.AIR));
    }
}
