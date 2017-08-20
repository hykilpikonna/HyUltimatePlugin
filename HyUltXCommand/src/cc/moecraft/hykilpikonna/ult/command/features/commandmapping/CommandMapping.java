package cc.moecraft.hykilpikonna.ult.command.features.commandmapping;

import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import cc.moecraft.hykilpikonna.ult.utils.PlayerUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandMapping
{
    private ArrayList<CommandMappingProperties> properties;

    private String fromCommand;
    private ArrayList<Command> toCommand;
    private String bypassPermission;
    private ArrayList<String> customMessage;
    private ArrayList<String> blockedWorlds;
    private String permission;

    public CommandMapping(String fromCommand, ArrayList<Command> toCommand, ArrayList<CommandMappingProperties> properties)
    {
        this.fromCommand = fromCommand;
        this.toCommand = toCommand;
        this.properties = properties;
        CommandMappingStaticVariables.getCommandMappingNames().add(fromCommand);
    }

    /**
     * 检查玩家输入的这条指令是否被墙
     * @param player
     * @return 被墙返回True, 不被墙返回False
     */
    public ArrayList<Command> check(String fromCommand, Player player)
    {
        if (permission != null && !player.hasPermission(permission)) return null;
        if (!(fromCommand.equals(this.fromCommand))) return null;
        if (properties.contains(CommandMappingProperties.BYPASSABLE) && bypassPermission != null && player.hasPermission(bypassPermission)) return null;
        if (properties.contains(CommandMappingProperties.WORLD) && !(blockedWorlds.contains(player.getWorld().getName()))) return null;
        if (properties.contains(CommandMappingProperties.SEND_MESSAGE))
            if (properties.contains(CommandMappingProperties.CUSTOM_MESSAGE)) PlayerUtils.sendMessageList(player, customMessage);
            else player.sendMessage(CommandMappingStaticVariables.getDefaultMessage());

        return getToCommand();
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

    public ArrayList<String> getEnabledWorlds()
    {
        return blockedWorlds;
    }

    public void setEnabledWorlds(ArrayList<String> blockedWorlds)
    {
        this.blockedWorlds = blockedWorlds;
    }

    public ArrayList<CommandMappingProperties> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<CommandMappingProperties> properties)
    {
        this.properties = properties;
    }

    public String getFromCommand() {
        return fromCommand;
    }

    public void setFromCommand(String fromCommand) {
        this.fromCommand = fromCommand;
    }

    public ArrayList<Command> getToCommand() {
        return toCommand;
    }

    public void setToCommand(ArrayList<Command> toCommand) {
        this.toCommand = toCommand;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
