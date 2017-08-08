package cc.moecraft.hykilpikonna.ult.fix.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.Material;
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
        addDefault("Height",  127);
        addDefault("TeleportedY",  64);
        addDefault("AntiBuild",  true);
        addDefault("AntiDestroy",  true);
        addDefault("TeleportDown.Enable",  true);
        addDefault("TeleportDown.PlaceBlockOnPlayersFeet",  true);
        addDefault("TeleportDown.PlaceMaterial", Material.NETHERRACK.name());
        addDefault("TeleportDown.RemoveBlockOnPlayersHead",  true);
        addDefault("SendMessage", true);
    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("TeleportDown.RemoveBlockList", new String[]{
                Material.NETHERRACK.name(),
                Material.NETHER_BRICK.name(),
                Material.LAVA.name(),
                Material.STATIONARY_LAVA.name(),
                Material.QUARTZ_ORE.name(),
                Material.GLOWSTONE.name(),
                Material.GRAVEL.name(),
                Material.SOUL_SAND.name(),
                Material.OBSIDIAN.name()
        });
        addDefault("EnabledWorlds", new String[]{"world_nether"});
    }
}
