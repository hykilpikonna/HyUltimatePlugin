package cc.moecraft.hykilpikonna.ult;

import org.bukkit.Bukkit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;

/**
 * 此类由 Hykilpikonna 在 2017/08/06 创建!
 * Created by Hykilpikonna on 2017/08/06!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class HyPluginsDownloadLink
{
    private String name;
    private URL jarURL;

    private static ArrayList<HyPluginsDownloadLink> list = new ArrayList<>();
    private static ArrayList<String> nameList = new ArrayList<>();
    private static ArrayList<URL> urlList = new ArrayList<>();

    HyPluginsDownloadLink(String name, String jarURL)
    {
        this.name = name;
        try {
            this.jarURL = new URL(jarURL);
        } catch (MalformedURLException e) { e.printStackTrace(); }

        list.add(this);
        nameList.add(this.name);
        urlList.add(this.jarURL);
    }

    public URL getJarURL() {
        return jarURL;
    }

    public void setJarURL(URL jarURL) {
        this.jarURL = jarURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<HyPluginsDownloadLink> getList() {
        return list;
    }

    public static void setList(ArrayList<HyPluginsDownloadLink> list) {
        HyPluginsDownloadLink.list = list;
    }

    public static ArrayList<String> getNameList() {
        return nameList;
    }

    public static void setNameList(ArrayList<String> nameList) {
        HyPluginsDownloadLink.nameList = nameList;
    }

    public static ArrayList<URL> getUrlList() {
        return urlList;
    }

    public static void setUrlList(ArrayList<URL> urlList) {
        HyPluginsDownloadLink.urlList = urlList;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HyPluginsDownloadLink &&
                ((HyPluginsDownloadLink) obj).getJarURL().equals(jarURL) &&
                ((HyPluginsDownloadLink) obj).getName().equals(name);
    }

    public static HyPluginsDownloadLink getPluginWithName(String name)
    {
        for (HyPluginsDownloadLink plugin : list)
            if (plugin.getName().equalsIgnoreCase(name))
                return plugin;
        return null;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", name, jarURL);
    }

    public static ArrayList<String> getNotInstalledPluginsNameList()
    {
        ArrayList<String> out = new ArrayList<>();
        for (String string : nameList)
        {
            if (Bukkit.getPluginManager().getPlugin(string) == null) out.add(string);
        }
        return out;
    }

    public static ArrayList<String> getInstalledPluginsNameList()
    {
        ArrayList<String> out = new ArrayList<>();
        for (String string : nameList)
        {
            if (Bukkit.getPluginManager().getPlugin(string) != null) out.add(string);
        }
        return out;
    }
}
