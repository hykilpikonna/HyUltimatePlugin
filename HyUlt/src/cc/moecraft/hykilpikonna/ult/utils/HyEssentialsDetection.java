package cc.moecraft.hykilpikonna.ult.utils;

import org.bukkit.Bukkit;

import java.net.MalformedURLException;
import java.net.URL;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.findPluginFile;
import static cc.moecraft.hykilpikonna.ult.utils.UrlUpdater.downloadHyEssentials;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/05 创建!
 * Created by Hykilpikonna on 2017/08/05!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyEssentialsDetection {
    public static void detectHyEss()
    {
        if (Bukkit.getPluginManager().getPlugin("HyEssentials") == null)
        {
            tempDebug("正在直接下载HyEssentials");
            try
            {
                downloadHyEssentials(new URL("https://raw.githubusercontent.com/hykilpikonna/HyEssentials/master/out/artifacts/HyEssentials/HyEssentials.jar"));
            }
            catch (MalformedURLException e)
            {
                tempDebug(RED + "HyEssentials下载失败");
                e.printStackTrace();
            }
        }

        tempDebug("正在设置HyEssentials的自动更新");
        try
        {
            cc.moecraft.hykilpikonna.ult.utils.UrlUpdater updater = new cc.moecraft.hykilpikonna.ult.utils.UrlUpdater(
                    findPluginFile("HyEssentials"),
                    Bukkit.getPluginManager().getPlugin("HyEssentials"),
                    new URL("https://raw.githubusercontent.com/hykilpikonna/HyEssentials/master/out/artifacts/HyEssentials/HyEssentials.jar"),
                    new URL("https://raw.githubusercontent.com/hykilpikonna/HyEssentials/master/src/plugin.yml"),
                    false
            );
            updater.update();
        }
        catch (MalformedURLException e)
        {
            tempDebug(RED + "自动更新失败");
            e.printStackTrace();
        }
    }
}
