package cc.moecraft.hykilpikonna.ult.util.features.calculations;

import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class GetHeadCalculations
{
    /**
     * 获取玩家头颅
     *
     * @param playerName 玩家名
     * @param amount 数量
     * @return 头颅物品ItemStack
     */
    public static ItemStack getHead(String playerName, int amount)
    {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(playerName);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 玩家用指令获取玩家的头
     *
     * @param commandSender 发送指令的玩家
     * @param amount 头的数量
     * @param playerName 头的玩家名
     */
    public static void commandGetHead(Player commandSender, int amount, String playerName)
    {
        commandSender.getInventory().addItem(getHead(playerName, amount));
        commandSender.updateInventory();
        commandSender.sendMessage(Features.getGetHead().getMessenger().getWithPrefix("success")
                .replace("%AMOUNT%", Integer.toString(amount))
                .replace("%PLAYER%", playerName)
        );
    }
}
