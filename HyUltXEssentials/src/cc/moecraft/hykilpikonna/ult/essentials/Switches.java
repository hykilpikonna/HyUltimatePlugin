package cc.moecraft.hykilpikonna.ult.essentials;

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
        super(HyUltXEssentials.getInstance(), "", "Switches");
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig()
    {
        addDefault("AutoUpdate.Enable", true);
        addDefault("AutoUpdate.Repeat", false);

        addDefault("Entity.Enable", true);
    }

    @Override
    public void writeDefaultConfig() {}
}
