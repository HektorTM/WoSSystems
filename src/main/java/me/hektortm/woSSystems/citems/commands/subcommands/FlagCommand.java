package me.hektortm.woSSystems.citems.commands.subcommands;


import me.hektortm.woSSystems.citems.commands.CitemSubCommand;
import me.hektortm.woSSystems.citems.DataManager;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class FlagCommand extends CitemSubCommand {

    private final NamespacedKey undroppableKey;
    private final NamespacedKey unusableKey;

    public FlagCommand(DataManager data) {
        undroppableKey = new NamespacedKey(Bukkit.getPluginManager().getPlugin("WoSSystems"), "undroppable");
        unusableKey = new NamespacedKey(Bukkit.getPluginManager().getPlugin("WoSSystems"), "unusable");
    }


    @Override
    public String getName() {
        return "flag";
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            Utils.error(sender, "general", "error.notplayer");
            return;
        }

        if (!sender.hasPermission("citem.flag")) {
            Utils.error(sender, "general", "error.perms");
            return;
        }

        Player p = (Player) sender;

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        ItemMeta meta = itemInHand.getItemMeta();

        if (args.length < 2) {
            Utils.error(p, "citems", "error.usage.flag");
            return;
        }

        if (itemInHand == null || itemInHand.getType() == Material.AIR) {
            Utils.error(p, "citems", "error.holding-item");
            return;
        }
        if (meta == null) {
            Utils.error(p, "citems", "error.no-meta");
            return;
        }
        String flagCmd = args[0];
        String flag = args[1].toLowerCase();
        switch (flagCmd.toLowerCase()) {
            case "add":
                if (flag.equals("undroppable")) {
                    PersistentDataContainer data = meta.getPersistentDataContainer();
                    data.set(undroppableKey, PersistentDataType.BOOLEAN, true);
                    Utils.successMsg(p, "citems", "flag.add.undroppable");
                }
                if (flag.equals("unbreakable")) {
                    meta.setUnbreakable(true);
                    Utils.successMsg(p, "citems", "flag.add.unbreakable");
                }
                if (flag.equals("unusable")) {
                    PersistentDataContainer data = meta.getPersistentDataContainer();
                    data.set(unusableKey, PersistentDataType.BOOLEAN, true);
                    Utils.successMsg(p, "citems", "flag.add.unusable");
                }
                break;

            case "remove":
                if (flag.equals("undroppable")) {
                    PersistentDataContainer data = meta.getPersistentDataContainer();
                    data.remove(undroppableKey);
                    Utils.successMsg(p, "citems", "flag.remove.undroppable");
                }
                if (flag.equals("unbreakable")) {
                    meta.setUnbreakable(false);
                    Utils.successMsg(p, "citems", "flag.remove.unbreakable");
                }
                if (flag.equals("unusable")) {
                    PersistentDataContainer data = meta.getPersistentDataContainer();
                    data.remove(unusableKey);
                    Utils.successMsg(p, "citems", "flag.remove.unusable");
                }
                break;

            default:
                Utils.error(p, "citems", "error.usage.flag");
                return;
        }
        itemInHand.setItemMeta(meta);
    }
}