package cc.moecraft.hykilpikonna.ult.chat.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.chat.Chat;

import static cc.moecraft.hykilpikonna.ult.chat.features.Features.NICK;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class NickConfig extends Config
{
    public NickConfig()
    {
        super(Chat.getHyUltXChat(), "Features", NICK.getFriendlyName());
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeConfig() {
        addDefault("MaxLength",  30);
        addDefault("MinLength",  3);
    }

    @Override
    public void writeDefaultConfig() {

    }
}
