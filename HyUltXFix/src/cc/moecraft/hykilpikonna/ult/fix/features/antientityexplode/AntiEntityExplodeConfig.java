package cc.moecraft.hykilpikonna.ult.fix.features.antientityexplode;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.fix.Fix;
import cc.moecraft.hykilpikonna.ult.fix.features.Features;

/**
 * 此类由 Hykilpikonna 在 2017/08/24 创建!
 * Created by Hykilpikonna on 2017/08/24!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class AntiEntityExplodeConfig extends Config
{
    public AntiEntityExplodeConfig()
    {
        super(Fix.getInstance(), "Features", Features.getAntiEntityExplode().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        AntiEntityExplodeListener.enableExplodingDamage = getBoolean("EnableExplodingDamage");
    }

    @Override
    public void writeConfig()
    {
        addDefault("EnableExplodingDamage", true);
    }

    @Override
    public void writeDefaultConfig()
    {

    }
}
