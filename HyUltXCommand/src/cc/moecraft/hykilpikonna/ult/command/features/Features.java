package cc.moecraft.hykilpikonna.ult.command.features;

import cc.moecraft.hykilpikonna.ult.api.Feature;
import cc.moecraft.hykilpikonna.ult.command.Command;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandConfig;
import cc.moecraft.hykilpikonna.ult.command.features.blockcommand.BlockCommandListener;

/**
 * 此类由 Hykilpikonna 在 2017/08/13 创建!
 * Created by Hykilpikonna on 2017/08/13!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Features
{
    private static Feature blockCommand;

    public Features()
    {
        if (Command.getInstance().getSwitches().getBoolean("BlockCommand.Enable"))
        {
            blockCommand = new Feature();
            blockCommand.setId("blockcommand");
            blockCommand.setFriendlyName("BlockCommand");
            blockCommand.setConfig(new BlockCommandConfig());
            blockCommand.setListener(new BlockCommandListener());
        }
    }

    public static Feature getBlockCommand() {
        return blockCommand;
    }
}
