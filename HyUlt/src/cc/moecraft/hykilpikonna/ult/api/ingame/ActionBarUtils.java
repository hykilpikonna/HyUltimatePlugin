package cc.moecraft.hykilpikonna.ult.api.ingame;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static cc.moecraft.hykilpikonna.ult.Main.getMain;
import static cc.moecraft.hykilpikonna.ult.utils.VersionUtils.getShortVersion;

/**
 * 此类由 Hykilpikonna 在 2017/07/11 创建!
 * Created by Hykilpikonna on 2017/07/11!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 *
 * Credit to @ConnorLinfoot
 */
public class ActionBarUtils
{
    public static String nmsver;

    public ActionBarUtils()
    {
        nmsver = Bukkit.getServer().getClass().getPackage().getName();
        nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
    }

    public void sendActionBar(Player player, String message)
    {
        if (player.isOnline())
        {
            if (getShortVersion() > 1.12)
            {
                sendActionBarPost112(player, message);
            }
            else
            {
                sendActionBarPre112(player, message);
            }
        }
    }

    public void sendActionBar(List<Player> players, String message)
    {
        for (Player onePlayer : players)
        {
            sendActionBar(onePlayer, message);
        }
    }

    public void sendActionBar(List<Player> players, String message, int duration)
    {
        for (Player onePlayer : players)
        {
            sendActionBar(onePlayer, message, duration);
        }
    }

    public void sendActionBarToAllPlayers(String message, int duration)
    {
        ArrayList<Player> onlinePlayers = new ArrayList<>();
        onlinePlayers.addAll(Bukkit.getOnlinePlayers());
        sendActionBar(onlinePlayers, message, duration);
    }

    public void sendActionBarToAllPlayers(String message)
    {
        sendActionBarToAllPlayers(message, -1);
    }

    public void sendActionBar(Player player, String message, int duration)
    {
        sendActionBar(player, message);
        if (duration >= 0)
        {
            Runnable undoActionBar = new BukkitRunnable()
            {
                public void run()
                {
                    sendActionBar(player, "");
                }
            };
            getMain().getServer().getScheduler().scheduleSyncDelayedTask(getMain(), undoActionBar, duration + 1);
        }

        if (duration > 40)
        {
            Runnable keepActionBar = new BukkitRunnable()
            {
                public void run()
                {
                    sendActionBar(player, message);
                }
            };
            getMain().getServer().getScheduler().scheduleSyncDelayedTask(getMain(), keepActionBar, duration);
        }
    }

    private void sendActionBarPost112(Player player, String message)
    {
        try
        {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);

            Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
            Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
            Class<?> chatMessageTypeClass = Class.forName("net.minecraft.server." + nmsver + ".ChatMessageType");
            Object[] chatMessageTypes = chatMessageTypeClass.getEnumConstants();
            Object chatMessageType = null;
            for (Object obj : chatMessageTypes) {
                if (obj.toString().equals("GAME_INFO")) {
                    chatMessageType = obj;
                }
            }
            Object o = c2.getConstructor(new Class[] { String.class }).newInstance(message);
            Object ppoc = c4.getConstructor(new Class[] { c3, chatMessageTypeClass }).newInstance(o, chatMessageType);
            Method m1 = craftPlayerClass.getDeclaredMethod("getHandle");
            Object h = m1.invoke(craftPlayer);
            Field f1 = h.getClass().getDeclaredField("playerConnection");
            Object pc = f1.get(h);
            Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
            m5.invoke(pc, ppoc);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void sendActionBarPre112(Player player, String message)
    {
        if (!player.isOnline()) {
            return;
        }
        try
        {
            Class<?> craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            Object craftPlayer = craftPlayerClass.cast(player);

            Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            Object ppoc;
            Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
            Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
            Object o = c2.getConstructor(new Class[] { String.class }).newInstance(message);
            ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(o, (byte) 2);
            Method m1 = craftPlayerClass.getDeclaredMethod("getHandle");
            Object h = m1.invoke(craftPlayer);
            Field f1 = h.getClass().getDeclaredField("playerConnection");
            Object pc = f1.get(h);
            Method m5 = pc.getClass().getDeclaredMethod("sendPacket", c5);
            m5.invoke(pc, ppoc);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
