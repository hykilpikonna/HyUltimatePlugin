package cc.moecraft.hykilpikonna.ult.api;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URL;

/**
 * 此类由 Hykilpikonna 在 2017/08/05 创建!
 * Created by Hykilpikonna on 2017/08/05!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyPluginData
{
    private String name;
    private File pluginFile;
    private URL jarUrl;
    private URL pluginYmlUrl;
    private Boolean autoUpdate;
    private Boolean autoUpdateRepeat;

    public HyPluginData(String name, File pluginFile, String jarUrl, String pluginYmlUrl, Boolean autoUpdate, Boolean autoUpdateRepeat)
    {
        try
        {
            this.name = name;
            this.pluginFile = pluginFile;
            this.jarUrl = new URL(jarUrl);
            this.pluginYmlUrl = new URL(pluginYmlUrl);
            this.autoUpdate = autoUpdate;
            this.autoUpdateRepeat = autoUpdateRepeat;
        } catch (Exception ignored) {}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getJarUrl() {
        return jarUrl;
    }

    public void setJarUrl(URL jarUrl) {
        this.jarUrl = jarUrl;
    }

    public URL getPluginYmlUrl() {
        return pluginYmlUrl;
    }

    public void setPluginYmlUrl(URL pluginYmlUrl) {
        this.pluginYmlUrl = pluginYmlUrl;
    }

    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public Boolean getAutoUpdateRepeat() {
        return autoUpdateRepeat;
    }

    public void setAutoUpdateRepeat(Boolean autoUpdateRepeat) {
        this.autoUpdateRepeat = autoUpdateRepeat;
    }

    public File getPluginFile() {
        return pluginFile;
    }

    public void setPluginFile(File pluginFile) {
        this.pluginFile = pluginFile;
    }
}
