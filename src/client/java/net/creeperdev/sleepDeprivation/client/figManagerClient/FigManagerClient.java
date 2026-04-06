package net.creeperdev.sleepDeprivation.client.figManagerClient;

import net.creeperdev.sleepDeprivation.figManager.FigPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;

public class FigManagerClient implements ClientModInitializer {
    public Figs f = new Figs();
    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommands.literal("sleepdeprivation_config").executes(context -> {
                Minecraft client = context.getSource().getClient();
                client.execute(() -> {

                    client.setScreen(new FigScreen(Component.literal("Fig")));
                });
                return 1;
            }).requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_MODERATOR)));
        });
        ClientPlayNetworking.registerGlobalReceiver(FigPacket.ID, ((payload, context) -> {

            f.interval = payload.interval();
            f.intervalRandomness = payload.intervalRandomness();
            f.modifyInventory = payload.modifyInventory();
            f.stageThreshold1 = payload.stageThreshold1();
            f.stageThreshold2 = payload.stageThreshold2();
            f.stageThreshold3 = payload.stageThreshold3();
            f.stageThreshold4 = payload.stageThreshold4();
            f.stageThreshold5 = payload.stageThreshold5();
            f.enableStageMessage = payload.enableStageMessage();
            f.stageMessage1 = payload.stageMessage1();
            f.stageMessage2 = payload.stageMessage2();
            f.stageMessage3 = payload.stageMessage3();
            f.stageMessage4 = payload.stageMessage4();
            f.stageMessage5 = payload.stageMessage5();
            f.inventorySwapMultiplier1 = payload.inventorySwapMultiplier1();
            f.inventorySwapMultiplier2 = payload.inventorySwapMultiplier2();
            f.inventorySwapMultiplier3 = payload.inventorySwapMultiplier3();
            f.inventorySwapMultiplier4 = payload.inventorySwapMultiplier4();
            f.inventorySwapMultiplier5 = payload.inventorySwapMultiplier5();
            f.includeHotbar = payload.includeHotbar();
            f.modifyEffects = payload.modifyEffects();
            f.potency1 = payload.potency1();
            f.potency2 = payload.potency2();
            f.potency3 = payload.potency3();
            f.potency4 = payload.potency4();
            f.potency5 = payload.potency5();
        }));

    }
}
