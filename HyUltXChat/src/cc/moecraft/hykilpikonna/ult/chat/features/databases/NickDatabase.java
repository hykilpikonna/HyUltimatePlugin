package cc.moecraft.hykilpikonna.ult.chat.features.databases;

import cc.moecraft.hykilpikonna.ult.api.Database;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;
import org.bukkit.entity.Player;

import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.convertColorCode;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class NickDatabase extends Database
{
    public NickDatabase()
    {
        super(Chat.getHyUltXChat(), Features.NICK.getFriendlyName());
    }

    /**
     * 获取玩家昵称
     * @param playerName 玩家名
     * @return 昵称
     */
    public String getNickname(String playerName)
    {
        return getString(String.format("Player.%s.Nickname", playerName));
    }

    /**
     * 获取玩家昵称
     * @param player 玩家
     * @return 昵称
     */
    public String getNickname(Player player)
    {
        return getNickname(player.getName());
    }

    /**
     * 用过玩家名设置玩家昵称
     * @param playerName 玩家名
     * @param nickname 玩家昵称
     */
    private void setNickname(String playerName, String nickname)
    {
        set(String.format("Player.%s.Nickname", playerName), nickname);
        save();
    }

    /**
     * 设置玩家昵称
     * @param player 玩家
     * @param nickname 昵称
     */
    public void setNickname(Player player, String nickname)
    {
        if (Features.NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.set.color", false)) nickname = convertColorCode(nickname);
        if (!(Features.NICK.getPermissionsConfig().hasPermission(player, "hyult.command.nick.set.limit.bypass", false)) &&
                (nickname.length() > Features.NICK.getConfig().getInt("MaxLength") || nickname.length() < Features.NICK.getConfig().getInt("MinLength")))
        {
            player.sendMessage(Features.NICK.getMessenger().getWithPrefix("command_length_unqualified")
                    .replace("%MIN%", Integer.toString(Features.NICK.getConfig().getInt("MinLength")))
                    .replace("%MAX%", Integer.toString(Features.NICK.getConfig().getInt("MaxLength"))));
            return;
        }
        setNickname(player.getName(), nickname);
    }
}
