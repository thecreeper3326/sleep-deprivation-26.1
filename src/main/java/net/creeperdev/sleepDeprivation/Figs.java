package net.creeperdev.sleepDeprivation;

import net.creeperdev.figManager.*;
import net.minecraft.ChatFormatting;

public class Figs {
    public static Figs instance = new Figs();
    public DividerFig general = new DividerFig("General settings", ChatFormatting.WHITE, true, false, false);
    public DividerFig note = new DividerFig("Note : tiredness is the amount of ticks you've gone without sleep.", ChatFormatting.GRAY, false, false, false);
    public DividerFig note2 = new DividerFig("another note: you can disable a stage completely by setting its threshold to -1", ChatFormatting.GRAY, false, false, false);
    public IntFig interval = new IntFig("Interval", "Ticks between random events", 60, 0, Integer.MAX_VALUE/2);
    public IntFig intervalRandomness = new IntFig("Interval Randomness", "Variation in the interval", 100, 0, Integer.MAX_VALUE/2);
    public BooleanFig modifyInventory = new BooleanFig("Modify Inventory", "Enable inventory modifications", true);
    public BooleanFig modifyEffects = new BooleanFig("Modify Effects", "Enable status effect modifications", true);
    public BooleanFig includeHotbar = new BooleanFig("Include Hotbar", "Inventory actions affect the hotbar", true);
    public BooleanFig enableStageMessage = new BooleanFig("Enable Messages", "Show actionbar message on stage increase", true);

    public DividerFig s1 = new DividerFig("Stage 1", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold1 = new IntFig("Threshold", "Tiredness required for Stage 1", 12000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage1 = new StringFig("Message", "Message for stage 1", "You feel a bit tired...", 128);
    public IntFig inventorySwapMultiplier1 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 1", 1, 0, 100);
    public IntFig potency1 = new IntFig("Effect Potency", "Effect strength for stage 1", 1, 0, 10);

    public DividerFig s2 = new DividerFig("Stage 2", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold2 = new IntFig("Threshold", "Tiredness required for Stage 2", 24000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage2 = new StringFig("Message", "Message for stage 2", "You think about resting...", 128);
    public IntFig inventorySwapMultiplier2 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 2", 2, 0, 100);
    public IntFig potency2 = new IntFig("Effect Potency", "Effect strength for stage 2", 2, 0, 10);

    public DividerFig s3 = new DividerFig("Stage 3", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold3 = new IntFig("Threshold", "Tiredness required for Stage 3", 32000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage3 = new StringFig("Message", "Message for stage 3", "You are feeling quite tired...", 128);
    public IntFig inventorySwapMultiplier3 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 3", 3, 0, 100);
    public IntFig potency3 = new IntFig("Effect Potency", "Effect strength for stage 3", 3, 0, 10);

    public DividerFig s4 = new DividerFig("Stage 4", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold4 = new IntFig("Threshold", "Tiredness required for Stage 4", 50000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage4 = new StringFig("Message", "Message for stage 4", "You are desperate for rest...", 128);
    public IntFig inventorySwapMultiplier4 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 4", 4, 0, 100);
    public IntFig potency4 = new IntFig("Effect Potency", "Effect strength for stage 4", 4, 0, 10);

    public DividerFig s5 = new DividerFig("Stage 5", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold5 = new IntFig("Threshold", "Tiredness required for Stage 5", 80000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage5 = new StringFig("Message", "Message for stage 5", "Your tiredness drives you to insanity...", 128);
    public IntFig inventorySwapMultiplier5 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 5", 8, 0, 100);
    public IntFig potency5 = new IntFig("Effect Potency", "Effect strength for stage 5", 10, 0, 10);

    public DividerFig s6 = new DividerFig("Stage 6", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold6 = new IntFig("Threshold", "Tiredness required for Stage 6", 120000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage6 = new StringFig("Message", "Message for stage 6", "", 128);
    public IntFig inventorySwapMultiplier6 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 6", 0, 0, 100);
    public IntFig potency6 = new IntFig("Effect Potency", "Effect strength for stage 6", 0, 0, 100);

    public DividerFig s7 = new DividerFig("Stage 7", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold7 = new IntFig("Threshold", "Tiredness required for Stage 7", 180000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage7 = new StringFig("Message", "Message for stage 7", "", 128);
    public IntFig inventorySwapMultiplier7 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 7", 0, 0, 100);
    public IntFig potency7 = new IntFig("Effect Potency", "Effect strength for stage 7", 0, 0, 100);

    public DividerFig s8 = new DividerFig("Stage 8", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold8 = new IntFig("Threshold", "Tiredness required for Stage 8", 260000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage8 = new StringFig("Message", "Message for stage 8", "", 128);
    public IntFig inventorySwapMultiplier8 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 8", 0, 0, 100);
    public IntFig potency8 = new IntFig("Effect Potency", "Effect strength for stage 8", 0, 0, 100);

    public DividerFig s9 = new DividerFig("Stage 9", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold9 = new IntFig("Threshold", "Tiredness required for Stage 9", 350000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage9 = new StringFig("Message", "Message for stage 9", "", 128);
    public IntFig inventorySwapMultiplier9 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 9", 0, 0, 100);
    public IntFig potency9 = new IntFig("Effect Potency", "Effect strength for stage 9", 0, 0, 100);

    public DividerFig s10 = new DividerFig("Stage 10", ChatFormatting.WHITE, true, false, false);
    public IntFig stageThreshold10 = new IntFig("Threshold", "Tiredness required for Stage 10", 500000, -1, Integer.MAX_VALUE);
    public StringFig stageMessage10 = new StringFig("Message", "Message for stage 10", "", 128);
    public IntFig inventorySwapMultiplier10 = new IntFig("Inventory Multiplier", "Inventory modification intensity for stage 10", 0, 0, 100);
    public IntFig potency10 = new IntFig("Effect Potency", "Effect strength for stage 10", 0, 0, 100);
}