package cc.moecraft.hykilpikonna.ult.api;

import cc.moecraft.hykilpikonna.ult.utils.PlaceholderUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/07/29 创建!
 * Created by Hykilpikonna on 2017/07/29!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class PermissionsConfig extends Config
{
    public PermissionsConfig(JavaPlugin plugin, String fileName)
    {
        super(plugin, "Permissions", fileName, "yml", false, true, true);
    }

    @Override
    public String get(String path)
    {
        return getString(replaceDot(path));
    }

    public String getNode(String path)
    {
        return getString(replaceDot(path) + ".node");
    }

    public boolean getRequired(String path)
    {
        return getBoolean(replaceDot(path) + ".required", true);
    }

    public void add(String node, boolean required)
    {
        addDefault(replaceDot(node) + ".node", node);
        addDefault(replaceDot(node) + ".required", required);
    }

    public String replaceDot(String string)
    {
        return string.replace(".", "_");
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
        if (isDefaultConfig()) writeDefaultConfig();
        if (!isLatest())
        {
            writeMessage();
            writeConfig();
        }
        if (isAutoOverwriteVersionInfo()) overwriteVersionInfo();
        save(); reload(); readConfig();
    }

    public void writeMessage()
    {
        addDefault("Message", RED + String.format("您没有权限! 缺少的权限: %s", "%PERM%"));
    }

    public String getMessage(String node)
    {
        return get("Message").replace("%PERM%", node);
    }

    /**
     * 检测一个玩家是否拥有一个指定的权限, 或者这个指定的权限未启用
     * @param player 玩家
     * @param path 配置中的权限点
     * @return 是否拥有权限
     */
    public boolean hasPermission(CommandSender player, String path, boolean message)
    {
        if (getRequired(path) && player instanceof Player && !player.hasPermission(getNode(path)))
        {
            if (message) player.sendMessage(getMessage(path));
            return false;
        }
        return true;
    }

    /**
     * 检测一个玩家是否拥有一个指定的权限, 或者这个指定的权限未启用
     * @param player 玩家
     * @param path 配置中的权限点
     * @return 是否拥有权限
     */
    public boolean hasPermissionNotInConfig(CommandSender player, String path, boolean message)
    {
        if (player instanceof Player && !player.hasPermission(path))
        {
            if (message) player.sendMessage(getMessage(path));
            return false;
        }
        return true;
    }

    /**
     * 给一个玩家发送无权限消息
     *
     * @param sender 玩家
     * @param path 权限节点
     */
    public void sendNoPermissionMessage(Player sender, String path)
    {
        sender.sendMessage(getMessage(path));
    }

    @Override
    public void readConfig() {}
    @Override
    public void writeDefaultConfig() {}
}
