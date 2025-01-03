package me.hektortm.woSSystems.systems.citems.commands.subcommands;


import me.hektortm.woSSystems.systems.citems.commands.CitemCommand;
import me.hektortm.woSSystems.systems.citems.CitemManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;

public class SaveCommand extends SubCommand {

    private final CitemCommand cmd;
    private final CitemManager data;

    public SaveCommand(CitemCommand cmd, CitemManager data) {
        this.cmd = cmd;
        this.data = data;
    }
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.CITEM_SAVE;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!PermissionUtil.isPlayer(sender)) return;

        Player p = (Player) sender;

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        ItemMeta meta = itemInHand.getItemMeta();

        if (args.length != 1) {
            Utils.error(p, "citems", "error.usage.save");
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

        String id = args[0];

        if (!cmd.citemsFolder.exists()) {
            cmd.citemsFolder.mkdirs();
        }
        File itemFile = new File(cmd.citemsFolder, id + ".json");
        if (itemFile.exists()) {
            Utils.error(p, "citems", "error.exists");
            return;
        }
        data.saveItemToFile(itemInHand, itemFile, id);
        Utils.successMsg1Value(p, "citems", "saved", "%id%", id);
    }
}
