package cc.moecraft.hykilpikonna.ult.command.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandConfig;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandListener;
import cc.moecraft.hykilpikonna.ult.command.features.commandloop.CommandLoopCommand;
import cc.moecraft.hykilpikonna.ult.command.features.commandloop.CommandLoopMessenger;
import cc.moecraft.hykilpikonna.ult.command.features.commandloop.CommandLoopPermissions;
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
    private static Feature commandLoop;

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
        if (HyUltXCommand.getInstance().getSwitches().getBoolean("CommandLoop.Enable"))
        {
            commandLoop = new Feature();
            commandLoop.setId("commandloop");
            commandLoop.setFriendlyName("CommandLoop");
            commandLoop.setMessenger(new CommandLoopMessenger());
            commandLoop.setPermissionsConfig(new CommandLoopPermissions());
            commandLoop.setCommandRunner(new CommandLoopCommand());
        }
    }

    public static Feature getBlockCommand() {
        return blockCommand;
    }

    public static Feature getCommandMapping() {
        return commandMapping;
    }

    public static Feature getCommandLoop() {
        return commandLoop;
    }

    public static void setCommandLoop(Feature commandLoop) {
        Features.commandLoop = commandLoop;
    }
}
