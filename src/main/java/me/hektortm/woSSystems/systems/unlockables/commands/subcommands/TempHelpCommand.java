package me.hektortm.woSSystems.systems.unlockables.commands.subcommands;

import me.hektortm.woSSystems.systems.unlockables.commands.TempUnlockableCommand;
import me.hektortm.woSSystems.utils.PermissionUtil;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import me.hektortm.wosCore.Utils;
import org.bukkit.command.CommandSender;

public class TempHelpCommand extends SubCommand {

    private final TempUnlockableCommand cmd;
    public TempHelpCommand(TempUnlockableCommand cmd) {
        this.cmd = cmd;
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public Permissions getPermission() {
        return null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (PermissionUtil.hasAnyPermission(sender, Permissions.UNLOCKABLE_TEMP_GIVE, Permissions.UNLOCKABLE_TEMP_TAKE, Permissions.UNLOCKABLE_TEMP_DELETE, Permissions.UNLOCKABLE_TEMP_CREATE)) {
            Utils.error(sender, "general", "error.perms");
            return;
        }

        cmd.tempUnlockableHelp(sender);

    }
}
