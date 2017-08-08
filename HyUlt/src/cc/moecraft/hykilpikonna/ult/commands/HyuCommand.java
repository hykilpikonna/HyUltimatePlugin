package cc.moecraft.hykilpikonna.ult.commands;

import cc.moecraft.hykilpikonna.ult.HyPluginsDownloadLink;
import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.utils.PluginUtil;
import cc.moecraft.hykilpikonna.ult.utils.UrlUpdater;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;
import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.reload;

/**
 * 此类由 Hykilpikonna 在 2017/08/01 创建!
 * Created by Hykilpikonna on 2017/08/01!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyuCommand extends CommandRunner
{
    public HyuCommand()
    {
        super(
                Main.getMain(), "hyu",
                new TabCompletesBuilder()
                        .append("reload -hyplugins")
                        .append("reload -all")
                        .append("reload %PLUGINS%")
                        .append("install -url:")
                        .append("install -hyplugins:%HYPLUGINS%")
                        .append("load")
                        .append("unload %PLUGINS%")
                        .append("delete %PLUGINS%")
                        .build()
        );
        //TODO: Tab补全的占位符替换
    }

    @Override
    public void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args)
    {
        if (args.size() == 0)
        {
            Main.messengers.sendMessageList(sender, "help_message");
            return;
        }
        String commandName = args.get(0);
        args.remove(0);
        switch (commandName)
        {
            case "reload":
                commandReload(sender, args);
                break;
            case "install":
                if (args.size() == 0 || args.size() == 1) commandInstall(sender, args); else Main.messengers.sendMessageList(sender, "help_message");
                break;
        }
    }

    private void commandReload(CommandSender sender, ArrayList<String> args)
    {
        if (args.size() == 0)
        {
            if (!Main.permissions.hasPermission(sender, "hyult.command.admin.reload", true)) return;
            Main.messengers.sendMessage(sender, "command_reload_start");
            Config.reloadAllConfig();
            Main.messengers.sendMessage(sender, "command_reload_complete");
            return;
        }
        switch (args.get(0).toLowerCase())
        {
            case "-hyplugins":
                if (!Main.permissions.hasPermission(sender, "hyult.command.admin.reload.hyplugins", true)) return;
                Main.messengers.sendMessage(sender, "command_reload_hyplugins_start");
                Main.getMain().reloadAllHyPlugin();
                Main.messengers.sendMessage(sender, "command_reload_hyplugins_complete");
                break;
            case "-all":
                if (!Main.permissions.hasPermission(sender, "hyult.command.admin.reload.all", true)) return;
                Main.messengers.sendMessage(sender, "command_reload_all_start");
                for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
                    if (plugin.isEnabled())
                        if (!(plugin.getDescription().getName().equals(Main.getMain().getDescription().getName()))) reload(plugin);
                Main.messengers.sendMessage(sender, "command_reload_all_complete");
                break;
            default:
                String pluginName = getTheRestToString(args, 0);
                Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
                if (plugin == null)
                {
                    sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_reload_specific_err_not_found"), pluginName));
                    return;
                }
                if (!Main.permissions.hasPermissionNotInConfig(sender, "hyult.command.admin.reload." + plugin.getName().toLowerCase(), true)) return;
                sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_reload_specific_start"), pluginName));
                reload(plugin);
                sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_reload_specific_complete"), plugin.getName()));
                break;
        }
    }

    private void commandInstall(CommandSender sender, ArrayList<String> args)
    {
        switch (args.size())
        {
            case 0:
                //TODO: 文字GUI
                break;
            case 1:
                String arg = args.get(0).toLowerCase();
                if (arg.contains("-url:") && Main.permissions.hasPermission(sender, "hyult.command.admin.install.url", true))
                    asyncDownloadFile(sender, arg.replace("-url:", ""));
                else if (arg.contains("-hyplugins:") && Main.permissions.hasPermission(sender, "hyult.command.admin.install.hyplugins", true))
                {
                    String pluginName = arg.replace("-hyplugins:", "");
                    HyPluginsDownloadLink hyPluginsDownloadLink = HyPluginsDownloadLink.getPluginWithName(pluginName);
                    if (hyPluginsDownloadLink != null) asyncDownloadFile(hyPluginsDownloadLink.getJarURL());
                    else Main.messengers.sendMessage(sender, "download_failed_wrong_hyplugin_name");
                }
                else Main.messengers.sendMessageList(sender, "help_message");
                break;
            default:
                Main.messengers.sendMessageList(sender, "help_message");
                //TODO: 加安装完成消息
                break;
        }
    }

    private void asyncDownloadFile(CommandSender sender, String url)
    {
        try
        {
            asyncDownloadFile(new URL(url));
        }
        catch (MalformedURLException e)
        {
            Main.messengers.sendMessage(sender, "download_failed");
            e.printStackTrace();
        }
    }

    private void asyncDownloadFile(URL url)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                tempDebug("开始下载");
                File file = UrlUpdater.downloadFile(url, "plugins/");
                PluginUtil.load(file);
            }
        }.runTaskAsynchronously(Main.getMain());
    }
}
