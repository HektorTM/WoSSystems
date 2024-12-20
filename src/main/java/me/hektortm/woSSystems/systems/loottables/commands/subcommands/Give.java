package me.hektortm.woSSystems.systems.loottables.commands.subcommands;

import me.hektortm.woSSystems.systems.loottables.LoottableManager;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Give extends SubCommand {

    private final LoottableManager manager;

    public Give(LoottableManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.LOOTTABLE_GIVE;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            Utils.error(sender, "loottables", "error.usage.give");
            return;
        }

        Player p = Bukkit.getPlayer(args[0]);
        String id = args[1].toLowerCase();

        manager.giveLoottables(p, id);
        Utils.successMsg2Values(sender, "loottables", "given", "%player%", p.getName(), "%id%", id);


    }
}
