package cc.moecraft.hykilpikonna.ult.chat.features.messengers;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ClsMessenger extends Messenger
{
    /**
     * 新建一个消息配置文件
     * 会自动写入默认消息
     */
    public ClsMessenger() 
    {
        super(Chat.getHyUltXChat(), Features.CLS.getFriendlyName());
    }

    @Override
    public void writeConfig() 
    {
        addDefault("console_unallowed_cls_self", String.format("%s后台不能给自己清屏", RED));
        addDefault("cls_all_success", String.format("%s已给所有玩家清屏", GREEN));
        addDefault("cls_player_success", GREEN + "已给玩家%PLAYER%清屏");
        addDefault("cls_player_not_found", RED + "%PLAYER%玩家不存在");
    }

    @Override
    public String prefix() {
        return "&7&l[&6&l清屏&7&l] &r";
    }
}
