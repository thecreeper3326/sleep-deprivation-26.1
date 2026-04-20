package net.creeperdev.sleepDeprivation.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.creeperdev.figManager.FigManager;
import net.creeperdev.figManagerClient.FigScreen;
import net.minecraft.network.chat.Component;

import static net.creeperdev.figManagerClient.FigManagerClient.ow;

public class ModMenuInit implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return p -> new FigScreen<>(Component.literal(FigManager.getName()), ow, FigManager.FIGS, p);
    }
}

