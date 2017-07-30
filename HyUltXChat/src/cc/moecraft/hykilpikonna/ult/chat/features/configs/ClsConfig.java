package cc.moecraft.hykilpikonna.ult.chat.features.configs;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.chat.Chat;
import cc.moecraft.hykilpikonna.ult.chat.features.calculations.ClsCalculations;
import cc.moecraft.hykilpikonna.ult.chat.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ClsConfig extends Config
{
    public ClsConfig()
    {
        super(Chat.getHyUltXChat(), "Features", Features.CLS.getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        for (int i = 0; i < getInt("Lines"); i++) ClsCalculations.message += " \n";
    }

    @Override
    public void writeConfig()
    {
        addDefault("Lines", 60);
    }

    @Override
    public void writeDefaultConfig() {

    }
}
