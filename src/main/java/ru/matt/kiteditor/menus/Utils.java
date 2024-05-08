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

/* Для разной полезной хуйни я хз
   а ваще кому не похуй че это
   @author: deadlyPig
 */

public class Utils {
    private JavaPlugin plugin;

    public Utils(JavaPlugin plugin) {
        this.plugin = plugin;
        getUtils();
    }

    public Inventory getUtils() {
        return createNewUtilsInventory();
    }

    public Inventory createNewUtilsInventory() {
        Inventory newUtils = Bukkit.createInventory(null, 54);
        loadItems(newUtils);
        return newUtils;
    }

    // Я ЕБАЛ НАХУЙ

    private void loadItems(Inventory inventory) {
        File utilsconfig = new File(plugin.getDataFolder(), "utils.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(utilsconfig);

        Map<Integer, ItemStack> items = new HashMap<>();
        ConfigurationSection itemsSection = configYaml.getConfigurationSection("utils.items");
        if (itemsSection!= null) {
            for (String key : itemsSection.getKeys(false)) {
                ItemStack item = new ItemStack(Material.getMaterial(key.toUpperCase()));
                int amount = itemsSection.getInt(key + ".amount");
                item.setAmount(amount);
                items.put(configYaml.getInt("utils.items." + key + ".slot"), item);
            }
        }

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }
}