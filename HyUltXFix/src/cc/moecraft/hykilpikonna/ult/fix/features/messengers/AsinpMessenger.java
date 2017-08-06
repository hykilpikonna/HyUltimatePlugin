package cc.moecraft.hykilpikonna.ult.fix.features.messengers;

import cc.moecraft.hykilpikonna.ult.Messengers;
import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AsinpMessenger extends Messenger 
{
    public AsinpMessenger() 
    {
        super(Fix.getInstance(), Features.getASINP().getFriendlyName());
    }

    @Override
    public String prefix() 
    {
        return "&7&l[&6&l防卡地狱门&7&l] &r";
    }

    @Override
    public void writeConfig() 
    {
        addDefault("teleport", "因为检测到您可能被卡在传送门里面了, 所以您已被传送到传送点");
        addDefault("command_error", RED + "指令错误");
        addDefault("command_console_not_allowed", RED + "后台不能设置传送点");
        addDefault("command_setspawn_success", String.format("%s您已将传送点设置为[%s, %s, %s, %s, %s, %s]", GREEN, "%WORLD%", "%X%", "%Y%", "%Z%", "%YAW%", "%PITCH%"));
    }
}
