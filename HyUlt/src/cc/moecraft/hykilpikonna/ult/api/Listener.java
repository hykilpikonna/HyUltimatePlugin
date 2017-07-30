package cc.moecraft.hykilpikonna.ult.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class Listener implements org.bukkit.event.Listener
{
    private Plugin plugin;

    public Listener(JavaPlugin plugin)
    {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    public Plugin getPlugin()
    {
        return plugin;
    }
}
