package me.hektortm.woSSystems.systems.unlockables.commands.subcommands;

import me.hektortm.woSSystems.systems.unlockables.UnlockableManager;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TempCreateCommand extends SubCommand {

    private final UnlockableManager manager;

    public TempCreateCommand(UnlockableManager manager) {
        this.manager = manager;
    }

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public Permissions getPermission() {
        return Permissions.UNLOCKABLE_TEMP_CREATE;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!PermissionUtil.isPlayer(sender)) return;

        if (args.length != 1) {
            Utils.error(sender, "unlockables", "usage.temp.create");
            return;
        }

        String id = args[0].toLowerCase();

        if(manager.tempUnlockables.containsKey(id)) {
            Utils.error(sender, "unlockables", "error.exists");
            return;
        }

        manager.addTempUnlockable(sender, id);
    }
}
