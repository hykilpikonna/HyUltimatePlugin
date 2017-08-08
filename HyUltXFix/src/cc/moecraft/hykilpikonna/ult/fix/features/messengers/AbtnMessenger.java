package cc.moecraft.hykilpikonna.ult.fix.features.messengers;

import cc.moecraft.hykilpikonna.ult.Messengers;
import cc.moecraft.hykilpikonna.ult.api.Messenger;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/07 创建!
 * Created by Hykilpikonna on 2017/08/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AbtnMessenger extends Messenger
{
    public AbtnMessenger(JavaPlugin plugin, String featureID)
    {
        super(plugin, featureID);
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l地狱基岩层反建&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("teleported", RED + "检测到您在地狱基岩层上面, 已将您传送下去!");
        addDefault("build_event_cancelled", RED + "地狱基岩层上无法建筑!");
        addDefault("destroy_event_cancelled", RED + "地狱基岩层上无法破坏!");
    }
}
