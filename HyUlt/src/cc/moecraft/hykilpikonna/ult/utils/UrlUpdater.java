package cc.moecraft.hykilpikonna.ult.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import static cc.moecraft.hykilpikonna.ult.Main.getMain;
import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;
import static cc.moecraft.hykilpikonna.ult.Main.tempLog;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.load;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.reload;
import static cc.moecraft.hykilpikonna.ult.utils.PluginUtil.unload;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.RED;

/**
 * 此类由 Hykilpikonna 在 2017/07/16 创建!
 * Created by Hykilpikonna on 2017/07/16!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class UrlUpdater
{
    private Plugin currentPlugin;
    private File currentPluginFile;
    private URL latestPluginFile;
    private String currentVersion;
    private String latestVersion;

    private File tempPluginYmlPath;

    /**
     * URL插件更新器
     * @param currentPluginFile 更新前的文件路径
     * @param currentVersion 当前的插件
     * @param latestFileURL 最新的文件URL地址
     * @param latestPluginYmlURL 最新的Plugin.yml地址
     */
    public UrlUpdater(File currentPluginFile, Plugin currentVersion, URL latestFileURL, URL latestPluginYmlURL, boolean repeatUpdate)
    {
        this(currentPluginFile, currentVersion, latestFileURL, latestPluginYmlURL, repeatUpdate, getMain().getConfig().getInt("AutoUpdate.Default.CheckDelayInSeconds"));
    }

    /**
     * URL插件更新器
     * @param currentPluginFile 更新前的文件路径
     * @param currentVersion 当前的插件
     * @param latestFileURL 最新的文件URL地址
     * @param latestPluginYmlURL 最新的Plugin.yml地址
     * @param repeatUpdate 是否间隔时间检查更新
     * @param period 间隔的时间(秒)
     */
    public UrlUpdater(File currentPluginFile, Plugin currentVersion, URL latestFileURL, URL latestPluginYmlURL, boolean repeatUpdate, long period)
    {
        this.tempPluginYmlPath = new File(getMain().getDataFolder() + "/temp/" + currentVersion.getDescription().getName() + "/" + System.currentTimeMillis() + "/plugin.yml");
        createFile(tempPluginYmlPath);

        this.currentPluginFile = currentPluginFile;
        this.currentVersion = currentVersion.getDescription().getVersion();
        this.currentPlugin = currentVersion;
        this.latestPluginFile = latestFileURL;

        tempDebug("已创建自动更新对象:");
        tempDebug("  - 当前插件路径 = " + this.currentPluginFile);
        tempDebug("  - 当前插件     = " + this.currentPlugin);
        tempDebug("  - 当前版本     = " + this.currentVersion);
        tempDebug("  - 最新URL      = " + this.latestPluginFile);
        tempDebug("  - 最新PluginYML= " + latestPluginYmlURL);
        tempDebug("  - 临时文件路径 = " + tempPluginYmlPath);

        this.latestVersion = downloadPluginYML(latestPluginYmlURL);

        tempDebug("  - 最新版本     = " + this.latestVersion);
        tempDebug("  - 检查延迟     = " + period);

        if (repeatUpdate) repeatUpdate(period);
    }

    /**
     * 判断是否需要更新
     * @return 是否需要更新
     */
    private boolean checkUpdate()
    {
        return versionComparison(currentVersion, latestVersion) == 1;
    }

    /**
     * 自动更新
     */
    public void repeatUpdate(long period)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                update();
            }
        }.runTaskTimerAsynchronously(getMain(), 0L, period * 20);
    }

    /**
     * 更新
     */
    public void asyncUpdate()
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                update();
            }
        }.runTaskAsynchronously(getMain());
    }

    /**
     * 更新
     */
    public void update()
    {
        if (getMain().getConfig().getBoolean("AutoUpdate.Enable"))
        {
            if (checkUpdate())
            {
                unload(currentPlugin);
                downloadFileInOneLine(latestPluginFile, currentPluginFile);
                reload(currentPlugin);
            }
        }
    }

    /**
     * 下载HyEssentials
     */
    public static void downloadHyEssentials(URL url)
    {
        tempLog("未检测到HyEssentialsAPI前置, 本插件需要前置才能运行, 正在下载...");
        tempDebug("正在下载HyEssentials:");
        tempDebug("  - 当前URL = " + url);
        File pluginDir = new File("plugins/HyEssentials.jar");
        tempDebug("已生成文件...");
        downloadFileInOneLine(url, pluginDir);
        tempLog(GREEN + "下载完成!");
        tempDebug("下载完成, 正在加载HyEssentials...");
        load(pluginDir);
        tempDebug("加载完成!");
    }

    /**
     * 下载Plugin.yml来获取最新版本
     * @param url 下载地址
     * @return 最新版本
     */
    private String downloadPluginYML(URL url)
    {
        downloadFileInOneLine(url, tempPluginYmlPath);
        return getVersionFromPluginYML(tempPluginYmlPath);
    }

    /**
     * 下载文件
     * 归功于 @V乐乐
     * Credit to @Vlvxingze
     *
     * @param url 下载地址
     * @param path 文件路径
     * @return 完整文件路径(File)
     */
    public static File downloadFile(URL url, String path)
    {
        String filePath = "";
        if (!(path.endsWith("/")) && !(path.endsWith("\\"))) filePath = path + "/";
        else filePath = path;

        tempDebug("正在下载文件, ");
        tempDebug("  - URL = " + url);
        tempDebug("  - Path = " + path);

        try
        {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(getMain().getConfig().getInt("AutoUpdate.Default.TimeoutInSeconds") * 1000);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //可能出错
            File file = new File(path, getFileName(httpURLConnection));
            tempDebug("  - File = " + file);

            //更可能出错
            if (file.exists()) file.delete();

            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] getData = readInputStream(inputStream);

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(getData);
            fileOutputStream.close();

            if (inputStream != null)
            {
                inputStream.close();
            }

            tempDebug(GREEN + "下载成功");
            return file;
        }
        catch (IOException ignored)
        {
            tempDebug(RED + "下载失败");
            ignored.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件名
     *
     * @param httpURLConnection HTTP连接
     * @return 文件名
     */
    public static String getFileName(HttpURLConnection httpURLConnection)
    {
        String raw = httpURLConnection.getHeaderField("Content-Disposition");
        String fileName;
        if(raw != null && raw.contains("filename="))
        {
            fileName = raw.split("filename=")[1];
        }
        else
        {
            fileName = "FILE-" + UUID.randomUUID().toString();
        }
        return fileName;
    }

    /**
     * 一行代码下载文件
     * 归功于 @喵呜
     * Credit to @ admin@yumc.pw
     *
     * @param url 下载地址
     * @param file 覆盖文件
     * @return 是否下载成功
     */
    public static boolean downloadFileInOneLine(URL url, File file)
    {
        tempDebug("正在下载文件, ");
        tempDebug("  - URL = " + url);
        tempDebug("  - File = " + file);
        try
        {
            Files.copy(url.openConnection().getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            tempDebug(GREEN + "下载成功");
            return true;
        }
        catch (IOException ignored)
        {
            tempDebug(RED + "下载失败:");
            ignored.printStackTrace();
            return false;
        }
    }

    /**
     * 缓存下载文件?
     * 没学过IO, 看不懂_(:з」∠)_
     * 归功于 @V乐乐
     * Credit to @Vlvxingze
     * @param inputStream IDK! Ask @Vlvxingze
     * @return IDK! Ask @Vlvxingze
     * @throws IOException IDK! Ask @Vlvxingze
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException
    {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 获取Plugin.yml中的版本号
     * @param pluginYML Plugin.yml文件
     * @return 版本号
     */
    public static String getVersionFromPluginYML(File pluginYML)
    {
        return YamlConfiguration.loadConfiguration(pluginYML).getString("version");
    }

    /**
     * 判断当前版本和最新版本的关系
     *
     * 如果当前版本小于最新版本, 返回1,
     * 如果当前版本大于最新版本, 返回-1,
     * 如果当前版本等于最新版本, 返回0
     *
     * 注意: 所有非数字字符都会被忽略
     *
     * 例子: 0.1.6.9 和 0.1.6.9 返回0
     * 例子: 0.1.6.9 和 0.1.7.0 返回1
     * 例子: 0.1.69  和 0.17.0  返回1
     * 例子: 0.16.9  和 0.1.70  返回-1
     *
     * @param currentVersion 当前版本
     * @param latestVersion 最新版本
     * @return 对比值
     */
    public static int versionComparison(String currentVersion, String latestVersion)
    {
        tempDebug("开始版本比较...");
        tempDebug(String.format("输入 = %s, %s", currentVersion, latestVersion));
        String[] currentVersionAfterSplit = removeInNumeric(currentVersion).split("\\.");
        String[] latestVersionAfterSplit = removeInNumeric(latestVersion).split("\\.");

        int currentLength = currentVersionAfterSplit.length;
        int latestLength = latestVersionAfterSplit.length;

        for (int i = 0; i < Math.max(currentLength, latestLength); i++)
        {
            int currentVersionAtI = i < currentLength ? Integer.parseInt(currentVersionAfterSplit[i]) : 0;
            int latestVersionAtI = i < latestLength ? Integer.parseInt(latestVersionAfterSplit[i]) : 0;

            tempDebug(String.format("当前循环 %s 次, 比较两个数: %s, %s", i, currentVersionAtI, latestVersionAtI));

            if (currentVersionAtI < latestVersionAtI)
            {
                tempDebug("输出 = 1");
                return 1;
            }
            if (currentVersionAtI > latestVersionAtI)
            {
                tempDebug("输出 = -1");
                return -1;
            }
        }
        tempDebug("输出 = 0");

        return 0;
    }

    public static String removeInNumeric(String string)
    {
        if (string == null || string.equals("")) return "";

        StringBuilder output = new StringBuilder();
        for (Character aChar : string.toCharArray())
        {
            if (Character.isDigit(aChar)) output.append(aChar);
            if (aChar == '.') output.append('.');
        }
        tempDebug("去除非数字/小数点字符结果 = " + output.toString());
        return output.toString();
    }

    /**
     * 创建文件
     * @param file 文件
     * @return 是否成功
     */
    public static boolean createFile(File file) 
    {
        if(file.exists()) 
        {
            tempDebug(RED + "创建文件" + file + "失败");
            return false;
        }
        if (file.toString().endsWith(File.separator)) 
        {
            tempDebug(RED + "创建文件" + file + "失败, 目标文件不能为目录");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) 
        {
            //如果目标文件所在的目录不存在，则创建父目录
            tempDebug("目标文件所在目录不存在, 正在创建...");
            if(!file.getParentFile().mkdirs()) 
            {
                tempDebug(RED + "创建目标文件所在目录失败");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                tempDebug(GREEN + "创建文件" + file + "成功");
                return true;
            } else {
                tempDebug(RED + "创建文件" + file + "失败");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            tempDebug(RED + "创建文件" + file + "失败");
            return false;
        }
    }

    /**
     * 创建目录
     * @param destDirName 目录
     * @return 是否成功
     */
    public static boolean createDir(String destDirName)
    {
        File dir = new File(destDirName);
        if (dir.exists())
        {
            tempDebug(RED + "创建目录" + destDirName + "失败, 目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator))
        {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs())
        {
            tempDebug(GREEN + "创建目录" + destDirName + "成功!");
            return true;
        }
        else
        {
            tempDebug(RED + "创建目录" + destDirName + "失败!");
            return false;
        }
    }


    public static String createTempFile(String prefix, String suffix, String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try{
                //在默认文件夹下创建临时文件
                tempFile = File.createTempFile(prefix, suffix);
                //返回临时文件的路径
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                tempDebug("创建临时文件失败！" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            //如果临时文件所在目录不存在，首先创建
            if (!dir.exists()) {
                if (!createDir(dirName)) {
                    tempDebug("创建临时文件失败，不能创建临时文件所在的目录！");
                    return null;
                }
            }
            try {
                //在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                e.printStackTrace();
                tempDebug("创建临时文件失败！" + e.getMessage());
                return null;
            }
        }
    }
}
