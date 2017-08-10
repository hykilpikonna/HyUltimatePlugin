package cc.moecraft.hykilpikonna.ult.util.features.calculations;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.entity.Player;

import static org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HealthCalculations
{
    /**
     * 锁定玩家血量
     * @param player 玩家
     * @param health 血量
     */
    public static void lockPlayerHealth(Player player, Integer health)
    {
        Features.getHealth().getDatabase().set(String.format("Players.%s.HealthLock.Enabled", player.getName()), true);
        Features.getHealth().getDatabase().set(String.format("Players.%s.HealthLock.Value", player.getName()), health);
        Features.getHealth().getDatabase().save();
    }

    /**
     * 解除锁定玩家血量
     * @param player 玩家
     */
    public static void unlockPlayerHealth(Player player)
    {
        Features.getHealth().getDatabase().set(String.format("Players.%s.HealthLock.Enabled", player.getName()), false);
        Features.getHealth().getDatabase().set(String.format("Players.%s.HealthLock.Value", player.getName()), 20);
        Features.getHealth().getDatabase().save();
    }

    /**
     * 设置玩家血量
     * @param player 玩家
     * @param health 血量
     */
    public static void setHealth(Player player, Integer health)
    {
        player.setHealth(checkHealthValue(player, health));
    }

    /**
     * 设置玩家血量
     * @param player 玩家
     * @param maxHealth 血量
     */
    public static void setMaxHealth(Player player, Integer maxHealth)
    {
        player.getAttribute(GENERIC_MAX_HEALTH).setBaseValue(checkHealthValue(player, maxHealth));
    }

    /**
     * 获取玩家最大血量
     * @param player 玩家
     */
    public static Double getMaxHealth(Player player)
    {
        return player.getAttribute(GENERIC_MAX_HEALTH).getBaseValue();
    }

    /**
     * 检查血量数值是否大于2048
     * @param player 玩家
     * @param input 血量
     * @return 输出血量
     */
    public static Double checkHealthValue(Player player, Double input)
    {
        if (input > 2048)
        {
            Features.getHealth().getMessenger().sendMessage(player, "value_more_then_2048");
            return 2048.0D;
        }
        else return input;
    }

    /**
     * 检查血量数值是否大于2048
     * @param player 玩家
     * @param input 血量
     * @return 输出血量
     */
    public static Double checkHealthValue(Player player, Integer input)
    {
        return checkHealthValue(player, input.doubleValue());
    }
}
