package net.creeperdev.sleepDeprivation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
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
        Figs figs = FigManager.FIGS;
        logger.info("Initialization complete.");
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            counter++;
            if (counter > nextInt(figs.interval, figs.interval + figs.intervalRandomness)) {
                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                    int awakeTime = player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));


                    if (awakeTime > figs.stageThreshold1 && awakeTime < figs.stageThreshold2) {
                        swapItems(player, figs.inventorySwapMultiplier1);
                        randomEffects(player, figs.potency1);
                    }
                    if (awakeTime > figs.stageThreshold2 && awakeTime < figs.stageThreshold3) {
                        swapItems(player, figs.inventorySwapMultiplier2);
                        randomEffects(player, figs.potency2);
                    }
                    if (awakeTime > figs.stageThreshold3 && awakeTime < figs.stageThreshold4) {
                        swapItems(player, figs.inventorySwapMultiplier3);
                        randomEffects(player, figs.potency3);
                    }
                    if (awakeTime > figs.stageThreshold4 && awakeTime < figs.stageThreshold5) {
                        swapItems(player, figs.inventorySwapMultiplier4);
                        randomEffects(player, figs.potency4);
                    }
                    if (awakeTime > figs.stageThreshold5) {
                        swapItems(player, figs.inventorySwapMultiplier5);
                        randomEffects(player, figs.potency5);

                    }
                }
                counter = 0;
            }
        });

    }

    public static void swapItems(ServerPlayer player, int times) {

        Figs figs = FigManager.FIGS;
        if (figs.modifyInventory) {
            int e = nextInt(0, (figs.inventorySwapMultiplier5*2)/times);
            for (int i = 0; i < times; i++) {
                if (e == 1) {
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
                    if (figs.includeHotbar) {
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
    public static void randomEffects(ServerPlayer player, int potency) {

        Figs figs = FigManager.FIGS;
        if (figs.modifyEffects) {
            if (potency == 1) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 40, 6, false ,false, false));
                }
            }
            if (potency == 2) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 8,false ,false, false));
                }
            }
            if (potency == 3) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 6,false ,false, false));
                } if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20, 0,false ,false, false));
                }
            }
            if (potency == 4) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 7,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 30, 0,false ,false, false));
                }
            }
            if (potency == 5) {
                int e = nextInt(1, 12);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 6,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 60, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 30, 0,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 10, 0,false ,false, false));
                }
            }
            if (potency == 6) {
                int e = nextInt(1, 12);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 8,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 2,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 8,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 1,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20, 0,false ,false, false));
                }
            }
            if (potency == 6) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 10,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 3,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 10,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 2,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
            }
            if (potency == 7) {
                int e = nextInt(1, 10);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 10,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 3,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 6,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 10,false ,false, false));
                }

                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 10,false ,false, false));
                }
            }
            if (potency == 8) {
                int e = nextInt(1, 9);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 10,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 0,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 10,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 0,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 10,false ,false, false));
                }
            }
            if (potency == 9) {
                int e = nextInt(1, 8);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 12,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 5,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 12,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 5,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 1,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 2,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 12,false ,false, false));
                }
            }if (potency == 10) {
                int e = nextInt(1, 5);
                if (e == 1) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 15,false ,false, false));
                }
                if (e == 2) {
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 5,false ,false, false));
                }
                if (e == 3) {
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 15,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60, 5,false ,false, false));
                }
                if (e == 4) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 2,false ,false, false));
                }
                if  (e == 5) {
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 30, 1,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 80, 2,false ,false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 120, 15,false ,false, false));
                }
            }
        }
    }
}
