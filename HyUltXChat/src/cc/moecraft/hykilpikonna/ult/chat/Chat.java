package cc.moecraft.hykilpikonna.ult.chat;

import cc.moecraft.hykilpikonna.essentials.logger.Logger;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;

/**
 * 此类由 Hykilpikonna 在 2017/07/27 创建!
 * Created by Hykilpikonna on 2017/07/27!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Chat extends JavaPlugin
{
    private static Chat instance;

    public Logger logger;
    public Switches switches;
    public Features features;

    public static Chat getHyUltXChat()
    {
        return instance;
    }

    @Override
    public void onEnable()
    {
        tempDebug("执行了onEnable");
        instance = this;

        logger = new Logger("HyUltXChat", true);
        switches = new Switches();
        features = new Features();

        if (Bukkit.getPluginManager().getPlugin("HyUltimatePlugin") != null)
        {

        }
        else
        {
            logger.Debug("未安装前置插件");
        }
    }
}
