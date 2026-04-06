package net.creeperdev.sleepDeprivation;

import net.creeperdev.sleepDeprivation.figManager.FigPacket;
import net.creeperdev.sleepDeprivation.figManager.FigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.apache.commons.lang3.RandomUtils.*;

public class SleepDeprivation implements ModInitializer {
    public static int counter = 0;
    public static final Logger logger = LoggerFactory.getLogger("Sleep Deprivation - Main");

    @Override
    public void onInitialize() {
        logger.info("Initializing...");
        FigManager.load();
        PayloadTypeRegistry.serverboundPlay().register(FigPacket.ID, FigPacket.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(FigPacket.ID, FigPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(FigPacket.ID, (payload, context) -> {
            context.server().execute(() -> {

                if (context.player().permissions().hasPermission(Permissions.COMMANDS_MODERATOR)) {
                    FigManager.load();

                    Figs f = FigManager.FIGS;
                    {
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
                        FigManager.save();
                    }
                    {
                        ServerPlayNetworking.send(context.player(),new FigPacket(
                            f.interval,
                            f.intervalRandomness,
                            f.modifyInventory,
                            f.stageThreshold1,
                            f.stageThreshold2,
                            f.stageThreshold3,
                            f.stageThreshold4,
                            f.stageThreshold5,
                            f.enableStageMessage,
                            f.stageMessage1,
                            f.stageMessage2,
                            f.stageMessage3,
                            f.stageMessage4,
                            f.stageMessage5,
                            f.inventorySwapMultiplier1,
                            f.inventorySwapMultiplier2,
                            f.inventorySwapMultiplier3,
                            f.inventorySwapMultiplier4,
                            f.inventorySwapMultiplier5,
                            f.includeHotbar,
                            f.modifyEffects,
                            f.potency1,
                            f.potency2,
                            f.potency3,
                            f.potency4,
                            f.potency5
                        ));
                    }
                    logger.warn("Figs for sleep_deprivation were modified by " + context.player().getPlainTextName());
                    context.player().sendSystemMessage(Component.literal("Updated Figs for sleep_deprivation successfully"));
                } else {
                    logger.error(context.player().getPlainTextName() + " attempted to modify figs without permission!");
                    context.player().sendSystemMessage(Component.literal("Failed to update figs, insufficient permissions"));

                }
            });
        });


        ServerTickEvents.END_SERVER_TICK.register(server -> {
            counter++;
            
            int time = nextInt(FigManager.FIGS.interval, FigManager.FIGS.interval + FigManager.FIGS.intervalRandomness);
            if (counter > time) {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {

                    int awakeTime = player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
                    if (FigManager.FIGS.enableStageMessage) {
                        if (awakeTime > FigManager.FIGS.stageThreshold1 - time && awakeTime < FigManager.FIGS.stageThreshold1 + time) {
                            player.sendSystemMessage(Component.literal(FigManager.FIGS.stageMessage1), true);
                        }
                        if (awakeTime > FigManager.FIGS.stageThreshold2 - time && awakeTime < FigManager.FIGS.stageThreshold2 + time) {
                            player.sendSystemMessage(Component.literal(FigManager.FIGS.stageMessage2), true);
                        }
                        if (awakeTime > FigManager.FIGS.stageThreshold3 - time && awakeTime < FigManager.FIGS.stageThreshold3 + time) {
                            player.sendSystemMessage(Component.literal(FigManager.FIGS.stageMessage3), true);
                        }
                        if (awakeTime > FigManager.FIGS.stageThreshold4 - time && awakeTime < FigManager.FIGS.stageThreshold4 + time) {
                            player.sendSystemMessage(Component.literal(FigManager.FIGS.stageMessage4), true);
                        }
                        if (awakeTime > FigManager.FIGS.stageThreshold5 - time && awakeTime < FigManager.FIGS.stageThreshold5 + time) {
                            player.sendSystemMessage(Component.literal(FigManager.FIGS.stageMessage5), true);
                        }
                    }
                    if (awakeTime > FigManager.FIGS.stageThreshold1 && awakeTime < FigManager.FIGS.stageThreshold2) {
                        swapItems(player, FigManager.FIGS.inventorySwapMultiplier1);
                        randomEffects(player, FigManager.FIGS.potency1);
                    }
                    if (awakeTime > FigManager.FIGS.stageThreshold2 && awakeTime < FigManager.FIGS.stageThreshold3) {
                        swapItems(player, FigManager.FIGS.inventorySwapMultiplier2);
                        randomEffects(player, FigManager.FIGS.potency2);
                    }
                    if (awakeTime > FigManager.FIGS.stageThreshold3 && awakeTime < FigManager.FIGS.stageThreshold4) {
                        swapItems(player, FigManager.FIGS.inventorySwapMultiplier3);
                        randomEffects(player, FigManager.FIGS.potency3);
                    }
                    if (awakeTime > FigManager.FIGS.stageThreshold4 && awakeTime < FigManager.FIGS.stageThreshold5) {
                        swapItems(player, FigManager.FIGS.inventorySwapMultiplier4);
                        randomEffects(player, FigManager.FIGS.potency4);
                    }
                    if (awakeTime > FigManager.FIGS.stageThreshold5) {
                        swapItems(player, FigManager.FIGS.inventorySwapMultiplier5);
                        randomEffects(player, FigManager.FIGS.potency5);

                    }
                }
                counter = 0;
            }
        });
        ServerPlayerEvents.JOIN.register(player -> {
            Figs f = FigManager.FIGS;
            ServerPlayNetworking.send(player,new FigPacket(
                f.interval,
                f.intervalRandomness,
                f.modifyInventory,
                f.stageThreshold1,
                f.stageThreshold2,
                f.stageThreshold3,
                f.stageThreshold4,
                f.stageThreshold5,
                f.enableStageMessage,
                f.stageMessage1,
                f.stageMessage2,
                f.stageMessage3,
                f.stageMessage4,
                f.stageMessage5,
                f.inventorySwapMultiplier1,
                f.inventorySwapMultiplier2,
                f.inventorySwapMultiplier3,
                f.inventorySwapMultiplier4,
                f.inventorySwapMultiplier5,
                f.includeHotbar,
                f.modifyEffects,
                f.potency1,
                f.potency2,
                f.potency3,
                f.potency4,
                f.potency5
            ));
        });
        logger.info("Initialization complete.");


    }

    public static void swapItems(ServerPlayer player, int times) {



        if (FigManager.FIGS.modifyInventory) {
            int e = nextInt(0, 5);
            for (int i = 0; i < times; i++) {
                if (nextInt(0,5) == 0) {
                    if (e == 1 || e == 2) {
                        Inventory inventory = player.getInventory();
                        int slotA = nextInt(9, 35);
                        ItemStack itemStackA = inventory.getItem(slotA);
                        int slotB = nextInt(9, 35);
                        ItemStack itemStackB = inventory.getItem(slotB);
                        if (slotA != slotB) {
                            inventory.setItem(slotA, ItemStack.EMPTY);
                            inventory.setItem(slotB, ItemStack.EMPTY);
                            inventory.setItem(slotA, itemStackB);
                            inventory.setItem(slotB, itemStackA);
                        }
                        inventory.setChanged();
                    }
                    if (e == 0) {
                        if (FigManager.FIGS.includeHotbar) {
                            Inventory inventory = player.getInventory();
                            int slotA = nextInt(0, 8);
                            ItemStack itemStackA = inventory.getItem(slotA);
                            int slotB = nextInt(0, 8);
                            ItemStack itemStackB = inventory.getItem(slotB);
                            if (slotA != slotB) {
                                inventory.setItem(slotA, ItemStack.EMPTY);
                                inventory.setItem(slotB, ItemStack.EMPTY);
                                inventory.setItem(slotA, itemStackB);
                                inventory.setItem(slotB, itemStackA);
                            }
                            inventory.setChanged();
                        } else {
                            Inventory inventory = player.getInventory();
                            int slotA = nextInt(9, 35);
                            ItemStack itemStackA = inventory.getItem(slotA);
                            int slotB = nextInt(9, 35);
                            ItemStack itemStackB = inventory.getItem(slotB);
                            if (slotA != slotB) {
                                inventory.setItem(slotA, ItemStack.EMPTY);
                                inventory.setItem(slotB, ItemStack.EMPTY);
                                inventory.setItem(slotA, itemStackB);
                                inventory.setItem(slotB, itemStackA);
                            }
                            inventory.setChanged();
                        }
                    }
                }
            }
        }
    }
    public static void randomEffects(ServerPlayer player, int potency) {

        if (FigManager.FIGS.modifyEffects) {
            if (potency == 1) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 6, false ,false, false));
                }
            }
            if (potency == 2) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 8,false ,false, false));
                }
            }
            if (potency == 3) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 6,false ,false, false));
                } if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 0,false ,false, false));
                }
            }
            if (potency == 4) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 7,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 30, 0,false ,false, false));
                }
            }
            if (potency == 5) {
                int e = nextInt(1, 12);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0,false ,false, false));
                }
            }
            if (potency == 6) {
                int e = nextInt(1, 12);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 2,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0,false ,false, false));
                }
            }
            if (potency == 6) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 3,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 2,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
            }
            if (potency == 7) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 3,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 10,false ,false, false));
                }

                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 6,false ,false, false));
                }
            }
            if (potency == 8) {
                int e = nextInt(1, 9);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140, 6,false ,false, false));
                }
            }
            if (potency == 9) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 180, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 5,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 180, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 5,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 2,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 160, 6,false ,false, false));
                }
            }if (potency == 10) {
                int e = nextInt(1, 5);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 10,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 5,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 10,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 5,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 2,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 2,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 200, 10,false ,false, false));
                }
            }
        }
    }
}
