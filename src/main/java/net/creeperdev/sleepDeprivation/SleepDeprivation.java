package net.creeperdev.sleepDeprivation;

import net.creeperdev.sleepDeprivation.figManager.FigPacket;
import net.creeperdev.sleepDeprivation.figManager.FigManager;
import net.creeperdev.sleepDeprivation.figManager.Figs;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
                    int valid = 0;
                    int invalid = 0;
                    if (payload.interval() >= 1) {
                        f.interval = payload.interval();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: interval"));
                        logger.error("Malformed value: interval");
                    }

                    if (payload.intervalRandomness() >= 0) {
                        f.intervalRandomness = payload.intervalRandomness();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: intervalRandomness"));
                        logger.error("Malformed value: intervalRandomness");
                    }

                    if (payload.stageThreshold1() >= 0) {
                        f.stageThreshold1 = payload.stageThreshold1();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageThreshold1"));
                        logger.error("Malformed value: stageThreshold1");
                    }
                    if (payload.stageThreshold2() >= 0) {
                        f.stageThreshold2 = payload.stageThreshold2();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageThreshold2"));
                        logger.error("Malformed value: stageThreshold2");
                    }
                    if (payload.stageThreshold3() >= 0) {
                        f.stageThreshold3 = payload.stageThreshold3();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageThreshold3"));
                        logger.error("Malformed value: stageThreshold3");
                    }
                    if (payload.stageThreshold4() >= 0) {
                        f.stageThreshold4 = payload.stageThreshold4();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageThreshold4"));
                        logger.error("Malformed value: stageThreshold4");
                    }
                    if (payload.stageThreshold5() >= 0) {
                        f.stageThreshold5 = payload.stageThreshold5();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageThreshold5"));
                        logger.error("Malformed value: stageThreshold5");
                    }

                    if (payload.inventorySwapMultiplier1() >= 0) {
                        f.inventorySwapMultiplier1 = payload.inventorySwapMultiplier1();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: inventorySwapMultiplier1"));
                        logger.error("Malformed value: inventorySwapMultiplier1");
                    }
                    if (payload.inventorySwapMultiplier2() >= 0) {
                        f.inventorySwapMultiplier2 = payload.inventorySwapMultiplier2();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: inventorySwapMultiplier2"));
                        logger.error("Malformed value: inventorySwapMultiplier2");
                    }
                    if (payload.inventorySwapMultiplier3() >= 0) {
                        f.inventorySwapMultiplier3 = payload.inventorySwapMultiplier3();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: inventorySwapMultiplier3"));
                        logger.error("Malformed value: inventorySwapMultiplier3");
                    }
                    if (payload.inventorySwapMultiplier4() >= 0) {
                        f.inventorySwapMultiplier4 = payload.inventorySwapMultiplier4();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: inventorySwapMultiplier4"));
                        logger.error("Malformed value: inventorySwapMultiplier4");
                    }
                    if (payload.inventorySwapMultiplier5() >= 0) {
                        f.inventorySwapMultiplier5 = payload.inventorySwapMultiplier5();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: inventorySwapMultiplier5"));
                        logger.error("Malformed value: inventorySwapMultiplier5");
                    }

                    if (payload.stageMessage1() != null && payload.stageMessage1().length() < 512) {
                        f.stageMessage1 = payload.stageMessage1();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageMessage1"));
                        logger.error("Malformed value: stageMessage1");
                    }
                    if (payload.stageMessage2() != null && payload.stageMessage2().length() < 512) {
                        f.stageMessage2 = payload.stageMessage2();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageMessage2"));
                        logger.error("Malformed value: stageMessage2");
                    }
                    if (payload.stageMessage3() != null && payload.stageMessage3().length() < 512) {
                        f.stageMessage3 = payload.stageMessage3();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageMessage3"));
                        logger.error("Malformed value: stageMessage3");
                    }
                    if (payload.stageMessage4() != null && payload.stageMessage4().length() < 512) {
                        f.stageMessage4 = payload.stageMessage4();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageMessage4"));
                        logger.error("Malformed value: stageMessage4");
                    }
                    if (payload.stageMessage5() != null && payload.stageMessage5().length() < 512) {
                        f.stageMessage5 = payload.stageMessage5();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: stageMessage5"));
                        logger.error("Malformed value: stageMessage5");
                    }

                    if (payload.potency1() >= 0 && payload.potency1() <= 10) {
                        f.potency1 = payload.potency1();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: potency1"));
                        logger.error("Malformed value: potency1");
                    }
                    if (payload.potency2() >= 0 && payload.potency2() <= 10) {
                        f.potency2 = payload.potency2();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: potency2"));
                        logger.error("Malformed value: potency2");
                    }
                    if (payload.potency3() >= 0 && payload.potency3() <= 10) {
                        f.potency3 = payload.potency3();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: potency3"));
                        logger.error("Malformed value: potency3");
                    }
                    if (payload.potency4() >= 0 && payload.potency4() <= 10) {
                        f.potency4 = payload.potency4();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: potency4"));
                        logger.error("Malformed value: potency4");
                    }
                    if (payload.potency5() >= 0 && payload.potency5() <= 10) {
                        f.potency5 = payload.potency5();
                        valid++;
                    } else {
                        invalid++;
                        context.player().sendSystemMessage(Component.literal("Malformed value: potency5"));
                        logger.error("Malformed value: potency5");
                    }
                    f.modifyInventory = payload.modifyInventory();
                    valid++;
                    f.includeHotbar = payload.includeHotbar();
                    valid++;
                    f.modifyEffects = payload.modifyEffects();
                    valid++;
                    f.enableStageMessage = payload.enableStageMessage();
                    valid++;
                    FigManager.save();

                    for (ServerPlayer player : context.server().getPlayerList().getPlayers()) {
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
                    }
                    logger.warn("Figs for sleep_deprivation were modified by " + context.player().getPlainTextName());
                    logger.warn(valid + " succeeded, " + invalid + " failed.");
                    context.player().sendSystemMessage(Component.literal("Updated Figs for sleep_deprivation" +". " + valid + " succeeded, " + invalid + " failed."));
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
