package cc.moecraft.hykilpikonna.ult.api;

import cc.moecraft.hykilpikonna.ult.api.tabcompleter.TabCompletes;
import cc.moecraft.hykilpikonna.ult.utils.ArrayUtils;
import cc.moecraft.hykilpikonna.ult.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类由 Hykilpikonna 在 2017/07/28 创建!
 * Created by Hykilpikonna on 2017/07/28!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class CommandRunner implements TabCompleter, CommandExecutor
{
    private static ArrayList<CommandRunner> commandRunners = new ArrayList<>();

    private String name;
    private JavaPlugin plugin;

    private boolean enableTabComplete;
    private TabCompletes tabCompletes;

    /**
     * 新建一个不带自动补全的指令对象
     *
     * @param plugin 插件
     * @param name 指令
     */
    public CommandRunner(JavaPlugin plugin, String name)
    {
        this.name = name;
        this.plugin = plugin;

        this.enableTabComplete = false;

        getPlugin().getCommand(getName()).setExecutor(this);
    }

    /**
     * 新建一个指令对象
     *
     * @param plugin 插件
     * @param name 指令
     * @param tabCompletes 自动补全
     */
    public CommandRunner(JavaPlugin plugin, String name, TabCompletes tabCompletes)
    {
        this.name = name;
        this.plugin = plugin;
        this.tabCompletes = tabCompletes;

        this.enableTabComplete = true;

        commandRunners.add(this);

        getPlugin().getCommand(getName()).setExecutor(this);
        getPlugin().getCommand(getName()).setTabCompleter(this);
    }

    /**
     * 注册所有指令 (按它们设置好的plugin)
     */
    public static void registerAllCommands()
    {
        for (CommandRunner commandRunner : getCommandRunners())
        {
            commandRunner.getPlugin().getCommand(commandRunner.getName()).setExecutor(commandRunner);
            if (commandRunner.isEnableTabComplete()) commandRunner.getPlugin().getCommand(commandRunner.getName()).setTabCompleter(commandRunner);
        }
    }

    /**
     * Executes the given commandRunner, returning its success
     *
     * @param sender Source of the commandRunner
     * @param command CommandRunner which was executed
     * @param label Alias of the commandRunner which was used
     * @param args Passed commandRunner arguments
     * @return true if a valid commandRunner, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        runCommand(sender, command, label, StringUtils.removeForceColorCode(ArrayUtils.stringArrayToArrayList(args)));
        return true;
    }

    /**
     * Executes the given commandRunner, returning its success
     *
     * @param sender Source of the commandRunner
     * @param command CommandRunner which was executed
     * @param alias Alias of the commandRunner which was used
     * @param args Passed commandRunner arguments
     */
    public abstract void runCommand(CommandSender sender, Command command, String alias, ArrayList<String> args);

    /**
     * 自动解析的TabCompleter
     * @param sender 发送者
     * @param commandRunner 指令
     * @param alias 指令Alias
     * @param args 指令Args
     * @return Tab补全内容
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command commandRunner, String alias, String[] args)
    {
        return getTabCompletes().get(args);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JavaPlugin getPlugin()
    {
        return plugin;
    }

    public static ArrayList<CommandRunner> getCommandRunners() {
        return commandRunners;
    }

    public TabCompletes getTabCompletes() {
        return tabCompletes;
    }

    public void setTabCompletes(TabCompletes tabCompletes) {
        this.tabCompletes = tabCompletes;
    }

    public boolean isEnableTabComplete() {
        return enableTabComplete;
    }

    public void setEnableTabComplete(boolean enableTabComplete) {
        this.enableTabComplete = enableTabComplete;
    }
}
