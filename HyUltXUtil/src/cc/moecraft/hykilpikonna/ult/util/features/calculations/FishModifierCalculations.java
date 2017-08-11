package cc.moecraft.hykilpikonna.ult.util.features.calculations;

import cc.moecraft.hykilpikonna.ult.util.features.others.Fish;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 此类由 Hykilpikonna 在 2017/08/10 创建!
 * Created by Hykilpikonna on 2017/08/10!
 * Twitter: @Hykilpikonna
 * QQ/Wechat: 871674895
 */
public class FishModifierCalculations 
{
    public static ArrayList<String> staticEnabledWorldList = new ArrayList<>();

    public static ArrayList<Fish> fishList;
    public static ArrayList<Double> chanceList;
    public static ArrayList<String> keys;

    public static double chanceDivideBy;
    public static ArrayList<Integer> intChance;

    public static ItemStack itemStackGen(Material material, int amount, int data, String displayName, String[] lore, boolean unbreakable, Enchantment[] enchantments, Integer[] levels)
    {
        ItemStack itemStack = new ItemStack(material, amount, (short) data);
        ItemMeta meta = itemStack.getItemMeta();
        if (displayName != null && !displayName.equals("")) meta.setDisplayName(displayName);
        if (lore != null && !(lore.length == 0)) meta.setLore(Arrays.asList(lore));
        meta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(meta);
        if (enchantments != null && levels != null && enchantments.length == levels.length)
        {
            for (int i = 0; i < enchantments.length; i++)
            {
                itemStack.addUnsafeEnchantment(enchantments[i], levels[i]);
            }
        }
        return itemStack;
    }
}
