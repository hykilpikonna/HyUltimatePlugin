package cc.moecraft.hykilpikonna.ult.api;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/07/25 创建!
 * Created by Hykilpikonna on 2017/07/25!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class Database extends Config
{
    public Database(JavaPlugin plugin, String fileName)
    {
        super(plugin, "Databases", fileName, "yml", false);
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig() {}

    @Override
    public void writeDefaultConfig() {}
}
