package cc.moecraft.hykilpikonna.ult;

import cc.moecraft.hykilpikonna.ult.api.Config;

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
        addDefault("Debug", false);

        addDefault("AutoUpdate.Enable", true);
        addDefault("AutoUpdate.CheckDelay", 3200);

        addDefault("AutoUpdate.HyUltimatePlugin.Enable", true);
        addDefault("AutoUpdate.HyUltimatePlugin.Repeat", false);
        addDefault("AutoUpdate.HyUltimatePlugin.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUlt/plugin.yml");
        addDefault("AutoUpdate.HyUltimatePlugin.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUlt.jar");

        addDefault("AutoUpdate.Default.TimeoutInSeconds", 3);
        addDefault("AutoUpdate.Default.CheckDelayInSeconds", 3600);

        addDefault("AutoUpdate.HyEssentials", true);
        addDefault("AutoUpdate.YumNetworkCheckBypass", true);

        addDefault("AutoBackupConfig.Enable", true);
        addDefault("AutoBackupConfig.WhenPluginUpdate", true);

        addDefault("Plugins.HyUltXChat.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUltXChat/plugin.yml");
        addDefault("Plugins.HyUltXChat.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXChat.jar");

        addDefault("Plugins.HyUltXFix.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUltXFix/plugin.yml");
        addDefault("Plugins.HyUltXFix.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXFix.jar");

        addDefault("Plugins.HyUltXFun.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUltXFun/plugin.yml");
        addDefault("Plugins.HyUltXFun.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXFun.jar");

        addDefault("Plugins.HyUltXUtil.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUltXUtil/plugin.yml");
        addDefault("Plugins.HyUltXUtil.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXUtil.jar");

        addDefault("Plugins.HyUltXCommand.PluginYmlURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/out/production/HyUltXCommand/plugin.yml");
        addDefault("Plugins.HyUltXCommand.JarURL", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXCommand.jar");
    }

    @Override
    public void writeDefaultConfig() {

    }
}
