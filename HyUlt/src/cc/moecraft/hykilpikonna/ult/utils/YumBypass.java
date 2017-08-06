package cc.moecraft.hykilpikonna.ult.utils;

import cc.moecraft.hykilpikonna.ult.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static cc.moecraft.hykilpikonna.ult.Main.tempLog;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.reload;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/05 创建!
 * Created by Hykilpikonna on 2017/08/05!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class YumBypass
{
    /**
     * 绕过YUM的网络检测和线程检测
     * @param pluginName 插件名
     * @throws IOException 读取YUM配置失败
     */
    public static void bypassYUM(String pluginName)
    {
        tempLog("正在检测YUM....");
        Plugin yum = Bukkit.getPluginManager().getPlugin("Yum");
        if (yum != null)
        {
            boolean reloadYum = false;
            File yumNetworkFile = new File("plugins/Yum/network.yml");
            YamlConfiguration yumNetworkConfig = YamlConfiguration.loadConfiguration(yumNetworkFile);
            List<String> ignoreList = yumNetworkConfig.getStringList("Ignore");
            if (!(ignoreList.contains(pluginName)))
            {
                tempLog("YUM拦截了" + pluginName + "对网络的访问, 正在解除拦截...");
                ignoreList.add(pluginName);
                yumNetworkConfig.set("Ignore", ignoreList);
                try {
                    yumNetworkConfig.save(yumNetworkFile);
                } catch (IOException e) { e.printStackTrace(); }
                reloadYum = true;
            }
            else
            {
                tempLog(GREEN + "YUM配置中网络设置已有本插件的例外");
            }

            if (reloadYum) reload(yum);
        }
        else
        {
            tempLog(GREEN + "未检测到YUM");
        }
    }
}
