package me.hektortm.woSSystems.systems.stats.commands.subcommands;

import me.hektortm.woSSystems.systems.stats.StatsManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class DeleteCommand extends SubCommand {

    private final StatsManager manager;

    public DeleteCommand(StatsManager manager) {
        this.manager = manager;
    }
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.STATS_DELETE;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!PermissionUtil.isPlayer(sender)) return;

        Player p = (Player) sender;
        String id = args[0].toLowerCase();

        if (!manager.getGlobalStats().containsKey(id)) {
            Utils.error(p, "stats", "error.not-found");
            return;
        }
        if (args.length == 1) {
            Utils.successMsg1Value(p, "stats", "delete.confirm", "%id%", id);
            return;
        }

        if(args.length == 2 && args[1].equals("confirm")) {
            manager.deleteStat(p, id, false);
            Utils.successMsg1Value(p, "stats", "delete.success", "%id%", id);
        }

    }


}
