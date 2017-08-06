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
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(GRAY + "###################" + BLUE + BOLD + "Hy" + AQUA + BOLD + "Ultimate" + GRAY + "###################");
        arrayList.add(GRAY + "#" + AQUA + "最新帮助界面: ");
        arrayList.add(GRAY + "#" + BLUE + "https://github.com/hykilpikonna/HyUltimatePlugin/blob/master/Help.yml");
        arrayList.add(GRAY + "##################################################");
        addDefault("help_message", arrayList);

        addDefault("reloading", GREEN + "插件正在重新加载....");
        addDefault("reload_complete", GREEN + "插件重载成功!");

        addDefault("download_failed", RED + "文件下载失败! 详情请看后台");
        addDefault("download_failed_wrong_hyplugin_name", RED + "文件下载失败! 未找到该插件");
    }

    @Override
    public String prefix() {
        return "&7&l[&6&lHy&3&lUltimate&7&l] &r";
    }
}
