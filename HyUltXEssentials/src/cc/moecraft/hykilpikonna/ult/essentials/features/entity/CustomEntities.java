package cc.moecraft.hykilpikonna.ult.essentials.features.entity;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/20 创建!
 * Created by Hykilpikonna on 2017/08/20!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public enum CustomEntities
{
    ARROW("Arrow", Enemies.NEUTRAL, EntityType.ARROW),
    SNOWBALL("Snowball", Enemies.NEUTRAL, EntityType.SNOWBALL),
    FIREBALL("Fireball", Enemies.NEUTRAL, EntityType.FIREBALL),
    SMALL_FIREBALL("Small_Fireball", Enemies.NEUTRAL, EntityType.SMALL_FIREBALL),
    DRAGON_FIREBALL("Dragon_Fireball", Enemies.NEUTRAL, EntityType.DRAGON_FIREBALL),
    EGG("Egg", Enemies.NEUTRAL, EntityType.EGG),
    WITHER_SKULL("Wither_Skull", Enemies.NEUTRAL, EntityType.WITHER_SKULL),
    ENDER_PEARL("Ender_Pearl", Enemies.NEUTRAL, EntityType.ENDER_PEARL);

    CustomEntities(String n, CustomEntities.Enemies en, String s, EntityType type)
    {
        this.suffix = s;
        this.name = n;
        this.type = en;
        this.bukkitType = type;
    }

    CustomEntities(String n, CustomEntities.Enemies en, EntityType type)
    {
        this.name = n;
        this.type = en;
        this.bukkitType = type;
    }

    CustomEntities(String n, CustomEntities.Enemies en, String typeName)
    {
        this.name = n;
        this.type = en;
        EntityType entityType;
        try
        {
            entityType = EntityType.valueOf(typeName);
        } catch (IllegalArgumentException ignored) { entityType = null; }
        bukkitType = entityType;
    }

    public String suffix = "s";
    final public String name;
    final public CustomEntities.Enemies type;
    final private EntityType bukkitType;
    private static final Map<String, CustomEntities> hashMap = new HashMap<>();
    private static final Map<EntityType, CustomEntities> bukkitMap = new HashMap<>();

    static
    {
        for (CustomEntities mob : CustomEntities.values())
        {
            hashMap.put(mob.name.toLowerCase(), mob);
            if (mob.bukkitType != null) bukkitMap.put(mob.bukkitType, mob);
        }
    }

    public static Set<String> getCustomEntitiesList()
    {
        return Collections.unmodifiableSet(hashMap.keySet());
    }

    public Entity spawn(final World world, final Server server, final Location loc) throws CustomEntities.CustomEntitiesException
    {
        final Entity entity = world.spawn(loc, this.bukkitType.getEntityClass());
        if (entity == null) throw new CustomEntities.CustomEntitiesException();
        return entity;
    }


    public enum Enemies
    {
        FRIENDLY("friendly"),
        NEUTRAL("neutral"),
        ENEMY("enemy");

        Enemies(final String type)
        {
            this.type = type;
        }

        final protected String type;
    }

    public EntityType getType()
    {
        return bukkitType;
    }

    public static CustomEntities fromName(final String name)
    {
        return hashMap.get(name.toLowerCase());
    }

    public static CustomEntities fromBukkitType(final EntityType type)
    {
        return bukkitMap.get(type);
    }

    public static class CustomEntitiesException extends Exception
    {
        private static final long serialVersionUID = 1L;
    }

    public static String[] getNames(Class<? extends Enum<?>> e)
    {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
