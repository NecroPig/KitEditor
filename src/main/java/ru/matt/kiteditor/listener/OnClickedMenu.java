package ru.matt.kiteditor.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OnClickedMenu implements Listener {
    @EventHandler
    public void onClickedMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory playerInventory = player.getInventory();
        ItemStack clickedItem = event.getCurrentItem();
        if (event.getInventory().equals("Pvp Menu")) {
            if (clickedItem != null) {
                playerInventory.addItem(clickedItem);
                event.setCancelled(true);
            }
        }
    }
}