package cc.moecraft.hykilpikonna.ult.utils;

/*
 * #%L
 * PlugMan
 * %%
 * Copyright (C) 2010 - 2014 PlugMan
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import cc.moecraft.hykilpikonna.ult.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Event;
import org.bukkit.plugin.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cc.moecraft.hykilpikonna.ult.Main.getMain;
import static cc.moecraft.hykilpikonna.ult.Main.tempDebug;

/**
 * Utilities for managing plugins.
 *
 * @author rylinaux
 */
public class PluginUtil {
    public static Plugin getPluginByName(String name) {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (name.equalsIgnoreCase(plugin.getName()))
                return plugin;
        }
        return null;
    }

    public static List<String> getPluginNames(boolean fullName) {
        List<String> plugins = new ArrayList<>();
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins())
            plugins.add(fullName ? plugin.getDescription().getFullName() : plugin.getName());
        return plugins;
    }

    public static boolean loadFile(File pluginFile)
    {
        Plugin target = null;
        String name = pluginFile.getName();
        try
        {
            try
            {
                target = Bukkit.getPluginManager().loadPlugin(pluginFile);
            }
            catch (Exception e) { e.printStackTrace(); return false; }
            if (target == null) return false;
            target.onLoad();
            Bukkit.getPluginManager().enablePlugin(target);
            return true;
        }
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean loadFileName(String name)
    {
        String filename = name;
        if (!name.endsWith(".jar")) {
            filename = name + ".jar";
        }
        File pluginDir = new File("plugins");
        File updateDir = new File(pluginDir, "update");
        if (!pluginDir.isDirectory()) return false;
        File pluginFile = new File(pluginDir, filename);
        if ((!pluginFile.isFile()) && (!new File(updateDir, filename).isFile()))
        {
            pluginFile = null;
            for (File file : pluginDir.listFiles())
            {
                if (file.getName().endsWith(".jar"))
                {
                    try
                    {
                        PluginDescriptionFile desc = Main.getMain().getPluginLoader().getPluginDescription(file);
                        if (desc.getName().equalsIgnoreCase(name))
                        {
                            pluginFile = file;
                            break;
                        }
                    }
                    catch (InvalidDescriptionException ignored) {}
                }
            }
            if (pluginFile == null) return false;
        }
        return loadFile(pluginFile);
    }
    
    public static boolean load(String name) {
        Plugin target = null;
        File pluginFile = findPluginFile(name);

        try {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException | InvalidPluginException e)
        {
            return false;
        }

        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);

        return true;
    }

    public static boolean load(File pluginFile)
    {
        Plugin target;

        try
        {
            target = Bukkit.getPluginManager().loadPlugin(pluginFile);
        }
        catch (InvalidDescriptionException | InvalidPluginException e)
        {
            e.printStackTrace();
            return false;
        }

        target.onLoad();
        Bukkit.getPluginManager().enablePlugin(target);

        return true;
    }

    public static File findPluginFile(String name)
    {
        File pluginDir = new File("plugins");

        File pluginFile = new File(pluginDir, name + ".jar");

        if (!pluginFile.isFile())
        {
            for (File f : pluginDir.listFiles())
            {
                if (f.getName().endsWith(".jar"))
                {
                    try
                    {
                        PluginDescriptionFile desc = getMain().getPluginLoader().getPluginDescription(f);
                        if (desc.getName().equalsIgnoreCase(name))
                        {
                            pluginFile = f;
                            break;
                        }
                    }
                    catch (InvalidDescriptionException ignored) {}
                }
            }
        }

        return pluginFile;
    }

    public static void reload(Plugin plugin) {
        if (plugin != null) {
            unload(plugin);
            load(plugin.getName());
        }
    }

    public static boolean delete(Plugin plugin)
    {
        return (unload(plugin)) && (getPluginFile(plugin).delete());
    }

    public static File getPluginFile(Plugin plugin)
    {
        File file = null;
        ClassLoader cl = plugin.getClass().getClassLoader();
        if ((cl instanceof URLClassLoader))
        {
            URLClassLoader ucl = (URLClassLoader)cl;
            URL url = ucl.getURLs()[0];
            try
            {
                file = new File(URLDecoder.decode(url.getFile(), "UTF-8"));
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
        }
        return file;
    }

    public static boolean unload(Plugin plugin) {
        String name = plugin.getName();
        PluginManager pluginManager = Bukkit.getPluginManager();
        SimpleCommandMap commandMap = null;
        List<Plugin> plugins = null;
        Map<String, Plugin> names = null;
        Map<String, Command> commands = null;
        Map<Event, SortedSet<RegisteredListener>> listeners = null;

        boolean reloadlisteners = true;

        if (pluginManager != null) {

            pluginManager.disablePlugin(plugin);

            try {

                Field pluginsField = Bukkit.getPluginManager().getClass().getDeclaredField("plugins");
                pluginsField.setAccessible(true);
                plugins = (List<Plugin>) pluginsField.get(pluginManager);

                Field lookupNamesField = Bukkit.getPluginManager().getClass().getDeclaredField("lookupNames");
                lookupNamesField.setAccessible(true);
                names = (Map<String, Plugin>) lookupNamesField.get(pluginManager);

                try {
                    Field listenersField = Bukkit.getPluginManager().getClass().getDeclaredField("listeners");
                    listenersField.setAccessible(true);
                    listeners = (Map<Event, SortedSet<RegisteredListener>>) listenersField.get(pluginManager);
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                    reloadlisteners = false;
                }

                Field commandMapField = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap) commandMapField.get(pluginManager);

                Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
                knownCommandsField.setAccessible(true);
                commands = (Map<String, Command>) knownCommandsField.get(commandMap);

            } catch (NoSuchFieldException e) {
                return true;
            } catch (IllegalAccessException e) {
                return false;
            }
        }

        pluginManager.disablePlugin(plugin);

        if (plugins != null && plugins.contains(plugin))
            plugins.remove(plugin);

        if (names != null && names.containsKey(name))
            names.remove(name);

        if (listeners != null && reloadlisteners) {
            for (SortedSet<RegisteredListener> set : listeners.values()) {
                for (Iterator<RegisteredListener> it = set.iterator(); it.hasNext(); ) {
                    RegisteredListener value = it.next();
                    if (value.getPlugin() == plugin) {
                        it.remove();
                    }
                }
            }
        }

        if (commandMap != null) {
            for (Iterator<Map.Entry<String, Command>> it = commands.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Command> entry = it.next();
                if (entry.getValue() instanceof PluginCommand) {
                    PluginCommand c = (PluginCommand) entry.getValue();
                    if (c.getPlugin() == plugin) {
                        c.unregister(commandMap);
                        it.remove();
                    }
                }
            }
        }

        // Attempt to close the classloader to unlock any handles on the plugin's
        // jar file.
        ClassLoader cl = plugin.getClass().getClassLoader();

        if (cl instanceof URLClassLoader) {
            try {
                ((URLClassLoader) cl).close();
            } catch (IOException ex) {
                Logger.getLogger(PluginUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Will not work on processes started with the -XX:+DisableExplicitGC flag,
        // but lets try it anyway. This tries to get around the issue where Windows
        // refuses to unlock jar files that were previously loaded into the JVM.
        System.gc();

        return true;

    }
}