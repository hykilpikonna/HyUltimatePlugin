package cc.moecraft.hykilpikonna.ult.fun.features.listeners;

import cc.moecraft.hykilpikonna.ult.Main;
import cc.moecraft.hykilpikonna.ult.api.Listener;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import cc.moecraft.hykilpikonna.ult.fun.features.calculations.ProjectileDamageCalculations;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ProjectileDamageListener extends Listener
{
    public ProjectileDamageListener()
    {
        super(Fun.getInstance());
    }

    @EventHandler
    public void onEvent(EntityDamageByEntityEvent event)
    {
        if (event.getEntity() instanceof Damageable && event.getDamager() instanceof Projectile)
        {
            Damageable entity = (Damageable) event.getEntity();
            if (ProjectileDamageCalculations.getStaticEnabledWorldList().contains(entity.getWorld().getName()))
            {
                String entityID;
                Projectile damager = (Projectile) event.getDamager();

                if (damager instanceof Snowball)
                    entityID = "SnowBall";
                else if (damager instanceof EnderPearl)
                    entityID = "EnderPearl";
                else if (damager instanceof Egg)
                    entityID = "Egg";
                else return;

                Double damage = Features.getProjectileDamage().getConfig().getDouble("Damage." + entityID);
                if (damager.getShooter() == null) return;
                Player player = (Player) damager.getShooter();

                if (!Features.getProjectileDamage().getPermissionsConfig().hasPermission(player, "hyult.pd.damage", false)) return;
                entity.damage(damage);

                if (entity instanceof Player)
                {
                    String message = Features.getProjectileDamage().getMessenger().getWithPrefix("hit_player")
                            .replace("%HEALTH%", Long.toString(Math.round(entity.getHealth())))
                            .replace("%DAMAGE%", Double.toString(Features.getProjectileDamage().getConfig().getDouble("Damage." + entityID)))
                            .replace("%PLAYER%", entity.getName())
                            .replace("%PROJECTILE%", Features.getProjectileDamage().getMessenger().get("projectile." + entityID));

                    if (Features.getProjectileDamage().getConfig().getBoolean("Message.SendMessage"))
                        player.sendMessage(message);
                    if (Features.getProjectileDamage().getConfig().getBoolean("Message.ActionBar"))
                        Main.getActionBarUtils().sendActionBar(player, message, Features.getProjectileDamage().getConfig().getInt("Message.ActionBarDuration"));
                    return;
                }
                TextComponent message = new TextComponent();
                String raw1 = Features.getProjectileDamage().getMessenger().get("hit_entity")
                        .replace("%HEALTH%", Integer.toString((int) Math.round(entity.getHealth() / 2.0)))
                        .replace("%DAMAGE%", Integer.toString((int) Math.round(Features.getProjectileDamage().getConfig().getDouble("Damage." + entityID) / 2)))
                        .replace("%PROJECTILE%", Features.getProjectileDamage().getMessenger().get("projectile." + entityID));
                String[] raw = raw1.split("%");
                message.addExtra(Features.getProjectileDamage().getMessenger().getPrefix());
                for (String oneOfRaw : raw)
                {
                    if (oneOfRaw.equals("ENTITY"))
                        if (entity.getCustomName() != null) message.addExtra(entity.getCustomName());
                        else if (Features.getProjectileDamage().getConfig().getBoolean("Message.ActionBar"))
                            if (Features.getProjectileDamage().getMessenger().contains(String.format("entity_%s_name", entity.getName()))) message.addExtra(Features.getProjectileDamage().getMessenger().get(String.format("entity_%s_name", entity.getName())));
                            else message.addExtra(Features.getProjectileDamage().getMessenger().get("entity_generic_name").replace("%ENTITY%", entity.getName()));
                        else message.addExtra(new TranslatableComponent(String.format("entity.%s.name", entity.getName())));
                    else message.addExtra(oneOfRaw);
                }
                if (Features.getProjectileDamage().getConfig().getBoolean("Message.SendMessage")) player.spigot().sendMessage(message);
                if (Features.getProjectileDamage().getConfig().getBoolean("Message.ActionBar")) Main.getActionBarUtils().sendActionBar(player, message.toPlainText(), Features.getProjectileDamage().getConfig().getInt("Message.ActionBarDuration"));
            }
        }
    }
}
