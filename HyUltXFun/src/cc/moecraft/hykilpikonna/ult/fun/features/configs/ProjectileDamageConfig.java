package cc.moecraft.hykilpikonna.ult.fun.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.ProjectileDamageCalculations;
import org.bukkit.plugin.java.JavaPlugin;

import javax.naming.CompositeName;
import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.getWorldNameArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ProjectileDamageConfig extends Config
{
    public ProjectileDamageConfig()
    {
        super(Fun.getInstance(), "Features", Features.getProjectileDamage().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        ProjectileDamageCalculations.getStaticEnabledWorldList().addAll(getStringList("Damage.EnabledWorlds"));
    }

    @Override
    public void writeConfig()
    {
        
    }

    @Override
    public void writeDefaultConfig()
    {
        ArrayList<String> worlds = getWorldNameArrayList();
        addDefault("Damage.SnowBall", 5.0D);
        addDefault("Damage.Egg", 5.0D);
        addDefault("Damage.EnderPearl", 10.0D);
        addDefault("Damage.EnabledWorlds", worlds);
        addDefault("Message.SendMessage", false);
        addDefault("Message.ActionBar", true);
        addDefault("Message.ActionBarDuration", 40);
    }
}
