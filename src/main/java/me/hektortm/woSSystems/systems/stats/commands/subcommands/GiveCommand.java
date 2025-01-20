package me.hektortm.woSSystems.systems.stats.commands.subcommands;

import me.hektortm.woSSystems.systems.stats.StatsManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

import static me.hektortm.woSSystems.systems.stats.utils.Operation.GIVE;

public class GiveCommand extends SubCommand {

    private final StatsManager manager;

    public GiveCommand(StatsManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.STATS_GIVE;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2 || args.length > 3) {
            Utils.error(sender, "stats", "error.usage.give");
            return;
        }

        OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
        String id = args[1].toLowerCase();
        long amount = 0L;

        if (!manager.getStats().containsKey(id)) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        try {
            amount = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            Utils.error(sender, "stats", "error.invalid-amount");
            return;
        }

        if (amount < 0) {
            Utils.error(sender, "stats", "error.invalid-amount");
            return;
        }

        if (!manager.getStats().containsKey(id)) {
            Utils.error(sender, "stats", "error.not-found");
            return;
        }

        manager.modifyStat(p.getUniqueId(), id, amount, GIVE);
        if (sender instanceof Player) {
            Utils.successMsg3Values(sender, "stats", "give", "%player%", p.getName(),"%stat%", id, "%amount%", String.valueOf(amount));
        }

    }
}
