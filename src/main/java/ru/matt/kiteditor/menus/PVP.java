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

public class PVP {
    private Inventory pvp;
    private JavaPlugin plugin;

    public PVP(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pvp = Bukkit.createInventory(null, 54);
        loadItems();
    }

    public Inventory getPvp() {
        return pvp;
    }

    private void loadItems() {
        File itemconfig = new File(plugin.getDataFolder(), "items.yml");
        YamlConfiguration configYaml = YamlConfiguration.loadConfiguration(itemconfig);

        Map<Integer, ItemStack> items = new HashMap<>();
        ConfigurationSection itemsSection = configYaml.getConfigurationSection("pvp.items");
        if (itemsSection != null) {
            for (String key : itemsSection.getKeys(false)) {
                ItemStack item = new ItemStack(Material.getMaterial(key.toUpperCase()));
                Map<Enchantment, Integer> enchantments = new HashMap<>();
                ConfigurationSection enchantmentsSection = itemsSection.getConfigurationSection(key + ".enchantments");
                if (enchantmentsSection != null) {
                    for (String enchantmentKey : enchantmentsSection.getKeys(false)) {
                        enchantments.put(Enchantment.getByName(enchantmentKey), enchantmentsSection.getInt(enchantmentKey + ".lvl"));
                    }
                    for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                        item.addUnsafeEnchantment(enchantment.getKey(), enchantment.getValue());
                    }
                    items.put(configYaml.getInt("pvp.items." + key + ".slot"), item);
                }
            }
        }

        for (Map.Entry<Integer, ItemStack> entry : items.entrySet()) {
            pvp.setItem(entry.getKey(), entry.getValue());
        }
    }
}