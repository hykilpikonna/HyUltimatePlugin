package cc.moecraft.hykilpikonna.ult.fun.features.messengers;

import cc.moecraft.hykilpikonna.ult.Messengers;
import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ElevatorMessenger extends Messenger
{
    public ElevatorMessenger()
    {
        super(Fun.getInstance(), Features.getElevator().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l电梯&7&l] &r";
    }

    @Override
    public void writeConfig()
    {

    }
}
