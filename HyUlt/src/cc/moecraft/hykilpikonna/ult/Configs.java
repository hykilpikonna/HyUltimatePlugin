package cc.moecraft.hykilpikonna.ult;

import cc.moecraft.hykilpikonna.ult.api.Config;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/07/30 创建!
 * Created by Hykilpikonna on 2017/07/30!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Configs extends Config
{
    public Configs() {
        super(Main.getMain(), "", "Config");
    }

    @Override
    public void readConfig() {

    }

    @Override
    public void writeConfig()
    {
        addDefault("AutoUpdate.Enable", true);
        addDefault("AutoUpdate.This.Enable", true);
        addDefault("AutoUpdate.This.Repeat", true);
        addDefault("AutoUpdate.This.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/HyUlt/src/plugin.yml");
        addDefault("AutoUpdate.This.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUlt.jar");
        addDefault("AutoUpdate.This.CheckDelay", 3200);
        addDefault("AutoUpdate.Default.TimeoutInSeconds", 3);
        addDefault("AutoUpdate.Default.CheckDelayInSeconds", 3600);

        addDefault("AutoUpdate.HyEssentials", true);
        addDefault("AutoUpdate.YumNetworkCheckBypass", true);

        addDefault("AutoBackupConfig.Enable", true);
        addDefault("AutoBackupConfig.WhenPluginUpdate", true);
    }

    @Override
    public void writeDefaultConfig() {

    }
}
