package cc.moecraft.hykilpikonna.ult.fix;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.HyPlugin;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2017/07/27 创建!
 * Created by Hykilpikonna on 2017/07/27!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Fix extends HyPlugin
{
    private static Fix instance;

    private Switches switches;
    private Features features;

    public static Fix getInstance()
    {
        return instance;
    }

    @Override
    public void preInit()
    {
        instance = this;
        switches = new Switches();
        features = new Features();
    }

    @Override
    public void run() {

    }

    @Override
    public String name() {
        return "HyUltXFix";
    }

    @Override
    public File pluginFile() {
        return getFile();
    }

    @Override
    public String jarUrl() {
        return Main.configs.getString("Plugins." + name() + ".JarURL");
    }

    @Override
    public String pluginYmlUrl() {
        return Main.configs.getString("Plugins." + name() + ".PluginYmlURL");
    }

    @Override
    public Boolean autoUpdate() {
        return switches.getBoolean("AutoUpdate.Enable");
    }

    @Override
    public Boolean autoUpdateRepeat() {
        return switches.getBoolean("AutoUpdate.Repeat");
    }

    public Features getFeatures() {
        return features;
    }

    public Switches getSwitches() {
        return switches;
    }
}
