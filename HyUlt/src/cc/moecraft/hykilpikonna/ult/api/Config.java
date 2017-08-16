package cc.moecraft.hykilpikonna.ult.api;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.ingame.Command;
import cc.moecraft.hykilpikonna.ult.api.ingame.CommandType;
import cc.moecraft.hykilpikonna.ult.utils.UrlUpdater;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 此类由 Hykilpikonna 在 2017/07/25 创建!
 * Created by Hykilpikonna on 2017/07/25!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public abstract class Config extends YamlConfiguration
{
    private File configFile;
    
    private JavaPlugin plugin;

    private String dir;
    private String fileName;
    private String fileExtension;

    private boolean autoBackup;
    private boolean autoOverwriteVersionInfo;

    private static ArrayList<Config> configs = new ArrayList<>();

    /**
     * 新建一个Config
     *
     * @param plugin 插件
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @param autoBackup 是否启用保存前自动备份
     * @param autoBackupWhenPluginUpdate 是否在配置更新的时候自动备份
     * @param autoOverwriteVersionInfo 是否自动覆盖版本信息
     */
    public Config(JavaPlugin plugin, String dir, String fileName, String fileExtension, boolean autoBackup, boolean autoBackupWhenPluginUpdate, boolean autoOverwriteVersionInfo)
    {
        super();

        this.plugin = plugin;

        this.dir = dir;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.autoBackup = autoBackup;
        this.autoOverwriteVersionInfo = autoOverwriteVersionInfo;

        this.configFile = getFileFromDir(dir, fileName, fileExtension);

        load();

        options().copyDefaults(true);

        if (!isLatest() && autoBackupWhenPluginUpdate) backup();

        checkConfig();

        configs.add(this);
    }

    /**
     * 新建一个Config
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     */
    public Config(JavaPlugin plugin, String dir, String fileName, String fileExtension)
    {
        this(plugin, dir, fileName, fileExtension, Main.getMain().getConfig().getBoolean("AutoBackupConfig.Enable"), Main.getMain().getConfig().getBoolean("AutoBackupConfig.WhenPluginUpdate"), true);
    }

    /**
     * 新建一个Config
     *
     * @param dir 路径
     * @param fileName 文件名
     */
    public Config(JavaPlugin plugin, String dir, String fileName)
    {
        this(plugin, dir, fileName, "yml", Main.getMain().getConfig().getBoolean("AutoBackupConfig.Enable"), Main.getMain().getConfig().getBoolean("AutoBackupConfig.WhenPluginUpdate"), true);
    }

    /**
     * 新建一个Config
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     */
    public Config(JavaPlugin plugin, String dir, String fileName, String fileExtension, boolean autoOverwriteVersionInfo)
    {
        this(plugin, dir, fileName, fileExtension, Main.getMain().getConfig().getBoolean("AutoBackupConfig.Enable"), Main.getMain().getConfig().getBoolean("AutoBackupConfig.WhenPluginUpdate"), autoOverwriteVersionInfo);
    }

    /**
     * 检测配置是否是最新
     *
     * 如果未生成, 执行writeDefaultConfig()方法
     * 如果生成过但不是最新, 执行writeConfig()方法
     * 然后保存, 然后重载, 然后执行readConfig()方法
     */
    public void checkConfig()
    {
        boolean save = false;
        if (isDefaultConfig())
        {
            writeDefaultConfig();
            save = true;
        }
        if (!isLatest())
        {
            writeConfig();
            save = true;
        }
        if (autoOverwriteVersionInfo)
        {
            overwriteVersionInfo();
            save = true;
        }
        if (save)
        {
            save();
        }
        if (save) reload();
        else readConfig();
    }

    /**
     * 在检测配置后 读取配置
     */
    public abstract void readConfig();

    /**
     * 在检测到配置生成过但不是最新时 写配置
     */
    public abstract void writeConfig();

    /**
     * 在检测到配置未生成时 写默认配置
     */
    public abstract void writeDefaultConfig();

    /**
     * 重载配置
     * 重载后会执行readConfig();
     */
    public void reload()
    {
        load();
        readConfig();
    }

    /**
     * 保存, 如果自动备份的话就备份
     *
     * @return 是否保存成功
     */
    public boolean save()
    {
        try
        {
            if (autoBackup) backup();
            save(configFile);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从配置文件默认备份路径备份
     *
     * @return 是否备份成功
     */
    public boolean backup()
    {
        return backup(dir, fileName, fileExtension);
    }

    /**
     * 备份
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @return 是否备份成功
     */
    public boolean backup(String dir, String fileName, String fileExtension)
    {
        try
        {
            save(getBackupFileFromDir(dir, fileName, fileExtension));
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从一个路径获取配置文件
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @return 配置文件
     */
    public File getFileFromDir(String dir, String fileName, String fileExtension)
    {
        dir = dir == null || dir.equals("") ? "" : "/" + dir;
        return new File(plugin.getDataFolder() + dir + "/" + fileName + "." + fileExtension);
    }

    /**
     * 从路径获取备份文件
     *
     * @param dir 路径
     * @param fileName 文件名
     * @param fileExtension 文件后缀
     * @return 备份文件(格式为 backup 路径 文件名at当前系统时间.文件后缀
     */
    public File getBackupFileFromDir(String dir, String fileName, String fileExtension)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        return new File(plugin.getDataFolder() + "/Backup/" + dir + "/" + fileName + "@" + dtf.format(now) + "." + fileExtension);
    }

    /**
     * 判断一个配置文件是否是新的
     * @return 是否是新的
     */
    public boolean isDefaultConfig()
    {
        return getBoolean("DefaultConfig") || !(contains("DefaultConfig"));
    }

    /**
     * 判断一个配置文件是不是最新的
     * @return 是不是最新的
     */
    public boolean isLatest()
    {
        return getString("ConfigVersion") != null && getString("ConfigVersion").equals(plugin.getDescription().getVersion());
    }

    public boolean isDefaultOrNotLatest()
    {
        return !isLatest() || isDefaultConfig();
    }

    /**
     * 覆盖版本信息
     */
    public void overwriteVersionInfo()
    {
        //把默认设置设为否
        set("DefaultConfig", false);

        //配置版本
        set("ConfigVersion", plugin.getDescription().getVersion());
    }

    /**
     * 重载所有配置文件
     */
    public static void reloadAllConfig()
    {
        for (Config config : configs)
        {
            config.reload();
        }
    }

    /**
     * 安全的添加一个默认值
     * //@param path 路径
     * //@param object 对象
     *
    @Override
    public void addDefault(String path, Object object)
    {
        if (!contains(path)) super.addDefault(path, object);
    }*/

    public void load()
    {
        try
        {
            super.load(configFile);
        }
        catch (FileNotFoundException e)
        {
            UrlUpdater.createFile(configFile);
            load();
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public Command getCommand(String path)
    {
        CommandType commandType = CommandType.valueOf(getString(path + ".CommandType"));
        String command = getString(path + ".Command");
        Boolean op = commandType == CommandType.PLAYER && getBoolean(path + "RunAsOp");

        return new Command(commandType, command, op);
    }

    public ArrayList<String> getKeys(String path)
    {
        ArrayList<String> output = new ArrayList<>();
        output.addAll(getConfigurationSection(path).getKeys(false));
        return output;
    }

    public String getDir() {
        return dir;
    }

    public boolean isAutoBackup() {
        return autoBackup;
    }

    public File getConfigFile() {
        return configFile;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setAutoBackup(boolean autoBackup) {
        this.autoBackup = autoBackup;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isAutoOverwriteVersionInfo() {
        return autoOverwriteVersionInfo;
    }

    public void setAutoOverwriteVersionInfo(boolean autoOverwriteVersionInfo) {
        this.autoOverwriteVersionInfo = autoOverwriteVersionInfo;
    }
}
