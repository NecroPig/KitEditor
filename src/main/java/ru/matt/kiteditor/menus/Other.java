package ru.matt.kiteditor.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* Буквально для другого
   Я НЕ СМОГ ПРИДУМАТЬ НАХУЯ ЕЩЕ ОДНО))
   @author: deadlyPig
 */

public class Other {
    private JavaPlugin plugin;

    public Other(JavaPlugin plugin) {
        this.plugin = plugin;
        getOther();
    }

    public Inventory getOther() {
        return createNewOtherInventory();
    }

    public Inventory createNewOtherInventory() {
        Inventory newOther = Bukkit.createInventory(null, 54);
        loadItems(newOther);
        return newOther;
    }

    // Я ЕБАЛ НАХУЙ

    private void loadItems(Inventory inventory) {
        File otherconfig = new File(plugin.getDataFolder(), "other.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(otherconfig);

        Map<Integer, ItemStack> items = new HashMap<>();
        ConfigurationSection itemsSection = configYaml.getConfigurationSection("other.items");
        if (itemsSection!= null) {
            for (String key : itemsSection.getKeys(false)) {
                ItemStack item = new ItemStack(Material.getMaterial(key.toUpperCase()));
                int amount = itemsSection.getInt(key + ".amount");
                item.setAmount(amount);
                items.put(configYaml.getInt("other.items." + key + ".slot"), item);
            }
        }

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }
}