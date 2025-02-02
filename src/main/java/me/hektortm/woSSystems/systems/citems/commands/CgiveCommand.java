package me.hektortm.woSSystems.systems.citems.commands;


import me.hektortm.woSSystems.systems.citems.CitemManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.wosCore.LangManager;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CgiveCommand implements CommandExecutor {

    private final CitemManager data;

    public CgiveCommand(CitemManager data) {
        this.data = data;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("cgive")) {
            if (!PermissionUtil.hasPermission(sender, Permissions.CITEM_GIVE)) return true;

            if (args.length < 2 || args.length > 3) {
                Utils.error(sender, "citems", "error.usage.cgive");
                return true;
            }

            Player t = Bukkit.getPlayer(args[0]);
            String id = args[1];
            Integer amount = 1;

            if(args.length == 3) {
                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    Utils.error(sender, "citems", "error.usage.cgive");
                    return true;
                }

            }

            data.giveCitem(sender, t, id, amount);



        }
        return true;
    }
}
