package net.creeperdev.sleepDeprivation.client;

import net.creeperdev.figManagerClient.FigManagerClient;
import net.creeperdev.sleepDeprivation.Figs;
import net.fabricmc.api.ClientModInitializer;

public class SleepDeprivationClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FigManagerClient figManagerClient = new FigManagerClient();
        figManagerClient.init(Figs.instance);
    }
}
