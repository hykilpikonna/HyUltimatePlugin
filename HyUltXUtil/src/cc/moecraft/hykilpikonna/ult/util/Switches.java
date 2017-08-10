package cc.moecraft.hykilpikonna.ult.util;

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
        super(Util.getInstance(), "", "Switches");
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig()
    {
        addDefault("AutoUpdate.Enable", true);
        addDefault("AutoUpdate.Repeat", false);

        addDefault("ItemFix.Enable", true);
        addDefault("GetHead.Enable", true);
        addDefault("Health.Enable", true);
        addDefault("FoodLevel.Enable", true);
        addDefault("Pullback.Enable", true);
        addDefault("AutoRespawn.Enable", true);
        addDefault("FishModifier.Enable", true);
    }

    @Override
    public void writeDefaultConfig() {}
}
