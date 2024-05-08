package ru.matt.kiteditor.menus;

// Alpha-Pvp????)

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* Для ЕБЕЙШЕГО ПИВИПИ
   @author: deadlyPig
 */

public class PVP {
    private JavaPlugin plugin;

    public PVP(JavaPlugin plugin) {
        this.plugin = plugin;
        getPvp();
    }

    public Inventory getPvp() {
        return createNewPvpInventory();
    }

    public Inventory createNewPvpInventory() {
        Inventory newPvp = Bukkit.createInventory(null, 54);
        loadItems(newPvp);
        return newPvp;
    }

    // Я ЕБАЛ НАХУЙ

    private void loadItems(Inventory inventory) {
        File itemconfig = new File(plugin.getDataFolder(), "items.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(itemconfig);

        Map<Integer, ItemStack> items = new HashMap<>();
        ConfigurationSection itemsSection = configYaml.getConfigurationSection("pvp.items");
        if (itemsSection!= null) {
            for (String key : itemsSection.getKeys(false)) {
                ItemStack item = new ItemStack(Material.getMaterial(key.toUpperCase()));
                int amount = itemsSection.getInt(key + ".amount");
                Map<Enchantment, Integer> enchantments = new HashMap<>();
                ConfigurationSection enchantmentsSection = itemsSection.getConfigurationSection(key + ".enchantments");
                if (enchantmentsSection!= null) {
                    for (String enchantmentKey : enchantmentsSection.getKeys(false)) {
                        enchantments.put(Enchantment.getByName(enchantmentKey), enchantmentsSection.getInt(enchantmentKey + ".lvl"));
                    }
                    for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                        item.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
                    }
                    item.setAmount(amount);
                    items.put(configYaml.getInt("pvp.items." + key + ".slot"), item);
                }
            }
        }

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }
}