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
public class HealthMessenger extends Messenger
{
    public HealthMessenger()
    {
        super(Util.getInstance(), Features.getHealth().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l血量&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("listener_registered", "[事件监听器][注册]HealthAndSaturation已经注册监听");
        addDefault("value_more_then_2048", RED + "您输入的血量数值大于2048, 已自动设置为2048.");
        addDefault("command_missing_args", RED + "您没有输入参数");
        addDefault("command_console_not_allowed", String.format("%s后台不能对自己使用此指令, 需要指定一个目标", RED));
        addDefault("command_player_not_found", String.format("%s%s玩家不存在", RED, "%PLAYER%"));
        addDefault("command_not_numeric", String.format("%s输入的值不是数字", RED));
        addDefault("command_health_see", String.format("%s玩家%s有%s点血", GREEN, "%TARGETPLAYER%", "%HEALTH%"));
        addDefault("command_health_lock_sender", String.format("%s玩家%s的血量已锁定到%s点血", GREEN, "%TARGETPLAYER%", "%HEALTHLOCK%"));
        addDefault("command_health_lock_target", String.format("%s您的血量已被锁定到%s点血", GREEN, "%HEALTHLOCK%"));
        addDefault("command_health_unlock_sender", String.format("%s玩家%s的血量已解除锁定", GREEN, "%TARGETPLAYER%"));
        addDefault("command_health_unlock_target", String.format("%s您的血量已被解除锁定", GREEN));
        addDefault("command_health_max_sender", String.format("%s玩家%s的血量上限已设置到%s点血", GREEN, "%TARGETPLAYER%", "%MAX%"));
        addDefault("command_health_max_target", String.format("%s您的血量上限已被设置到%s点血", GREEN, "%MAX%"));
        addDefault("command_health_set_self", String.format("%s您成功地把您的血量设置为%s", GREEN, "%HEALTH%"));
        addDefault("command_health_set_sender", String.format("%s玩家%s的血量已设置到%s点血", GREEN, "%TARGETPLAYER%", "%HEALTH%"));
        addDefault("command_health_set_target", String.format("%s您的血量已被设置到%s点血", GREEN, "%HEALTH%"));
    }
}
