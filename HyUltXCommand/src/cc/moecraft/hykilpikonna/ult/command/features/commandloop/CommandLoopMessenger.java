package cc.moecraft.hykilpikonna.ult.command.features.commandloop;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import org.bukkit.ChatColor;

/**
 * 此类由 Hykilpikonna 在 2017/08/18 创建!
 * Created by Hykilpikonna on 2017/08/18!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandLoopMessenger extends Messenger
{
    public CommandLoopMessenger()
    {
        super(HyUltXCommand.getInstance(), Features.getCommandLoop().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&lHy&e&lCommand&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("console_not_allowed", String.format("%s后台暂时无法实现此指令", ChatColor.RED));
        addDefault("failed_to_read_tag", String.format("%s您输入的标签%s无法识别", ChatColor.RED, "%TAG%"));
        addDefault("command_start", new String[] {ChatColor.GREEN + "您的指令开始运行!"});
    }
}
