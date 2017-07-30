package cc.moecraft.hykilpikonna.ult.chat.features.calculations;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ClsCalculations
{
    public static String message;

    public static void clsAll()
    {
        for (Player player1 : Bukkit.getServer().getOnlinePlayers()) sendClsMessage(player1);
    }

    public static void sendClsMessage(CommandSender player)
    {
        player.sendMessage(message);
    }
}
