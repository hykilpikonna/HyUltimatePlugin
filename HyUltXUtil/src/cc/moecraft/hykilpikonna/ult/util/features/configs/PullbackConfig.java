package cc.moecraft.hykilpikonna.ult.util.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class PullbackConfig extends Config
{
    public PullbackConfig()
    {
        super(Util.getInstance(), "Features", Features.getPullback().getFriendlyName());
    }

    @Override
    public void readConfig()
    {

    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("SetFacingDown", true);
        addDefault("DisableFallDamage", true);
        addDefault("DefaultY", -10);
        addDefault("DefaultPullbackY", 255);
    }
}
