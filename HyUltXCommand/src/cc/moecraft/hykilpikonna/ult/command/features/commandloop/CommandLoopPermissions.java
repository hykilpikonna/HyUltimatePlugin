package cc.moecraft.hykilpikonna.ult.command.features.commandloop;

import cc.moecraft.hykilpikonna.ult.api.PermissionsConfig;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/08/18 创建!
 * Created by Hykilpikonna on 2017/08/18!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandLoopPermissions extends PermissionsConfig
{
    public CommandLoopPermissions()
    {
        super(HyUltXCommand.getInstance(), Features.getCommandLoop().getFriendlyName());
    }

    @Override
    public void writeConfig()
    {
        add("hyc.loop.start", true);
    }
}
