package me.hektortm.woSSystems.systems.guis;

import me.hektortm.woSSystems.utils.dataclasses.gui.GUIConfig;
import me.hektortm.woSSystems.utils.dataclasses.gui.Slot;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

import java.util.List;

public class GUIListener implements Listener {
    private final String guiId;
    private final GUIConfig guiConfig;

    public GUIListener(String guiId, GUIConfig guiConfig) {
        this.guiId = guiId;
        this.guiConfig = guiConfig;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(guiConfig.getTitle())) {
            return;
        }

        int slotIndex = event.getSlot();
        Slot slot = guiConfig.getSlots().get(slotIndex);

        // If the slot is null or not clickable, cancel the event
        if (slot == null || !slot.getAttributes().isClickable()) {
            event.setCancelled(true);
            return;
        }

        // Handle slot actions based on click type
        Player player = (Player) event.getWhoClicked();
        switch (event.getClick()) {
            case LEFT:
                runActions(slot.getActions().getLeft(), player);
                break;
            case RIGHT:
                runActions(slot.getActions().getRight(), player);
                break;
            case SHIFT_LEFT:
                runActions(slot.getActions().getShiftLeft(), player);
                break;
            case SHIFT_RIGHT:
                runActions(slot.getActions().getShiftRight(), player);
                break;
            case DROP:
                runActions(slot.getActions().getDrop(), player);
                break;
            default:
                // Cancel all unhandled interactions
                event.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!event.getView().getTitle().equals(guiConfig.getTitle())) {
            return;
        }

        // Cancel dragging if any of the dragged slots are not clickable
        for (int slotIndex : event.getRawSlots()) {
            if (slotIndex < 0 || slotIndex >= guiConfig.getSlots().size()) {
                continue;
            }

            Slot slot = guiConfig.getSlots().get(slotIndex);
            if (slot != null && !slot.getAttributes().isClickable()) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(guiConfig.getTitle())) {
            return;
        }

        System.out.println("GUI closed for ID: " + guiId);
    }

    private void runActions(List<String> actions, Player player) {
        if (actions == null || actions.isEmpty()) {
            return;
        }

        for (String action : actions) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action.replace("%player%", player.getName()));
        }
    }
}
