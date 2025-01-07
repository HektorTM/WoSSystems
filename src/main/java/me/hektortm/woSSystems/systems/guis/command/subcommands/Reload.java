package me.hektortm.woSSystems.systems.guis.command.subcommands;

import me.hektortm.woSSystems.systems.guis.GUIHandler;
import me.hektortm.woSSystems.utils.Permissions;
import me.hektortm.woSSystems.utils.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload extends SubCommand {
    private final GUIHandler guiHandler;

    public Reload(GUIHandler guiHandler) {
        this.guiHandler = guiHandler;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public Permissions getPermission() {
        return null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        String id = args[0];

        guiHandler.reloadGUI(id, (Player) sender);
    }
}
