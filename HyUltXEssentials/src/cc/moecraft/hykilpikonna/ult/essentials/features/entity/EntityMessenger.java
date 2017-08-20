package cc.moecraft.hykilpikonna.ult.essentials.features.entity;

import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.essentials.HyUltXEssentials;
import cc.moecraft.hykilpikonna.ult.essentials.features.Features;
import org.bukkit.ChatColor;

/**
 * 此类由 Hykilpikonna 在 2017/08/20 创建!
 * Created by Hykilpikonna on 2017/08/20!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class EntityMessenger extends Messenger
{
    public EntityMessenger()
    {
        super(HyUltXEssentials.getInstance(), Features.getEntityCommand().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "§7§l[§6§l实体生成§7§l] §r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("error_entity_not_found", ChatColor.RED + "实体%entity%在%class%中未找到");
        addDefault("failed", ChatColor.RED + "生成失败, 请在后台查询报错信息");
        addDefault("success", ChatColor.GREEN + "生成成功, 已生成%amount%个%entity%");
        addDefault("failed_to_read_tag", String.format("%s您输入的标签%s无法识别", ChatColor.RED, "%TAG%"));
    }
}
