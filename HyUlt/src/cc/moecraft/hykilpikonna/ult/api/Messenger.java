package cc.moecraft.hykilpikonna.ult.api;

import cc.moecraft.hykilpikonna.ult.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.utils.StringUtils.convertColorCode;
import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/07/07 创建!
 * Created by Hykilpikonna on 2017/07/07!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class Messenger extends Config
{
    public Messenger(JavaPlugin plugin, String featureID)
    {
        super(plugin, "Messages", featureID);
    }

    /**
     * 检测配置是否是最新
     *
     * 如果未生成, 执行writeDefaultConfig()方法
     * 如果生成过但不是最新, 执行writeConfig()方法
     * 然后保存, 然后重载, 然后执行readConfig()方法
     */
    @Override
    public void checkConfig()
    {
        boolean save = false;
        if (isDefaultOrNotLatest())
        {
            Main.tempDebug("写入信息");
            options().copyDefaults(true);
            setPrefix(prefix());
            writeConfig();
            save = true;
        }
        if (isAutoOverwriteVersionInfo())
        {
            overwriteVersionInfo();
            save = true;
        }
        if (save)
        {
            Main.tempDebug("保存信息");
            options().copyDefaults(true);
            save();
            reload();
        }
        else readConfig();
    }

    /**
     * 获取一个消息
     * 如果没有就返回MESSAGE MISSING (路径)
     *
     * @param path 路径
     * @return 消息
     */
    @Override
    public String get(String path)
    {
        return convertColorCode(getString(path) != null ? getString(path) : RED + "MESSAGE MISSING (" + path + ")");
    }

    /**
     * 获取一个带前缀的消息
     * 如果没有就返回MESSAGE MISSING (路径)
     *
     * @param path 路径
     * @return 消息
     */
    public String getWithPrefix(String path)
    {
        return convertColorCode(getPrefix() + (getString(path) != null ? getString(path) : RED + "MESSAGE MISSING (" + path + ")"));
    }

    /**
     * 获取一个带前缀的消息List
     * 每一个项都会加上前缀
     * 如果没有就返回MESSAGE MISSING (路径)
     *
     * @param path 路径
     * @return 消息
     */
    public ArrayList<String> getListWithPrefix(String path)
    {
        ArrayList<String> arrayList = getMessageList(path);
        for (int i = 0; i < arrayList.size(); i++) arrayList.set(i, convertColorCode(getPrefix() + arrayList.get(i)));
        return arrayList;
    }

    /**
     * 获取一个前缀
     * 获取路径为 Prefix
     * 如果默认也没有就返回 "[PREFIX MISSING]"
     *
     * @return 前缀
     */
    public String getPrefix()
    {
        String path = "Prefix";
        return convertColorCode(getString(path) != null ? getString(path) : RED + "[PREFIX MISSING]");
    }

    /**
     * 设置前缀
     *
     * @param prefix 前缀
     */
    public void setPrefix(String prefix)
    {
        addDefault("Prefix", convertColorCode(prefix));
    }

    /**
     * 获取是否包含一个前缀
     *
     * @return 是否包含一个前缀
     */
    public boolean containsPrefix()
    {
        return contains("Prefix");
    }

    /**
     * 获取一个消息List
     * 如果没有就返回MESSAGE LIST MISSING (路径)
     *
     * @param path 路径
     * @return List
     */
    public ArrayList<String> getMessageList(String path)
    {
        ArrayList<String> out = new ArrayList<>();
        ArrayList<String> defaultArrayList = new ArrayList<>();
        defaultArrayList.add(RED + "MESSAGE LIST MISSING (" + path + ")");
        out.addAll(getStringList(path) != null ? getStringList(path) : defaultArrayList);
        return out;
    }

    /**
     * 给玩家发送一段消息
     * @param commandSender 玩家
     * @param messageNode 消息的配置节点
     */
    public void sendMessage(CommandSender commandSender, String messageNode)
    {
        commandSender.sendMessage(getWithPrefix(messageNode));
    }

    /**
     * 给玩家发送一段消息
     * @param commandSender 玩家
     * @param messageNode 消息的配置节点
     */
    public void sendMessageList(CommandSender commandSender, String messageNode)
    {
        sendArrayListMessageToAPlayer(commandSender, getListWithPrefix(messageNode));
    }

    private void sendArrayListMessageToAPlayer(CommandSender commandSender, ArrayList<String> msg)
    {
        for (String message : msg) commandSender.sendMessage(message);
    }

    private void sendArrayListMessageToAPlayer(Player commandSender, ArrayList<String> msg)
    {
        for (String message : msg) commandSender.sendMessage(message);
    }

    @Override
    public void readConfig() {}

    @Override
    public void writeDefaultConfig() {writeConfig();}

    public abstract String prefix();
}
