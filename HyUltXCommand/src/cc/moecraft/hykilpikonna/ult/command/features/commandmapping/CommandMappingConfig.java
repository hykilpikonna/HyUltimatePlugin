package cc.moecraft.hykilpikonna.ult.command.features.commandmapping;

import cc.moecraft.hykilpikonna.ult.api.Config;
import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import cc.moecraft.hykilpikonna.ult.api.ingame.CommandType;
import cc.moecraft.hykilpikonna.ult.command.HyUltXCommand;
import cc.moecraft.hykilpikonna.ult.command.features.Features;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandMappingConfig extends Config
{
    public CommandMappingConfig()
    {
        super(HyUltXCommand.getInstance(), "Features", Features.getCommandMapping().getFriendlyName());
    }

    @Override
    public void readConfig()
    {
        CommandMappingStaticVariables.setDefaultMessage(getString("DefaultMessage"));
        getKeys("CommandMappings").forEach(key ->
        {
            CommandMapping mappedCommand = new CommandMapping(
                    getString(String.format("CommandMappings.%s.FromCommand", key)),
                    Command.getCommandList(this, String.format("CommandMappings.%s.ToCommands", key)),
                    getCommandMappingsPropertiesList(String.format("CommandMappings.%s.Properties", key))
            );
            if (mappedCommand.getProperties().contains(CommandMappingProperties.BYPASSABLE)) mappedCommand.setBypassPermission(getString(String.format("CommandMappings.%s.BypassPermission", key)));
            if (mappedCommand.getProperties().contains(CommandMappingProperties.CUSTOM_MESSAGE)) mappedCommand.setCustomMessage((ArrayList<String>) getStringList(String.format("CommandMappings.%s.Message", key)));
            if (mappedCommand.getProperties().contains(CommandMappingProperties.WORLD)) mappedCommand.setEnabledWorlds((ArrayList<String>) getStringList(String.format("CommandMappings.%s.EnabledWorlds", key)));
            CommandMappingStaticVariables.getCommandMappings().add(mappedCommand);
        });
    }

    @Override
    public void writeConfig()
    {

    }

    @Override
    public void writeDefaultConfig()
    {
        addDefault("DefaultMessage", ChatColor.GREEN + "您的指令已被转发!");

        {
            addDefault("CommandMappings.Command1.FromCommand", "/demo_mapping_command_with_message1");
            ArrayList<Map<String, Object>> tempALForCommand1 = new ArrayList<>();
            tempALForCommand1.add(new Command(CommandType.PLAYER, "/demo_mapped_command").serialize());
            tempALForCommand1.add(new Command(CommandType.PLAYER, "/demo_mapped_command2").serialize());
            tempALForCommand1.add(new Command(CommandType.CONSOLE, "/demo_mapped_console_command").serialize());
            tempALForCommand1.add(new Command(CommandType.CONSOLE, "/say 测试转发指令").serialize());
            addDefault("CommandMappings.Command1.ToCommands", tempALForCommand1);
            addDefault("CommandMappings.Command1.Properties", new String[] {CommandMappingProperties.SEND_MESSAGE.name(), CommandMappingProperties.CUSTOM_MESSAGE.name()});
            addDefault("CommandMappings.Command1.Message", new String[] {"我是一条可以转发到多条的测试指令"});
        }

        {
            addDefault("CommandMappings.aBypassableCommand.FromCommand", "/demo_mapping_command_bypassable");
            ArrayList<Map<String, Object>> tempALForCommand1 = new ArrayList<>();
            tempALForCommand1.add(new Command(CommandType.PLAYER, "/say 测试可绕过的转发指令").serialize());
            addDefault("CommandMappings.aBypassableCommand.ToCommands", tempALForCommand1);
            addDefault("CommandMappings.aBypassableCommand.Properties", new String[] {CommandMappingProperties.SEND_MESSAGE.name(), CommandMappingProperties.CUSTOM_MESSAGE.name(), CommandMappingProperties.BYPASSABLE.name()});
            addDefault("CommandMappings.aBypassableCommand.BypassPermission", "demo.bypass.permission");
            addDefault("CommandMappings.aBypassableCommand.Message", new String[] {"我是一条可以绕过的转发指令"});
        }

        {
            addDefault("CommandMappings.aWorldCommand.FromCommand", "/demo_command_world");
            ArrayList<Map<String, Object>> tempALForCommand1 = new ArrayList<>();
            tempALForCommand1.add(new Command(CommandType.PLAYER, "/say 测试世界指令").serialize());
            addDefault("CommandMappings.aWorldCommand.ToCommands", tempALForCommand1);
            addDefault("CommandMappings.aWorldCommand.Properties", new String[] {CommandMappingProperties.SEND_MESSAGE.name(), CommandMappingProperties.CUSTOM_MESSAGE.name(), CommandMappingProperties.WORLD.name()});
            addDefault("CommandMappings.aWorldCommand.Message",  new String[] {"我是一条只有在指定世界拦截的指令"});
            addDefault("CommandMappings.aWorldCommand.EnabledWorlds",  new String[] {Bukkit.getWorlds().get(0).getName()});
        }
    }

    public ArrayList<CommandMappingProperties> getCommandMappingsPropertiesList(String path)
    {
        ArrayList<CommandMappingProperties> output = new ArrayList<>();
        getStringList(path).forEach(one -> {output.add(CommandMappingProperties.valueOf(one));});
        return output;
    }
}
