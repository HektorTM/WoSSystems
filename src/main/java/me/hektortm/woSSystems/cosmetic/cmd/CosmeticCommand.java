package me.hektortm.woSSystems.cosmetic.cmd;

import me.hektortm.woSSystems.cosmetic.CosmeticManager;

import me.hektortm.woSSystems.cosmetic.cmd.subcmd.Give;

import me.hektortm.woSSystems.database.DAOHub;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CosmeticCommand implements CommandExecutor {
    private final Map<String, SubCommand> subCommands = new HashMap<>();
    private final CosmeticManager manager;
    private final DAOHub hub;

    public CosmeticCommand(CosmeticManager manager, DAOHub hub) {
        this.manager = manager;
        this.hub = hub;

        subCommands.put("give", new Give(hub));


    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        String subCommandName;
        SubCommand subCommand;
        if (!(sender instanceof Player)) {
            subCommandName = args[0].toLowerCase();
            subCommand = subCommands.get(subCommandName);
            subCommand.execute(sender, java.util.Arrays.copyOfRange(args, 1, args.length));
        } else {

            if (args.length == 0) {
                manager.openMainPage((Player) sender);
                return true;
            }
            subCommandName = args[0].toLowerCase();
            subCommand = subCommands.get(subCommandName);

            if (subCommand != null) {
                if(!(PermissionUtil.hasPermissionNoMsg(sender, subCommand.getPermission()))) {
                    manager.openMainPage((Player) sender);
                    return true;
                }
                subCommand.execute(sender, java.util.Arrays.copyOfRange(args, 1, args.length));
            } else {
                manager.openMainPage((Player) sender);
            }
        }


        return true;
    }


}
