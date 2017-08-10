package cc.moecraft.hykilpikonna.ult.util.features.calculations;

import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.entity.Player;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FoodLevelCalculations
{
    /**
     * 锁定玩家饱食度
     * @param player 玩家
     * @param foodLevel 饱食度
     */
    public static void lockPlayerFoodLevel(Player player, Integer foodLevel)
    {
        Features.getFoodLevel().getDatabase().set(String.format("Players.%s.SatLock.Enabled", player.getName()), true);
        Features.getFoodLevel().getDatabase().set(String.format("Players.%s.SatLock.Value", player.getName()), foodLevel);
        Features.getFoodLevel().getDatabase().save();
    }

    /**
     * 解除锁定玩家饱食度
     * @param player 玩家
     */
    public static void unlockPlayerFoodLevel(Player player)
    {
        Features.getFoodLevel().getDatabase().set(String.format("Players.%s.SatLock.Enabled", player.getName()), false);
        Features.getFoodLevel().getDatabase().set(String.format("Players.%s.SatLock.Value", player.getName()), 20);
        Features.getFoodLevel().getDatabase().save();
    }

    /**
     * 设置玩家饱食度
     * @param player 玩家
     * @param foodLevel 饱食度
     */
    public static void setFoodLevel(Player player, Integer foodLevel)
    {
        player.setFoodLevel(foodLevel);
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
            Features.getFoodLevel().getMessenger().sendMessage(player, "value_more_then_2048");
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
