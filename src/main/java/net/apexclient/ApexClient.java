package net.apexclient;

import net.apexclient.modules.ModuleManager;
import net.apexclient.modules.impl.farming.AutoPests;
import net.apexclient.ui.ClickGuiScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApexClient implements ModInitializer
{
    public static final String VERSION = "0.1-dev";

    public static final Logger LOGGER = LoggerFactory.getLogger("apexclient");

    public static final KeyMapping EXAMPLE_KEYBINDING = new KeyMapping(
            "key.apexclient.gui",
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            KeyMapping.Category.MISC
    );

    public static final ModuleManager moduleManager  = new ModuleManager();

    @Override
    public void onInitialize()
    {
        LOGGER.info("Hello Fabric world!");

        // Ingame example with ImGui, also see GameRendererMixin
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (EXAMPLE_KEYBINDING.consumeClick())
            {
                client.setScreen(new ClickGuiScreen());
            }
        });

        // farming
        moduleManager.addModule(new AutoPests());
    }
}
