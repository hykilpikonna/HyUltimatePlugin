package cc.moecraft.hykilpikonna.ult.fun.features.messengers;

import cc.moecraft.hykilpikonna.ult.Messengers;
import cc.moecraft.hykilpikonna.ult.api.Messenger;
import cc.moecraft.hykilpikonna.ult.fun.Fun;
import cc.moecraft.hykilpikonna.ult.fun.features.Features;
import org.bukkit.plugin.java.JavaPlugin;
import static org.bukkit.ChatColor.*;

/**
 * 此类由 Hykilpikonna 在 2017/08/08 创建!
 * Created by Hykilpikonna on 2017/08/08!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class ProjectileDamageMessenger extends Messenger
{
    public ProjectileDamageMessenger()
    {
        super(Fun.getInstance(), Features.getProjectileDamage().getFriendlyName());
    }

    @Override
    public String prefix()
    {
        return "&7&l[&6&l投掷物伤害&7&l] &r";
    }

    @Override
    public void writeConfig()
    {
        addDefault("projectile.Egg", "鸡蛋");
        addDefault("projectile.SnowBall", "雪球");
        addDefault("projectile.EnderPearl", "末影珍珠");
        addDefault("hit_player", GREEN + "您发射的%PROJECTILE%已击中玩家" + YELLOW + "%PLAYER%" + GREEN + ", 该玩家掉了%DAMAGE%血, 还剩%HEALTH%血");
        addDefault("hit_entity", GREEN + "您发射的%PROJECTILE%已击中" + YELLOW + "%ENTITY%" + GREEN + "实体, 它掉了%DAMAGE%血, 还剩%HEALTH%血");

        addDefault("entity_Bat_name", "蝙蝠");
        addDefault("entity_Blaze_name", "烈焰人");
        addDefault("entity_Cat_name", "猫");
        addDefault("entity_CaveSpider_name", "洞穴蜘蛛");
        addDefault("entity_Chicken_name", "鸡");
        addDefault("entity_Cow_name", "牛");
        addDefault("entity_Creeper_name", "爬行者");
        addDefault("entity_Donkey_name", "驴");
        addDefault("entity_ElderGuardian_name", "远古守卫者");
        addDefault("entity_EnderDragon_name", "末影龙");
        addDefault("entity_Enderman_name", "末影人");
        addDefault("entity_Endermite_name", "末影螨");
        addDefault("entity_Evoker_name", "唤魔者");
        addDefault("entity_Ghast_name", "恶魂");
        addDefault("entity_Giant_name", "巨人");
        addDefault("entity_Guardian_name", "守卫者");
        addDefault("entity_Horse_name", "马");
        addDefault("entity_Husk_name", "尸壳");
        addDefault("entity_KillerBunny_name", "杀手兔");
        addDefault("entity_LavaSlime_name", "岩浆怪");
        addDefault("entity_Llama_name", "羊驼");
        addDefault("entity_Mule_name", "骡");
        addDefault("entity_MushroomCow_name", "哞菇");
        addDefault("entity_Ozelot_name", "豹猫");
        addDefault("entity_Pig_name", "猪");
        addDefault("entity_PigZombie_name", "僵尸猪人");
        addDefault("entity_PolarBear_name", "北极熊");
        addDefault("entity_Rabbit_name", "兔子");
        addDefault("entity_Sheep_name", "羊");
        addDefault("entity_Shulker_name", "潜影贝");
        addDefault("entity_Silverfish_name", "蠹虫");
        addDefault("entity_Skeleton_name", "骷髅");
        addDefault("entity_SkeletonHorse_name", "骷髅马");
        addDefault("entity_Slime_name", "史莱姆");
        addDefault("entity_SnowMan_name", "雪傀儡");
        addDefault("entity_Spider_name", "蜘蛛");
        addDefault("entity_Squid_name", "鱿鱼");
        addDefault("entity_Stray_name", "流髑");
        addDefault("entity_Vex_name", "恼鬼");
        addDefault("entity_Villager_name", "村民");
        addDefault("entity_VillagerGolem_name", "铁傀儡");
        addDefault("entity_VindicationIllager_name", "卫道士");
        addDefault("entity_Witch_name", "女巫");
        addDefault("entity_WitherBoss_name", "凋灵");
        addDefault("entity_WitherSkeleton_name", "凋灵骷髅");
        addDefault("entity_Wolf_name", "狼");
        addDefault("entity_Zombie_name", "僵尸");
        addDefault("entity_ZombieHorse_name", "僵尸马");
        addDefault("entity_ZombieVillager_name", "僵尸村民");
        addDefault("entity_generic_name", "未知");
    }
}
