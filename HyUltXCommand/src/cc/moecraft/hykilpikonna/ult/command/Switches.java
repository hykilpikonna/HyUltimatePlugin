package cc.moecraft.hykilpikonna.ult.command;

import cc.moecraft.hykilpikonna.ult.api.Config;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Switches extends Config
{
    public Switches()
    {
        super(HyUltXCommand.getInstance(), "", "Switches");
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig()
    {
        addDefault("AutoUpdate.Enable", true);
        addDefault("AutoUpdate.Repeat", false);

        addDefault("BlockCommand.Enable", true);
        addDefault("CommandMapping.Enable", true);
    }

    @Override
    public void writeDefaultConfig() {}
}
