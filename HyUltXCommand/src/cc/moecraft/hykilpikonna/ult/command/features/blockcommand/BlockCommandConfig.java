package cc.moecraft.hykilpikonna.ult.command.features.blockcommand;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class BlockCommandConfig extends Config
{
    public BlockCommandConfig()
    {
        super(HyUltXCommand.getInstance(), "Features", Features.getBlockCommand().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        BlockCommandStaticVariables.setBlockedCommandNames(new ArrayList<>());
        BlockCommandStaticVariables.setBlockedCommands(new ArrayList<>());

        BlockCommandStaticVariables.setDefaultMessage(getString("DefaultMessage"));
        getKeys("BlockedCommands").forEach(key -> {
            BlockedCommand blockedCommand = new BlockedCommand(getString(String.format("BlockedCommands.%s.Command", key)), getBlockedCommandPropertiesList(String.format("BlockedCommands.%s.Properties", key)));
            if (blockedCommand.getProperties().contains(BlockedCommandProperties.BYPASSABLE)) blockedCommand.setBypassPermission(getString(String.format("BlockedCommands.%s.BypassPermission", key)));
            if (blockedCommand.getProperties().contains(BlockedCommandProperties.CUSTOM_MESSAGE)) blockedCommand.setCustomMessage((ArrayList<String>) getStringList(String.format("BlockedCommands.%s.Message", key)));
            if (blockedCommand.getProperties().contains(BlockedCommandProperties.WORLD)) blockedCommand.setBlockedWorlds((ArrayList<String>) getStringList(String.format("BlockedCommands.%s.BlockedWorlds", key)));
            BlockCommandStaticVariables.getBlockedCommands().add(blockedCommand);
        });
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("DefaultMessage", ChatColor.RED + "此指令不存在 - This Command Does Not Exist!");

        addDefault("BlockedCommands.Command1.Command", "/demo_command_with_message1");
        addDefault("BlockedCommands.Command1.Properties", new String[] {BlockedCommandProperties.SEND_MESSAGE.name()});

        addDefault("BlockedCommands.Commandadfadfaj.Command", "/demo_command_with_custom_message");
        addDefault("BlockedCommands.Commandadfadfaj.Properties", new String[] {BlockedCommandProperties.SEND_MESSAGE.name(), BlockedCommandProperties.CUSTOM_MESSAGE.name()});
        addDefault("BlockedCommands.Commandadfadfaj.Message", new String[] {"我是一条测试消息", "我是第二条测试消息"});

        addDefault("BlockedCommands.aBypassableCommand.Command", "/demo_command_bypassable");
        addDefault("BlockedCommands.aBypassableCommand.Properties", new String[] {BlockedCommandProperties.SEND_MESSAGE.name(), BlockedCommandProperties.CUSTOM_MESSAGE.name(), BlockedCommandProperties.BYPASSABLE.name()});
        addDefault("BlockedCommands.aBypassableCommand.BypassPermission", "demo.bypass.permission");
        addDefault("BlockedCommands.aBypassableCommand.Message", new String[] {"我是一条可以绕过的指令测试消息"});

        addDefault("BlockedCommands.aWorldCommand.Command", "/demo_command_world");
        addDefault("BlockedCommands.aWorldCommand.Properties", new String[] {BlockedCommandProperties.SEND_MESSAGE.name(), BlockedCommandProperties.CUSTOM_MESSAGE.name(), BlockedCommandProperties.WORLD.name()});
        addDefault("BlockedCommands.aWorldCommand.Message",  new String[] {"我是一条只有在指定世界拦截的指令"});
        addDefault("BlockedCommands.aWorldCommand.BlockedWorlds",  new String[] {Bukkit.getWorlds().get(0).getName()});
    }

    public ArrayList<BlockedCommandProperties> getBlockedCommandPropertiesList(String path)
    {
        ArrayList<BlockedCommandProperties> output = new ArrayList<>();
        getStringList(path).forEach(one -> {output.add(BlockedCommandProperties.valueOf(one));});
        return output;
    }
}
