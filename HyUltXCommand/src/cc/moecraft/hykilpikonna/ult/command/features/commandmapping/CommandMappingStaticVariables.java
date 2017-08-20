package cc.moecraft.hykilpikonna.ult.command.features.commandmapping;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class CommandMappingStaticVariables
{
    private static String defaultMessage = "";

    private static ArrayList<CommandMapping> commandMappings;
    private static ArrayList<String> commandMappingNames;
    
    public static String getDefaultMessage()
    {
        return defaultMessage;
    }

    public static void setDefaultMessage(String defaultMessage)
    {
        CommandMappingStaticVariables.defaultMessage = defaultMessage;
    }

    public static ArrayList<CommandMapping> getCommandMappings() {
        return commandMappings;
    }

    public static void setCommandMappings(ArrayList<CommandMapping> commandMappings) {
        CommandMappingStaticVariables.commandMappings = commandMappings;
    }

    public static ArrayList<String> getCommandMappingNames() {
        return commandMappingNames;
    }

    public static void setCommandMappingNames(ArrayList<String> commandMappingNames) {
        CommandMappingStaticVariables.commandMappingNames = commandMappingNames;
    }
}
