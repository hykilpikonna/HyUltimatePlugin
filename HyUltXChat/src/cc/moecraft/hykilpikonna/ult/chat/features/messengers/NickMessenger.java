package cc.moecraft.hykilpikonna.ult.chat.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;

import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class NickMessenger extends Messenger
{
    public NickMessenger()
    {
        super(Chat.getHyUltXChat(), Features.NICK.getFriendlyName());
    }

    @Override
    public void writeConfig() 
    {
        setPrefix("&7&l[&6&l昵称&7&l] &r");

        addDefault("command_missing_args", RED + "您未输入足够的参数");
        addDefault("command_player_not_found", String.format("%s%s玩家不存在", RED, "%PLAYER%"));
        addDefault("command_length_unqualified", String.format("%s昵称长度不合格, 需要在%s和%s之间", RED, "%MIN%", "%MAX%"));
        addDefault("command_console_not_allowed", String.format("%s后台不能对自己使用此指令, 需要指定一个玩家", RED));
        addDefault("command_see_self", String.format("%s您的昵称是%s", AQUA, "%NICK%"));
        addDefault("command_see_other", String.format("%s玩家%s的昵称是%s", AQUA, "%TARGETPLAYER%", "%NICK%"));
        addDefault("command_set_other", String.format("%s已将玩家%s的昵称设置为%s", GREEN, "%TARGETPLAYER%", "%NICK%"));
        addDefault("command_set_self", String.format("%s您已将自己的昵称设置为%s%s", GREEN, RESET, "%NICK%"));
    }
}
