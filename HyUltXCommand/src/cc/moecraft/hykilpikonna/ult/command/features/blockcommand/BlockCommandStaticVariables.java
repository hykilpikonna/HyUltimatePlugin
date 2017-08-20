package cc.moecraft.hykilpikonna.ult.command.features.blockcommand;

import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/08/14 创建!
 * Created by Hykilpikonna on 2017/08/14!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class BlockCommandStaticVariables
{
    private static String defaultMessage = "";

    private static ArrayList<BlockedCommand> blockedCommands;
    private static ArrayList<String> blockedCommandNames;
    
    public static String getDefaultMessage()
    {
        return defaultMessage;
    }

    public static void setDefaultMessage(String defaultMessage)
    {
        BlockCommandStaticVariables.defaultMessage = defaultMessage;
    }

    public static ArrayList<BlockedCommand> getBlockedCommands()
    {
        return blockedCommands;
    }

    public static void setBlockedCommands(ArrayList<BlockedCommand> blockedCommands)
    {
        BlockCommandStaticVariables.blockedCommands = blockedCommands;
    }

    public static ArrayList<String> getBlockedCommandNames()
    {
        return blockedCommandNames;
    }

    public static void setBlockedCommandNames(ArrayList<String> blockedCommandNames)
    {
        BlockCommandStaticVariables.blockedCommandNames = blockedCommandNames;
    }
}
