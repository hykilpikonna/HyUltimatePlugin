package cc.moecraft.hykilpikonna.ult.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static cc.moecraft.hykilpikonna.ult.Setup.loglogger;
import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.isNumeric;

/**
 * 此类由 Hykilpikonna 在 2017/06/15 创建!
 * Created by Hykilpikonna on 2017/06/15!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class VersionUtils
{
    /**
     * 获取短版本号
     * @return 短版本号
     */
    public static Double getShortVersion()
    {
        Double tempDouble;
        String version = Bukkit.getServer().getClass().getPackage().getName().substring(Bukkit.getServer().getClass().getPackage().getName().lastIndexOf('.') + 1);
        if (isNumeric(version.charAt(1)))
        {
            tempDouble = Double.parseDouble(String.valueOf(version.charAt(1)));
            if (!isNumeric(version.charAt(2)))
            {
                if (isNumeric(version.charAt(3)))
                {
                    tempDouble = tempDouble + (Double.parseDouble(String.valueOf(version.charAt(3))) / 10);
                    if (isNumeric(version.charAt(4)))
                    {
                        tempDouble = tempDouble + (Double.parseDouble(String.valueOf(version.charAt(4))) / 100);
                    }
                }
            }
        }
        else
        {
            return 0.0;
        }
        loglogger.Debug("计算出的版本号为" + tempDouble);
        return tempDouble;
    }

    /**
     * 获取玩家手上物品(全版本)
     * @param player 玩家
     * @return 手上物品
     */
    public static ItemStack getAllVersionItemInHand(Player player)
    {
        if (getShortVersion() >= 1.9)
        {
            return player.getInventory().getItemInMainHand();
        }
        else
        {
            return player.getInventory().getItemInHand();
        }
    }

    /**
     * 设置玩家手上物品数据(全版本)
     * @param player 玩家
     * @param itemMeta 手上物品数据
     */
    public static void setAllVersionItemInHand(Player player, ItemMeta itemMeta)
    {
        if (getShortVersion() >= 1.9)
        {
            player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
            player.updateInventory();
        }
        else
        {
            player.getInventory().getItemInHand().setItemMeta(itemMeta);
            player.updateInventory();
        }
    }
}
