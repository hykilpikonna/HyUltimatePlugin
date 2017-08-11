package cc.moecraft.hykilpikonna.ult.api.ingame;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 此类由 Hykilpikonna 在 2017/08/11 创建!
 * Created by Hykilpikonna on 2017/08/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class Command
{
    private CommandType commandType;
    private String command;
    private Boolean op;

    public Command(CommandType type, String command)
    {
        this(type, command, false);
    }

    public Command(CommandType type, String command, Boolean op)
    {
        this.commandType = type;
        this.command = command;
        this.op = op;
    }

    public void sendCommand(Player player)
    {
        if (commandType == CommandType.PLAYER)
            if (op)
                if (player.isOp()) player.chat(command);
                else sendOpCommand(player, command);
            else player.chat(command);
        else if (commandType == CommandType.CONSOLE) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static void sendOpCommand(Player player, String command)
    {
        player.setOp(true);
        player.chat(command);
        player.setOp(false);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Boolean getOp() {
        return op;
    }

    public void setOp(Boolean op) {
        this.op = op;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public Map<String, Object> serialize()
    {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("CommandType", commandType.name());
        result.put("Command", command);
        if (commandType == CommandType.PLAYER) result.put("RunAsOp", op);

        return result;
    }

    public static Command deserialize(Map<String, Object> args)
    {
        CommandType commandType = CommandType.valueOf((String) args.get("CommandType"));
        String command = (String) args.get("Command");
        Boolean op = commandType == CommandType.PLAYER ? (Boolean) args.get("RunAsOp") : false;

        return new Command(commandType, command, op);
    }

    public static Command deserializeWithUnknownMap(Map<?, ?> args)
    {
        CommandType commandType = CommandType.valueOf((String) args.get("CommandType"));
        String command = (String) args.get("Command");
        Boolean op = commandType == CommandType.PLAYER ? (Boolean) args.get("RunAsOp") : false;

        return new Command(commandType, command, op);
    }
}
