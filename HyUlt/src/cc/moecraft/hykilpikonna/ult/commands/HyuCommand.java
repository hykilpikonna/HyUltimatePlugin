package cc.moecraft.hykilpikonna.ult.commands;

import cc.moecraft.hykilpikonna.ult.HyPluginsDownloadLink;
import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.CommandRunner;
import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletesBuilder;
import cc.moecraft.hykilpikonna.ult.utils.PluginUtil;
import cc.moecraft.hykilpikonna.ult.utils.UrlUpdater;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;
import static cc.moecraft.hykilpikonna.ult.utils.ArrayUtils.getTheRestToString;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.*;

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
            Main.sendHelpMessage(sender);
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
                if (args.size() == 0 || args.size() == 1) commandInstall(sender, args); else Main.sendHelpMessage(sender);
                break;
            case "unload":
                if (args.size() == 1) commandUnload(sender, args); else Main.sendHelpMessage(sender);
                break;
            case "delete":
                if (args.size() == 1) commandDelete(sender, args); else Main.sendHelpMessage(sender);
                break;
            case "load":
                if (args.size() >= 1) commandLoad(sender, args); else Main.sendHelpMessage(sender);
                break;
        }
    }

    private void commandLoad(CommandSender sender, ArrayList<String> args)
    {
        if (!Main.permissions.hasPermissionNotInConfig(sender, "hyult.command.admin.load", true)) return;
        String pluginName = getTheRestToString(args, 0);
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_load_specific_start"), pluginName));
        if (loadFileName(pluginName)) sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_load_specific_complete"), pluginName)); else sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_load_specific_err_not_found"), pluginName));
    }

    private void commandDelete(CommandSender sender, ArrayList<String> args)
    {
        String pluginName = getTheRestToString(args, 0);
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null)
        {
            sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_err_not_found"), pluginName));
            return;
        }
        if (!Main.permissions.hasPermissionNotInConfig(sender, "hyult.command.admin.delete", true)) return;
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_start"), pluginName));
        delete(plugin);
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_complete"), pluginName));
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_delete_specific_complete"), pluginName));
    }

    private void commandUnload(CommandSender sender, ArrayList<String> args)
    {
        String pluginName = getTheRestToString(args, 0);
        Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginName);
        if (plugin == null)
        {
            sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_err_not_found"), pluginName));
            return;
        }
        if (!Main.permissions.hasPermissionNotInConfig(sender, "hyult.command.admin.unload", true)) return;
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_start"), pluginName));
        unload(plugin);
        sender.sendMessage(String.format(Main.messengers.getWithPrefix("command_unload_specific_complete"), pluginName));
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
                sendInstallMessage(sender);
                break;
            case 1:
                String arg = args.get(0).toLowerCase();
                if (arg.contains("-url:") && Main.permissions.hasPermission(sender, "hyult.command.admin.install.url", true))
                    asyncDownloadFile(sender, arg.replace("-url:", ""), arg.replace("-url:", ""));
                else if (arg.contains("-hyplugins:") && Main.permissions.hasPermission(sender, "hyult.command.admin.install.hyplugins", true))
                {
                    String pluginName = arg.replace("-hyplugins:", "");
                    HyPluginsDownloadLink hyPluginsDownloadLink = HyPluginsDownloadLink.getPluginWithName(pluginName);
                    if (hyPluginsDownloadLink != null) asyncDownloadFile(sender, hyPluginsDownloadLink.getJarURL(), pluginName);
                    else Main.messengers.sendMessage(sender, "download_failed_wrong_hyplugin_name");
                }
                else Main.sendHelpMessage(sender);
                break;
            default:
                Main.sendHelpMessage(sender);
                break;
        }
    }

    private void sendInstallMessage(CommandSender sender)
    {
        sendFrontLine(sender);
        if (HyPluginsDownloadLink.getNotInstalledPluginsNameList().size() == 0)
        {
            Main.messengers.sendMessage(sender, "downloaded_all");
            sendInstalledPluginsMessage(sender);
            sendBackLine(sender);
            return;
        }
        TextComponent message = new TextComponent();
        message.addExtra(Main.messengers.getWithPrefix("download_list_pre") + "\n" + Main.messengers.getPrefix());
        message.addExtra(Main.messengers.get("download_list_one_plugin_first"));
        for (String name : HyPluginsDownloadLink.getNotInstalledPluginsNameList())
        {
            TextComponent onePlugin = new TextComponent();
            onePlugin.addExtra(Main.messengers.get("download_list_one_plugin_pre") + name);;
            onePlugin.addExtra(Main.messengers.get("download_list_one_plugin_suf"));
            onePlugin.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/hyu install -hyplugins:" + name));
            onePlugin.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(String.format(Main.messengers.get("download_list_one_plugin_hover"), name)).create()));
            //TODO: 修复不自动刷新的问题
            message.addExtra(onePlugin);
        }
        if (sender instanceof Player)
        {
            message.addExtra("\n" + Main.messengers.getWithPrefix("download_list_suf"));
            ((Player) sender).spigot().sendMessage(message);
        }
        else sender.sendMessage(message.getText());
        sendInstalledPluginsMessage(sender);
        sendBackLine(sender);
    }

    private void sendInstalledPluginsMessage(CommandSender sender)
    {
        if (HyPluginsDownloadLink.getInstalledPluginsNameList().size() == 0)
        {
            Main.messengers.sendMessage(sender, "installed_list_none");
            return;
        }
        TextComponent message = new TextComponent();
        message.addExtra(Main.messengers.getWithPrefix("installed_list_pre") + "\n" + Main.messengers.getPrefix());
        message.addExtra(Main.messengers.get("installed_list_one_plugin_first"));
        for (String name : HyPluginsDownloadLink.getInstalledPluginsNameList())
        {
            TextComponent onePlugin = new TextComponent();
            onePlugin.addExtra(Main.messengers.get("installed_list_one_plugin_pre"));
            onePlugin.addExtra(name);
            onePlugin.addExtra(Main.messengers.get("installed_list_one_plugin_suf"));
            message.addExtra(onePlugin);
        }
        if (sender instanceof Player) ((Player) sender).spigot().sendMessage(message);
        else sender.sendMessage(message.getText());
    }

    private void sendFrontLine(CommandSender sender)
    {
        Main.messengers.sendMessage(sender, "line_front");
    }

    private void sendBackLine(CommandSender sender)
    {
        Main.messengers.sendMessage(sender, "line_back");
    }

    private void asyncDownloadFile(CommandSender sender, String url, String pluginName)
    {
        try
        {
            asyncDownloadFile(sender, new URL(url), pluginName);
        }
        catch (MalformedURLException e)
        {
            Main.messengers.sendMessage(sender, "download_failed");
            e.printStackTrace();
        }
    }

    private void asyncDownloadFile(CommandSender sender, URL url, String pluginName)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                sender.sendMessage(String.format(Main.messengers.getWithPrefix("download_start"), pluginName));
                File file = UrlUpdater.downloadFile(url, "plugins/");
                sender.sendMessage(String.format(Main.messengers.getWithPrefix("download_finish"), pluginName));
                Plugin plugin = load(file);
                if (plugin != null) sender.sendMessage(String.format(Main.messengers.getWithPrefix("install_finish"), plugin.getDescription().getName(), plugin.getDescription().getVersion()));
                else sender.sendMessage(String.format(Main.messengers.getWithPrefix("install_failed"), pluginName));
            }
        }.runTaskAsynchronously(Main.getMain());
    }

    public static Plugin load(File pluginFile)
    {
        Plugin target;

        try
        {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException | InvalidPluginException e)
        {
            e.printStackTrace();
            return null;
        }

        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);

        return target;
    }
}
