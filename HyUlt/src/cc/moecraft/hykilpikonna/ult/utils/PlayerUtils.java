package cc.moecraft.hykilpikonna.ult.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 此类由 Hykilpikonna 在 2017/06/24 创建!
 * Created by Hykilpikonna on 2017/06/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PlayerUtils
{
    /**
     * Get the cardinal compass direction of a player.
     *
     * @param player
     * @return
     */
    public static String getCardinalDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }

    /**
     * Converts a rotation to a cardinal direction name.
     *
     * @param rot
     * @return
     */
    private static String getDirection(double rot) {
        if (0 <= rot && rot < 22.5) {
            return "North";
        } else if (22.5 <= rot && rot < 67.5) {
            return "Northeast";
        } else if (67.5 <= rot && rot < 112.5) {
            return "East";
        } else if (112.5 <= rot && rot < 157.5) {
            return "Southeast";
        } else if (157.5 <= rot && rot < 202.5) {
            return "South";
        } else if (202.5 <= rot && rot < 247.5) {
            return "Southwest";
        } else if (247.5 <= rot && rot < 292.5) {
            return "West";
        } else if (292.5 <= rot && rot < 337.5) {
            return "Northwest";
        } else if (337.5 <= rot && rot < 360.0) {
            return "North";
        } else {
            return null;
        }
    }

    /**
     * 判断是不是玩家
     * @param sender 指令发送者
     * @return 是不是玩家
     */
    public static boolean isPlayer(CommandSender sender)
    {
        return sender instanceof Player;
    }
}
