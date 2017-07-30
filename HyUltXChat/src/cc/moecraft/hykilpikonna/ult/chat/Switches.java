package cc.moecraft.hykilpikonna.ult.chat;

import cc.moecraft.hykilpikonna.ult.api.Config;
import org.bukkit.plugin.Plugin;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Switches extends Config
{
    public Switches()
    {
        super(Chat.getHyUltXChat(), "", "Switches");
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig() {
        addDefault("Cls.Enable", true);
        addDefault("Nick.Enable", true);
    }

    @Override
    public void writeDefaultConfig() {}
}
