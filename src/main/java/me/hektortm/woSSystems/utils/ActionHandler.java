package me.hektortm.woSSystems.utils;

import me.hektortm.woSSystems.WoSSystems;
import me.hektortm.woSSystems.utils.dataclasses.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class ActionHandler {

    private final PlaceholderResolver resolver = WoSSystems.getPlugin(WoSSystems.class).getPlaceholderResolver();

    public enum SourceType {
        INTERACTION("interaction"),
        GUI("gui");

        private final String type;
        SourceType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }


    public void executeActions(Player player, List<String> actions, SourceType sourceType, String sourceID) {
        for (String cmd : actions) {
            String parsedCommand = cmd.replace("@p", player.getName());
            if (cmd.startsWith("send_message")) {
                String message = cmd.replace("send_message ", "").replace("&", "§");
                player.sendMessage(resolver.resolvePlaceholders(message, player));
                continue;
            }
            if(cmd.startsWith("eco")) {
                String[] parts = cmd.split(" ");
                String actionType = parts[1];
                String currency = parts[3];
                int amount = Integer.parseInt(parts[4]);
                if (actionType.equalsIgnoreCase("give")) {
                    WoSSystems.getPlugin(WoSSystems.class).getEcoManager().ecoLog(player.getUniqueId(), currency, amount, sourceType.getType(), sourceID);

                }
                if (actionType.equalsIgnoreCase("take")) {
                    WoSSystems.getPlugin(WoSSystems.class).getEcoManager().ecoLog(player.getUniqueId(), currency, -amount, sourceType.getType(), sourceID);

                }
                if (actionType.equalsIgnoreCase("set")) {
                    WoSSystems.getPlugin(WoSSystems.class).getEcoManager().ecoLog(player.getUniqueId(), currency, amount, sourceType.getType(), sourceID);

                }
                if (actionType.equalsIgnoreCase("reset")) {
                    WoSSystems.getPlugin(WoSSystems.class).getEcoManager().ecoLog(player.getUniqueId(), currency, 0, sourceType.getType(), sourceID);

                }
            }
            if (cmd.startsWith("close_inventory")) {
                player.closeInventory();
                continue;
            }

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsedCommand);
        }
    }

}
