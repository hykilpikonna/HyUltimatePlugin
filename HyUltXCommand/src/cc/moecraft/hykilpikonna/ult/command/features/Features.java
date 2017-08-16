package cc.moecraft.hykilpikonna.ult.command.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandConfig;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandListener;
import cc.moecraft.hykilpikonna.ult.command.features.commandmapping.CommandMappingConfig;
import cc.moecraft.hykilpikonna.ult.command.features.commandmapping.CommandMappingListener;

/**
 * 此类由 Hykilpikonna 在 2017/08/13 创建!
 * Created by Hykilpikonna on 2017/08/13!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature blockCommand;
    private static Feature commandMapping;

    public Features()
    {
        if (HyUltXCommand.getInstance().getSwitches().getBoolean("BlockCommand.Enable"))
        {
            blockCommand = new Feature();
            blockCommand.setId("blockcommand");
            blockCommand.setFriendlyName("BlockCommand");
            blockCommand.setConfig(new BlockCommandConfig());
            blockCommand.setListener(new BlockCommandListener());
        }
        if (HyUltXCommand.getInstance().getSwitches().getBoolean("CommandMapping.Enable"))
        {
            commandMapping = new Feature();
            getCommandMapping().setId("commandmapping");
            getCommandMapping().setFriendlyName("CommandMapping");
            getCommandMapping().setConfig(new CommandMappingConfig());
            getCommandMapping().setListener(new CommandMappingListener());
        }
    }

    public static Feature getBlockCommand() {
        return blockCommand;
    }

    public static Feature getCommandMapping() {
        return commandMapping;
    }
}
