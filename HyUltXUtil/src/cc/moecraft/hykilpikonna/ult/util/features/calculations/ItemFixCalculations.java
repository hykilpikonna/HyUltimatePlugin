package cc.moecraft.hykilpikonna.ult.util.features.calculations;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.utils.VersionUtils.getShortVersion;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ItemFixCalculations
{
    private static ArrayList<String> fixItems = new ArrayList<>();

    /**
     * 修复手中物品
     * @param player 玩家
     */
    public static boolean fixItemInHand(Player player)
    {
        try
        {
            if (getShortVersion() >= 1.9) return fix(player.getInventory().getItemInMainHand());
            else return fix(player.getInventory().getItemInHand());
        }
        catch (Exception ignored)
        {
            return false;
        }
    }

    /**
     * 修复一个物品
     * @param itemStack 物品
     * @return 是否修复成功
     */
    public static boolean fix(ItemStack itemStack)
    {
        try
        {
            if (getFixItems().contains(itemStack.getType().name())) itemStack.setDurability((short)0);
            else return false;
        }
        catch (Exception ignored)
        {
            return false;
        }
        return true;
    }

    /**
     * 修复玩家身上某个位置的物品
     * @param player 玩家
     * @param slot 位置
     * @return 是否修复成功
     */
    public static boolean fix(Player player, Integer slot)
    {
        return fix(player.getInventory().getItem(slot));
    }

    /**
     * 修复玩家身上所有位置的物品
     * @param player 玩家
     * @return 修复失败的数量
     */
    public static int fixAll(Player player)
    {
        int count = 0;
        for (int i = 0; i <= 36; i++)
        {
            if (player.getInventory().getItem(i) != null) if (!(fix(player, i))) count += 1;
        }
        return count;
    }

    public static ArrayList<String> getFixItems()
    {
        return fixItems;
    }
}
