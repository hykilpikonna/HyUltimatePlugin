package cc.moecraft.hykilpikonna.ult;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/07/30 创建!
 * Created by Hykilpikonna on 2017/07/30!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Messengers extends Messenger
{
    public Messengers()
    {
        super(Main.getMain(), "HyUlt");
    }

    @Override
    public void writeConfig()
    {
        addDefault("help_message_text", GRAY + "# [最新帮助界面(点我)]");
        addDefault("help_message_click_link", "https://github.com/hykilpikonna/HyUltimatePlugin/blob/master/Help.yml");

        addDefault("reloading", GREEN + "插件正在重新加载....");
        addDefault("reload_complete", GREEN + "插件重载成功!");

        addDefault("command_reload_start", YELLOW + "正在重载配置...");
        addDefault("command_reload_complete", GREEN + "所有HyUlt插件配置重载成功!");

        addDefault("command_reload_hyplugins_start", YELLOW + "正在重载所有HyUlt插件...");
        addDefault("command_reload_hyplugins_complete", GREEN + "所有HyUlt插件重载成功!");

        addDefault("command_reload_all_start", YELLOW + "正在重载所有插件...");
        addDefault("command_reload_all_complete", GREEN + "所有插件重载成功!");

        addDefault("command_reload_specific_start", YELLOW + "正在重载%s插件...");
        addDefault("command_reload_specific_err_not_found", RED + "重载失败, 未找到%s插件.");
        addDefault("command_reload_specific_complete", GREEN + "%s插件重载成功!");

        addDefault("download_failed", RED + "文件下载失败! 详情请看后台");
        addDefault("download_failed_wrong_hyplugin_name", RED + "文件下载失败! 未找到该插件");

        addDefault("download_list_pre", GRAY + "# " + GREEN + "可下载的子插件: ");
        addDefault("download_list_one_plugin_first", GRAY + "# ");
        addDefault("download_list_one_plugin_pre", GRAY + "[" + GOLD);
        addDefault("download_list_one_plugin_hover", AQUA + "点击即可下载并安装%s子插件");
        addDefault("download_list_one_plugin_suf", GRAY + "] ");
        addDefault("download_list_suf", GRAY + "# " + "点击上方括号内文字即可一键下载");

        addDefault("downloaded_all", GRAY + "# " + GREEN + "您已下载所有子插件! 无需安装");
        addDefault("installed_list_none", GRAY + "# " + RED + "没有已安装的子插件!");

        addDefault("installed_list_pre", GRAY + "# " + GREEN + "已安装的子插件: ");
        addDefault("installed_list_one_plugin_first", GRAY + "# ");
        addDefault("installed_list_one_plugin_pre", GRAY + "[" + GOLD);
        addDefault("installed_list_one_plugin_suf", GRAY + "] ");

        addDefault("download_start", GRAY + "开始下载%s...");
        addDefault("download_finish", GREEN + "%s下载完成!");
        addDefault("install_finish", GREEN + "插件%s加载完成! 版本: %s");
        addDefault("install_failed", RED + "插件%s加载失败! 详情看后台");

        addDefault("line_front", GRAY + "#######################################");
        addDefault("line_back", GRAY + "#######################################");
    }

    @Override
    public String prefix() {
        return "&7&l[&6&lHy&3&lUltimate&7&l] &r";
    }
}
