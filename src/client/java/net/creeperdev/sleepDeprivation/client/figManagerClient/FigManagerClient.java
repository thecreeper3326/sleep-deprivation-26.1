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

            Figs.interval = payload.interval();
            Figs.intervalRandomness = payload.intervalRandomness();
            Figs.modifyInventory = payload.modifyInventory();
            Figs.stageThreshold1 = payload.stageThreshold1();
            Figs.stageThreshold2 = payload.stageThreshold2();
            Figs.stageThreshold3 = payload.stageThreshold3();
            Figs.stageThreshold4 = payload.stageThreshold4();
            Figs.stageThreshold5 = payload.stageThreshold5();
            Figs.enableStageMessage = payload.enableStageMessage();
            Figs.stageMessage1 = String.valueOf(payload.stageMessage1());
            Figs.stageMessage2 = String.valueOf(payload.stageMessage2());
            Figs.stageMessage3 = String.valueOf(payload.stageMessage3());
            Figs.stageMessage4 = String.valueOf(payload.stageMessage4());
            Figs.stageMessage5 = String.valueOf(payload.stageMessage5());
            Figs.inventorySwapMultiplier1 = payload.inventorySwapMultiplier1();
            Figs.inventorySwapMultiplier2 = payload.inventorySwapMultiplier2();
            Figs.inventorySwapMultiplier3 = payload.inventorySwapMultiplier3();
            Figs.inventorySwapMultiplier4 = payload.inventorySwapMultiplier4();
            Figs.inventorySwapMultiplier5 = payload.inventorySwapMultiplier5();
            Figs.includeHotbar = payload.includeHotbar();
            Figs.modifyEffects = payload.modifyEffects();
            Figs.potency1 = payload.potency1();
            Figs.potency2 = payload.potency2();
            Figs.potency3 = payload.potency3();
            Figs.potency4 = payload.potency4();
            Figs.potency5 = payload.potency5();
        }));

    }
}
