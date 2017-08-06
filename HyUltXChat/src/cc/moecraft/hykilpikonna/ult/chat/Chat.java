package cc.moecraft.hykilpikonna.ult.chat;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.HyPlugin;
import cc.moecraft.hykilpikonna.ult.api.HyPluginData;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;
import org.bukkit.Bukkit;

import java.io.File;

/**
 * 此类由 Hykilpikonna 在 2017/07/27 创建!
 * Created by Hykilpikonna on 2017/07/27!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Chat extends HyPlugin
{
    private static Chat instance;

    public Switches switches;
    public Features features;

    public static Chat getHyUltXChat()
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
    public void run()
    {

    }

    @Override
    public String name() {
        return "HyUltXChat";
    }

    @Override
    public File pluginFile() {
        return getFile();
    }

    @Override
    public String jarUrl() {
        return Main.configs.getString("Plugins.HyUltXChat.JarURL");
    }

    @Override
    public String pluginYmlUrl() {
        return Main.configs.getString("Plugins.HyUltXChat.PluginYmlURL");
    }

    @Override
    public Boolean autoUpdate() {
        return Chat.getHyUltXChat().switches.getBoolean("AutoUpdate.Enable");
    }

    @Override
    public Boolean autoUpdateRepeat() {
        return Chat.getHyUltXChat().switches.getBoolean("AutoUpdate.Repeat");
    }
}
