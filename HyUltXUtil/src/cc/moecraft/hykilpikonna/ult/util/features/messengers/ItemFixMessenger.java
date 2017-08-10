package cc.moecraft.hykilpikonna.ult.util.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.util.Util;
import cc.moecraft.hykilpikonna.ult.util.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/09 创建!
 * Created by Hykilpikonna on 2017/08/09!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ItemFixMessenger extends Messenger
{
    public ItemFixMessenger()
    {
        super(Util.getInstance(), Features.getItemFix().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l修复&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("fix_self_console_unallowed", String.format("%s您没有输入玩家参数, 后台不支持修复自己的物品", RED));
        addDefault("fix_all_self_console_unallowed", String.format("%s您没有输入玩家参数, 后台不支持修复自己的全部物品", RED));
        addDefault("fix_player_not_found", String.format("%s玩家不存在!", RED));
        addDefault("fix_self_success", String.format("%s您成功修复了您的手中物品", GREEN));
        addDefault("fix_all_self_success", String.format("%s您成功修复了您的全部物品, 有%s个物品未被修复", GREEN, "%COUNT%"));
        addDefault("fix_all_other_success_sender", String.format("%s您成功修复了%s的全部物品, 有%s个物品未被修复", GREEN, "%TARGETPLAYER%", "%COUNT%"));
        addDefault("fix_all_other_success_target", String.format("%s%s修复了您的全部物品, 有%s个物品未被修复", GREEN, "%PLAYER%", "%COUNT%"));
        addDefault("fix_other_success_sender", String.format("%s您成功修复了%s的手中物品", GREEN, "%TARGETPLAYER"));
        addDefault("fix_other_success_target", String.format("%s%s修复了您的手中物品", GREEN, "%PLAYER%"));
    }
}
