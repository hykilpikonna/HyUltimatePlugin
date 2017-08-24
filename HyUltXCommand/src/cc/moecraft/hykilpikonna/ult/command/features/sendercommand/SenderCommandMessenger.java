package cc.moecraft.hykilpikonna.ult.command.features.sendercommand;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import org.bukkit.ChatColor;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class SenderCommandMessenger extends Messenger
{
    public SenderCommandMessenger()
    {
        super(HyUltXCommand.getInstance(), Features.getSenderCommand().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "§7§l[§6§lHy§e§lCommand§7§l] §r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("sent_player", ChatColor.GREEN + "玩家%PLAYER%已发送消息%MESSAGE%");
        addDefault("sent_console", ChatColor.GREEN + "后台已发送指令%MESSAGE%");
        addDefault("player_not_found", ChatColor.RED + "未找到玩家%PLAYER%");
    }
}
