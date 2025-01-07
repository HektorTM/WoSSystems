package me.hektortm.woSSystems.systems.guis;

import me.hektortm.woSSystems.utils.dataclasses.gui.GUIConfig;
import me.hektortm.woSSystems.utils.dataclasses.gui.Item;
import me.hektortm.woSSystems.utils.dataclasses.gui.Slot;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class GUIHandler {
    private final GUIManager guiManager;

    public GUIHandler(GUIManager guiManager) {
        this.guiManager = guiManager;
    }

    /**
     * Opens a GUI for the player based on the given ID.
     *
     * @param id     The ID of the GUI to open.
     * @param player The player for whom to open the GUI.
     */
    public void openGUI(String id, Player player) {
        GUIConfig config = guiManager.getGuiConfig(id);

        if (config == null) {
            player.sendMessage("§cGUI not found: " + id);
            return;
        }

        Inventory inventory = createInventory(config);
        player.openInventory(inventory);
    }

    /**
     * Creates an inventory based on the provided GUIConfig.
     *
     * @param config The GUI configuration.
     * @return The constructed inventory.
     */
    private Inventory createInventory(GUIConfig config) {
        Inventory inventory = Bukkit.createInventory(null, config.getRows() * 9, config.getTitle());

        for (Map.Entry<Integer, Slot> entry : config.getSlots().entrySet()) {
            int slotIndex = entry.getKey();
            Slot slot = entry.getValue();

            // Skip slots that are not visible
            if (!slot.getAttributes().isVisible()) {
                continue;
            }

            // Create and set the item in the inventory
            ItemStack itemStack = createItemStack(slot.getItem());
            if (itemStack != null) {
                inventory.setItem(slotIndex, itemStack);
            }
        }

        return inventory;
    }

    /**
     * Creates an ItemStack from a custom Item object.
     *
     * @param item The custom item data.
     * @return The constructed ItemStack, or null if data is invalid.
     */
    private ItemStack createItemStack(Item item) {
        if (item == null) {
            return null;
        }

        try {
            ItemStack itemStack = new ItemStack(Material.valueOf(item.getMaterial()));
            ItemMeta meta = itemStack.getItemMeta();

            if (meta != null) {
                // Set item name
                meta.setDisplayName(item.getName());

                // Set lore
                meta.setLore(item.getLore());

                // Add enchantments if applicable
                if (item.isEnchanted()) {
                    meta.addEnchant(Enchantment.UNBREAKING, 1, true);
                }

                itemStack.setItemMeta(meta);
            }

            itemStack.setAmount(item.getAmount());
            return itemStack;
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().warning("Invalid material for item: " + item.getMaterial());
            return null;
        }
    }

    /**
     * Refreshes the GUI inventory for the player dynamically, updating visibility and items.
     *
     * @param player The player whose inventory should be refreshed.
     * @param config The GUI configuration.
     */
    public void refreshInventory(Player player, GUIConfig config) {
        Inventory inventory = player.getOpenInventory().getTopInventory();

        for (Map.Entry<Integer, Slot> entry : config.getSlots().entrySet()) {
            int slotIndex = entry.getKey();
            Slot slot = entry.getValue();

            // Handle visibility
            if (!slot.getAttributes().isVisible()) {
                inventory.setItem(slotIndex, null); // Clear the slot
            } else {
                ItemStack itemStack = createItemStack(slot.getItem());
                inventory.setItem(slotIndex, itemStack);
            }
        }

        player.updateInventory(); // Ensure changes are reflected
    }

    public void reloadGUI(String id, Player player) {
        boolean success = guiManager.reloadGuiConfig(id);

        if (success) {
            player.sendMessage("§aSuccessfully reloaded GUI: " + id);
        } else {
            player.sendMessage("§cFailed to reload GUI: " + id + ". Make sure the JSON is valid!");
        }
    }
}
