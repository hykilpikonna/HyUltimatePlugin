package cc.moecraft.hykilpikonna.ult.util.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierMessenger extends Messenger
{
    public FishModifierMessenger()
    {
        super(Util.getInstance(), Features.getFishModifier().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l钓鱼&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("command_console_not_available", "后台暂时没有可用指令");
        addDefault("command_add_success", GREEN + "成功在水池添加手中物品");
        addDefault("command_add_no_item_in_hand", RED + "添加失败, 手中没有物品");
        addDefault("command_not_enough_args", RED + "参数不足");
        addDefault("command_not_number", RED + "输入的几率不是数字");
    }
}
