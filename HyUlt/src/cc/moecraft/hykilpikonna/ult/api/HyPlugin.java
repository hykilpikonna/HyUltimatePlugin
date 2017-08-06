package cc.moecraft.hykilpikonna.ult.api;

import cc.moecraft.hykilpikonna.ult.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;

/**
 * 此类由 Hykilpikonna 在 2017/08/05 创建!
 * Created by Hykilpikonna on 2017/08/05!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class HyPlugin extends JavaPlugin
{
    private HyPluginData pluginData;

    @Override
    public void onEnable()
    {
        preInit();
        pluginData = new HyPluginData(name(), pluginFile(), jarUrl(), pluginYmlUrl(), autoUpdate(), autoUpdateRepeat());
        run();
        Main.getPlugins().add(this);
    }

    @Override
    public void onDisable() {}

    public abstract void preInit();
    public abstract void run();

    public abstract String name();
    public abstract File pluginFile();
    public abstract String jarUrl();
    public abstract String pluginYmlUrl();
    public abstract Boolean autoUpdate();
    public abstract Boolean autoUpdateRepeat();

    public HyPluginData getPluginData() {
        return pluginData;
    }

    public void setPluginData(HyPluginData pluginData) {
        this.pluginData = pluginData;
    }
}
