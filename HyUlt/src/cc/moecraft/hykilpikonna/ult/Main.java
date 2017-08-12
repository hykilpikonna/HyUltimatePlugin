package cc.moecraft.hykilpikonna.ult;

 import cc.moecraft.hykilpikonna.essentials.updater.UrlUpdater;
 import cc.moecraft.hykilpikonna.ult.api.HyPlugin;
 import cc.moecraft.hykilpikonna.ult.api.ingame.ActionBarUtils;
 import cc.moecraft.hykilpikonna.ult.commands.HyuCommand;
 import cc.moecraft.hykilpikonna.ult.utils.HyEssentialsDetection;
 import cc.moecraft.hykilpikonna.ult.utils.YumBypass;
 import net.md_5.bungee.api.chat.ClickEvent;
 import net.md_5.bungee.api.chat.ComponentBuilder;
 import net.md_5.bungee.api.chat.HoverEvent;
 import net.md_5.bungee.api.chat.TextComponent;
 import org.bukkit.Bukkit;
 import org.bukkit.ChatColor;
 import org.bukkit.command.CommandSender;
 import org.bukkit.configuration.file.YamlConfiguration;
 import org.bukkit.entity.Player;
 import org.bukkit.plugin.Plugin;
 import org.bukkit.plugin.java.JavaPlugin;

 import java.io.File;
 import java.io.IOException;
 import java.util.ArrayList;

 import static cc.moecraft.hykilpikonna.ult.Setup.*;
 import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.reload;

 /**
 * 此类由 Hykilpikonna 在 2017/06/21 创建!
 * Created by Hykilpikonna on 2017/06/21!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Main extends HyPlugin
{
    private static ArrayList<HyPlugin> plugins = new ArrayList<>();

    private static Main main = null;
    public static Messengers messengers;
    public static Configs configs;
    public static Permissions permissions;
    private static HyuCommand command;

    private static ActionBarUtils actionBarUtils;

    public static ActionBarUtils getActionBarUtils()
    {
        return actionBarUtils;
    }

    public static void setActionBarUtils(ActionBarUtils actionBarUtils)
    {
        Main.actionBarUtils = actionBarUtils;
    }

    @Override
    public void preInit()
    {
        main = this;
        configs = new Configs();
        messengers = new Messengers();
        permissions = new Permissions();

        command = new HyuCommand();

        setActionBarUtils(new ActionBarUtils());

        if (!(Main.getMain().getConfig().contains("AutoUpdate.YumNetworkCheckBypass")) || Main.getMain().getConfig().getBoolean("AutoUpdate.YumNetworkCheckBypass"))
            YumBypass.bypassYUM("HyUltimatePlugin");

        HyEssentialsDetection.detectHyEss();
    }

    @Override
    public void run()
    {
        HyPluginsDownloadLink CHAT = new HyPluginsDownloadLink("HyUltXChat", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXChat.jar");
        HyPluginsDownloadLink FIX = new HyPluginsDownloadLink("HyUltXFix", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXFix.jar");
        HyPluginsDownloadLink FUN = new HyPluginsDownloadLink("HyUltXFun", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXFun.jar");
        HyPluginsDownloadLink UTIL = new HyPluginsDownloadLink("HyUltXUtil", "https://raw.githubusercontent.com/hykilpikonna/HyUltimatePlugin/master/Build/HyUltXUtil.jar");

        setup();
        setupAutoUpdate();
        loglogger.log("[加载]此插件加载完成!");
    }

    @Override
    public String name() {
        return "HyUltimatePlugin";
    }

    @Override
    public File pluginFile() {
        return getFile();
    }

    @Override
    public String jarUrl() {
        return configs.getString("AutoUpdate.HyUltimatePlugin.JarURL");
    }

    @Override
    public String pluginYmlUrl() {
        return configs.getString("AutoUpdate.HyUltimatePlugin.PluginYmlURL");
    }

    @Override
    public Boolean autoUpdate() {
        return configs.getBoolean("AutoUpdate.HyUltimatePlugin.Enable");
    }

    @Override
    public Boolean autoUpdateRepeat() {
        return configs.getBoolean("AutoUpdate.HyUltimatePlugin.Repeat");
    }

    private void setupAutoUpdate()
    {
        if (configs.getBoolean("AutoUpdate.Enable"))
        {
            Integer checkDelay = getConfig().getInt("AutoUpdate.CheckDelay");
            for (HyPlugin plugin : plugins)
            {
                //创建一个自动更新对象并自动检查更新
                urlUpdater = new UrlUpdater(
                        plugin.getPluginData().getPluginFile(), plugin,
                        plugin.getPluginData().getJarUrl(),
                        plugin.getPluginData().getPluginYmlUrl(),
                        plugin.getPluginData().getAutoUpdateRepeat(), checkDelay);

                if(!plugin.getPluginData().getAutoUpdateRepeat()) urlUpdater.update();
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
    //TODO: 加开关
    {
        if (true)
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

    public static void registerHyPlugin(HyPlugin plugin)
    {
        plugins.add(plugin);
    }

    public void reloadAllHyPlugin()
    {
        ArrayList<Plugin> pluginArrayList = new ArrayList<>();
        pluginArrayList.addAll(plugins);
        plugins = new ArrayList<>();
        for (Plugin plugin : pluginArrayList)
        {
            if (!(plugin.getDescription().getName().equals(this.getDescription().getName()))) reload(plugin);
        }
    }

    public static ArrayList<HyPlugin> getPlugins() {
        return plugins;
    }

    public static void sendHelpMessage(CommandSender sender)
    {
        TextComponent message = new TextComponent();
        message.addExtra(messengers.getWithPrefix("line_front") + "\n" + messengers.getPrefix());
        TextComponent click = new TextComponent();
        click.addExtra(messengers.get("help_message_text"));
        click.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, messengers.get("help_message_click_link")));
        click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(messengers.get("help_message_hover_text")).create()));
        message.addExtra(click);
        message.addExtra("\n" + messengers.getWithPrefix("line_back"));
        if (sender instanceof Player) ((Player) sender).spigot().sendMessage(message);
        else sender.sendMessage(message.getText());
    }
}
