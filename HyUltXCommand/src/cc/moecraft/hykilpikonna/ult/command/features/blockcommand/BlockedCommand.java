package cc.moecraft.hykilpikonna.ult.command.features.blockcommand;

import cc.moecraft.hykilpikonna.ult.utils.PlayerUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class BlockedCommand
{
    private ArrayList<BlockedCommandProperties> properties;
    
    private String command;
    private String bypassPermission;
    private ArrayList<String> customMessage;
    private ArrayList<String> blockedWorlds;

    public BlockedCommand(String command, ArrayList<BlockedCommandProperties> properties)
    {
        this.command = command;
        this.properties = properties;
        BlockCommandStaticVariables.getBlockedCommandNames().add(command);
    }

    /**
     * 检查玩家输入的这条指令是否被墙
     * @param player
     * @return 被墙返回True, 不被墙返回False
     */
    public boolean check(Player player)
    {
        if (properties.contains(BlockedCommandProperties.BYPASSABLE) && bypassPermission != null && player.hasPermission(bypassPermission)) return false;
        if (properties.contains(BlockedCommandProperties.WORLD) && !(blockedWorlds.contains(player.getWorld().getName()))) return false;
        if (properties.contains(BlockedCommandProperties.SEND_MESSAGE))
            if (properties.contains(BlockedCommandProperties.CUSTOM_MESSAGE)) PlayerUtils.sendMessageList(player, customMessage);
            else player.sendMessage(BlockCommandStaticVariables.getDefaultMessage());

        return true;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public void setBypassPermission(String bypassPermission)
    {
        this.bypassPermission = bypassPermission;
    }

    public String getBypassPermission()
    {
        return bypassPermission;
    }

    public ArrayList<String> getCustomMessage()
    {
        return customMessage;
    }

    public void setCustomMessage(ArrayList<String> customMessage)
    {
        this.customMessage = customMessage;
    }

    public ArrayList<String> getBlockedWorlds()
    {
        return blockedWorlds;
    }

    public void setBlockedWorlds(ArrayList<String> blockedWorlds)
    {
        this.blockedWorlds = blockedWorlds;
    }

    public ArrayList<BlockedCommandProperties> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<BlockedCommandProperties> properties)
    {
        this.properties = properties;
    }
}
