package cc.moecraft.hykilpikonna.ult;

 import cc.moecraft.hykilpikonna.essentials.updater.UrlUpdater;
 import cc.moecraft.hykilpikonna.ult.api.Messenger;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;

 import java.io.File;
 import java.io.IOException;
 import java.net.MalformedURLException;
 import java.net.URL;
 import java.util.List;

 import static cc.moecraft.hykilpikonna.ult.Setup.*;
 import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.findPluginFile;
 import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.reload;
 import static cc.moecraft.hykilpikonna.ult.utils.UrlUpdater.downloadHyEssentials;
 import static org.bukkit.ChatColor.GREEN;
 import static org.bukkit.ChatColor.RED;

 /**
 * 此类由 Hykilpikonna 在 2017/06/21 创建!
 * Created by Hykilpikonna on 2017/06/21!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Main extends JavaPlugin
{
    private static Main main = null;
    public static Messenger messenger;

    /**
     * 加载插件
     */
    public void onEnable()
    {
        main = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        try
        {
            bypassYUM();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        detectHyEss();
        setup();
        setupAutoUpdate();
        saveConfig();
        loglogger.log("[加载]此插件加载完成!");
    }

    private void detectHyEss()
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

    private void setupAutoUpdate()
    {
        if (getConfig().getBoolean("AutoUpdate.This.Enable"))
        {
            //Main 的自动更新 (必须Setup后执行)
            try
            {
                if (getConfig().getBoolean("AutoUpdate.This.Repeat"))
                {
                    //创建一个自动更新对象并自动检查更新
                    urlUpdater = new UrlUpdater(
                            this.getFile(),
                            this,
                            new URL(getConfig().getString("AutoUpdate.This.JarURL")),
                            new URL(getConfig().getString("AutoUpdate.This.PluginYmlURL")),
                            true,
                            getConfig().getInt("AutoUpdate.This.CheckDelay")
                    );
                }
                else
                {
                    //创建一个自动更新对象并一次性检查
                    urlUpdater = new UrlUpdater(
                            this.getFile(),
                            this,
                            new URL(getConfig().getString("AutoUpdate.This.JarURL")),
                            new URL(getConfig().getString("AutoUpdate.This.PluginYmlURL")),
                            false,
                            getConfig().getInt("AutoUpdate.This.CheckDelay")
                    );
                    urlUpdater.update();
                }
            }
            catch (MalformedURLException ignored)
            {
                loglogger.Debug(RED + "自动更新失败, URL生成失败");
            }
        }
    }

    /**
     * 卸载插件
     */
    public void onDisable()
    {
        loglogger.log("此插件已卸载!");
    }

    /**
     * 获取实例
     * @return 实例
     */
    public static Main getMain()
    {
        return main;
    }

    public static void tempDebug(String string)
    {
        if (getMain().getConfig().getBoolean("Debug"))
            Bukkit.getConsoleSender().sendMessage("[" +
                    ChatColor.RED +
                    "DEBUG" +
                    ChatColor.WHITE +
                    "(" +
                    ChatColor.YELLOW +
                    Thread.currentThread().getStackTrace()[2].getClassName() +
                    "." +
                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                    ":" +
                    Thread.currentThread().getStackTrace()[2].getLineNumber() +
                    ChatColor.WHITE +
                    ")] " +
                    string);
    }

    public static void tempLog(String string)
    {
        Bukkit.getConsoleSender().sendMessage("[HyUltimatePlugin] " + ChatColor.YELLOW + string);
    }

    /**
     * 绕过YUM的网络检测和线程检测
     * @throws IOException 读取YUM配置失败
     */
    private static void bypassYUM() throws IOException
    {
        tempLog("正在检测YUM....");
        Plugin yum = Bukkit.getPluginManager().getPlugin("Yum");
        if (yum != null)
        {
            boolean reloadYum = false;

            if (!(getMain().getConfig().contains("AutoUpdate.YumNetworkCheckBypass")) || getMain().getConfig().getBoolean("AutoUpdate.YumNetworkCheckBypass"))
            {
                File yumNetworkFile = new File("plugins/Yum/network.yml");
                YamlConfiguration yumNetworkConfig = YamlConfiguration.loadConfiguration(yumNetworkFile);
                List<String> ignoreList = yumNetworkConfig.getStringList("Ignore");
                if (!(ignoreList.contains("HyUltimatePlugin")))
                {
                    tempLog("YUM拦截了HyUltimatePlugin对网络的访问, 正在解除拦截...");
                    ignoreList.add("HyUltimatePlugin");
                    yumNetworkConfig.set("Ignore", ignoreList);
                    yumNetworkConfig.save(yumNetworkFile);
                    reloadYum = true;
                }
                else
                {
                    tempLog(GREEN + "YUM配置中网络设置已有本插件的例外");
                }
            }
            else
            {
                tempLog(RED + "未开启绕过YUM网络检测");
            }

            if (reloadYum)
            {
                reload(yum);
                tempLog(GREEN + "拦截解除成功!");
            }
        }
        else
        {
            tempLog(GREEN + "未检测到YUM");
        }
    }
}
