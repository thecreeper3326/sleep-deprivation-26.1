package net.creeperdev.sleepDeprivation;

import net.creeperdev.figManager.FigManager;
import net.creeperdev.figManager.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomUtils.*;

public class SleepDeprivation implements ModInitializer {
    public static int counter = 0;
    public static final Logger logger = LoggerFactory.getLogger("Sleep Deprivation - Main");
    record Stage(Field threshold, Field message, Field potency, Field inventory) {}
    private static final Map<Integer, Stage> stages = new LinkedHashMap<>();

    public static void initCache() {
        Class<?> j = Figs.instance.getClass();

        for (int i = 1; i <= 10; i++) {
            try {
                stages.put(i, new Stage(j.getDeclaredField("stageThreshold" + i), j.getDeclaredField("stageMessage" + i), j.getDeclaredField("potency" + i), j.getDeclaredField("inventorySwapMultiplier" + i)));
                stages.get(i).threshold().setAccessible(true);
                stages.get(i).message().setAccessible(true);
                stages.get(i).potency().setAccessible(true);
                stages.get(i).inventory().setAccessible(true);
            } catch (NoSuchFieldException e) {
                logger.error("Smth didnt go too well while processing stage " + i);
            }
        }
    }
    @Override
    public void onInitialize() {
        logger.info("Initializing...");
        FigManager e = new FigManager();
        initCache();
        e.init("sleep_deprivation", "2.4", Figs.instance);

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            counter++;
            Figs f = Figs.instance;
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                int awakeTime = player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));

                for (var entry : stages.entrySet()) {
                    Stage fields = entry.getValue();
                    try {
                        int threshold = ((IntFig) fields.threshold().get(f)).value;
                        if (threshold > -1) {
                            if (awakeTime == threshold && f.enableStageMessage.value) {
                                String msg = ((StringFig) fields.message().get(f)).value;
                                player.sendSystemMessage(Component.literal(msg), true);
                            }
                            if (counter > nextInt(f.interval.value, f.interval.value + f.intervalRandomness.value)) {
                                if (awakeTime >= threshold) {
                                    Stage nextFields = stages.get(entry.getKey() + 1);
                                    boolean isLastStage = (nextFields == null);

                                    int nextThreshold = isLastStage ? Integer.MAX_VALUE : ((IntFig) nextFields.threshold().get(f)).value;

                                    if (awakeTime < nextThreshold) {
                                        if (f.modifyEffects.value) {
                                            randomEffects(player, ((IntFig) fields.potency().get(f)).value);
                                        }
                                        if (f.modifyInventory.value) {
                                            swapItems(player, ((IntFig) fields.inventory().get(f)).value);
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IllegalAccessException oops) {
                        logger.error("oops:");
                        logger.error(oops.getMessage());
                    }
                }
            }
            if (counter > f.interval.value + f.intervalRandomness.value) {
                counter = 0;
            }
        });

        logger.info("Initialization complete.");
    }
    public static void swapItems(ServerPlayer player, int times) {
        Figs f = (Figs) FigManager.FIGS;
        if (f.modifyInventory.value) {
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
                        if (f.includeHotbar.value) {
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
        Figs f = (Figs) FigManager.FIGS;
        if (f.modifyEffects.value) {
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
