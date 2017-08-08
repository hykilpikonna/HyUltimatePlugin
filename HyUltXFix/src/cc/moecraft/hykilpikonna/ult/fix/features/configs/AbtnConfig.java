package cc.moecraft.hykilpikonna.ult.fix.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AbtnConfig extends Config
{
    public AbtnConfig()
    {
        super(Fix.getInstance(), "Features", Features.getABTN().getFriendlyName());
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
        addDefault("Height",  128);
        addDefault("TeleportedY",  64);
        addDefault("AntiBuild",  true);
        addDefault("AntiDestroy",  true);
        addDefault("TeleportDown",  false);
        addDefault("SendMessage", true);
        addDefault("EnabledWorlds",  new String[]{"world_nether"});
    }
}
