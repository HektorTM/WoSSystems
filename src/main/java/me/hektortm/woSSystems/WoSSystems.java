package me.hektortm.woSSystems;

import me.hektortm.woSSystems.citems.commands.CgiveCommand;
import me.hektortm.woSSystems.citems.commands.CitemCommand;
import me.hektortm.woSSystems.citems.commands.CremoveCommand;
import me.hektortm.woSSystems.citems.DataManager;
import me.hektortm.woSSystems.citems.listeners.DropListener;
import me.hektortm.woSSystems.citems.listeners.HoverListener;
import me.hektortm.woSSystems.citems.listeners.UseListener;
import me.hektortm.woSSystems.interactions.actions.InventoryInteraction;
import me.hektortm.woSSystems.interactions.commands.GUIcommand;
import me.hektortm.woSSystems.interactions.commands.InteractionCommand;
import me.hektortm.woSSystems.interactions.config.YAMLLoader;
import me.hektortm.woSSystems.interactions.core.ActionHandler;
import me.hektortm.woSSystems.interactions.core.BindManager;
import me.hektortm.woSSystems.interactions.core.InteractionConfig;
import me.hektortm.woSSystems.interactions.core.InteractionManager;
import me.hektortm.woSSystems.interactions.gui.GUIManager;
import me.hektortm.woSSystems.interactions.listeners.InterBlockListener;
import me.hektortm.woSSystems.interactions.listeners.InventoryClickListener;
import me.hektortm.woSSystems.interactions.listeners.InventoryCloseListener;
import me.hektortm.woSSystems.interactions.particles.ParticleHandler;
import me.hektortm.woSSystems.stats.StatsManager;
import me.hektortm.woSSystems.stats.commands.StatsCommand;
import me.hektortm.woSSystems.utils.PlaceholderResolver;
import me.hektortm.wosCore.LangManager;
import me.hektortm.wosCore.WoSCore;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class WoSSystems extends JavaPlugin {

    private static LangManager lang;
    WoSCore core = JavaPlugin.getPlugin(WoSCore.class);
    private InteractionManager interactionManager;
    private GUIManager guiManager;
    private BindManager bindManager;
    private ParticleHandler particleHandler;
    private StatsManager statsManager;
    private PlaceholderResolver resolver;
    private static DataManager data;

    // TODO:
    //  - Interactions
    //      - Conditions
    //   - Stats
    //      -

    @Override
    public void onEnable() {

        YAMLLoader yamlLoader = new YAMLLoader(this);


        particleHandler = new ParticleHandler();
        bindManager = new BindManager(this);
        statsManager = new StatsManager(core);

        resolver = new PlaceholderResolver(statsManager);

        ActionHandler actionHandler = new ActionHandler(this, resolver);
        guiManager = new GUIManager(this, actionHandler, resolver);
        interactionManager = new InteractionManager(yamlLoader, this, guiManager, particleHandler, resolver);

        lang = new LangManager(core);
        data = new DataManager(new me.hektortm.woSSystems.citems.commands.CitemCommand(this, data, interactionManager, lang), interactionManager);
        Map<String, InteractionConfig> interactionConfigs = yamlLoader.loadInteractions();

        if (core != null) {
            lang.loadLangFileExternal(this, "citems", core);
            lang.loadLangFileExternal(this, "stats", core);
        } else {
            getLogger().severe("WoSCore not found. Disabling WoSSystems");
        }

        // Interaction Commands
        cmdReg("opengui", new GUIcommand(guiManager, interactionManager));
        cmdReg("interaction", new InteractionCommand(interactionManager, bindManager));
        // Citems Commands
        cmdReg("citem", new CitemCommand(this, data, interactionManager, lang));
        cmdReg("cgive", new CgiveCommand(data, lang));
        cmdReg("cremove", new CremoveCommand(data, lang));
        // Stats Commands
        cmdReg("stats", new StatsCommand(this, statsManager));

        // Interaction Events
        eventReg(new InventoryCloseListener(guiManager));
        eventReg(new InterBlockListener(this, interactionManager));
        // Citem Events
        eventReg(new DropListener());
        eventReg(new HoverListener(data));
        eventReg(new UseListener(data));

        // Register inventory click listener
        InventoryInteraction inventoryInteraction = new InventoryInteraction(this, actionHandler);

        // Register InventoryClickListener for each interaction config loaded
        for (Map.Entry<String, InteractionConfig> entry : interactionConfigs.entrySet()) {
            InteractionConfig config = entry.getValue();
            getServer().getPluginManager().registerEvents(new InventoryClickListener(inventoryInteraction, config, guiManager), this);
        }

        interactionManager.loadAllInteractions();
        interactionManager.startParticleTask();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void eventReg(Listener l) {
        getServer().getPluginManager().registerEvents(l, this);
    }

    private void cmdReg(String cmd, CommandExecutor e) {
        this.getCommand(cmd).setExecutor(e);
    }

}