package cc.moecraft.hykilpikonna.ult.api.ingame;

import cc.moecraft.hykilpikonna.ult.api.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

import static cc.moecraft.hykilpikonna.ult.utils.PlaceholderUtils.replacePlayerPlaceholder;

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
        this.command = removeSlash(command);
        this.op = op;
    }

    public void sendCommand(Player player)
    {
        String command = replacePlayerPlaceholder(player, this.command);
        if (commandType == CommandType.PLAYER)
            if (op)
                if (player.isOp()) player.chat("/" + command);
                else sendOpCommand(player, "/" + command);
            else player.chat("/" + command);
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

    public static ArrayList<Command> getCommandList(Config config, String path)
    {
        ArrayList<Command> output = new ArrayList<>();
        config.getMapList(path).forEach(map -> output.add(Command.deserializeWithUnknownMap(map)));
        return output;
    }

    @Override
    public String toString()
    {
        return String.format("{TYPE = %s, COMMAND = %s, OP = %s}", commandType.name(), command, op.toString());
    }

    public String removeSlash(String original)
    {
        StringBuilder output = new StringBuilder();
        output.append(original.charAt(0) == '/' ? "" : original.charAt(0));
        for (int i = 1; i < original.length(); i++) output.append(original.charAt(i));
        return output.toString();
    }
}
