package cc.moecraft.hykilpikonna.ult.command.features.sendercommand;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class SenderCommandPermissions extends PermissionsConfig
{
    public SenderCommandPermissions()
    {
        super(HyUltXCommand.getInstance(), Features.getSenderCommand().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyult.commands.sendercommand.player", true);
        add("hyult.commands.sendercommand.console", true);
    }
}
