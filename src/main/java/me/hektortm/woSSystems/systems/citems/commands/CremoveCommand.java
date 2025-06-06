package me.hektortm.woSSystems.systems.citems.commands;


import me.hektortm.woSSystems.systems.citems.CitemManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.wosCore.LangManager;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CremoveCommand implements CommandExecutor {

    private final CitemManager data;
    private final LangManager lang;
    public CremoveCommand(CitemManager data, LangManager lang) {
        this.data = data;
        this.lang = lang;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("cremove")) {
            if (!PermissionUtil.hasPermission(sender, Permissions.CITEM_REMOVE)) return true;

            if (args.length < 2 || args.length > 3) {
                Utils.error(sender, "citems", "error.usage.cremove");
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);
            String id = args[1];
            Integer amount = 1;
            if (args.length == 3) {
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    Utils.error(sender, "citems", "error.usage.cremove");
                    return true;
                }
            }


            ItemStack savedItem = data.getCitemDAO().getCitem(id);
            ItemStack item = savedItem.clone();

            item.setAmount(amount);
            if (savedItem == null) {
                Utils.error(sender, "citems", "error.not-found");
                return true;
            }
            t.getInventory().removeItem(item);
            Utils.success(sender, "citems", "removed",
                    "%amount%", String.valueOf(amount),
                    "%id%", id, "%player%", t.getName());

        }
        return true;
    }
}
