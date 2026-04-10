package net.creeperdev.sleepDeprivation.client.figManagerClient;

import net.creeperdev.sleepDeprivation.figManager.FigPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;


public class FigScreen extends Screen {

    public FigScreen(Component title) {
        super(title);

    }
    private OptionsList list;

    protected void init() {


        EditBox interval = new EditBox(font,10,50,((width-40)/8),20,Component.literal("e"));
        interval.setHint(Component.literal("Interval"));
        interval.setValue(String.valueOf(Figs.interval));
        interval.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that will pass between the random events")));

        EditBox intervalRandomness = new EditBox(font,(((width*2)/8)-(width)/8)+10,50,((width-40)/8)+10,20,Component.literal("e"));
        intervalRandomness.setHint(Component.literal("Randomness"));
        intervalRandomness.setValue(String.valueOf(Figs.intervalRandomness));
        intervalRandomness.setTooltip(Tooltip.create(Component.literal("The amount of deviation from the set interval")));

        EditBox threshold1 = new EditBox(font,10,100,((width-40)/8),20,Component.literal("e"));
        threshold1.setHint(Component.literal("Threshold 1"));
        threshold1.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that is required to pass before activating this stage")));

        EditBox threshold2 = new EditBox(font,10,120,((width-40)/8),20,Component.literal("e"));
        threshold2.setHint(Component.literal("Threshold 2"));
        threshold2.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that is required to pass before activating this stage")));

        EditBox threshold3 = new EditBox(font,10,140,((width-40)/8),20,Component.literal("e"));
        threshold3.setHint(Component.literal("Threshold 3"));
        threshold3.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that is required to pass before activating this stage")));

        EditBox threshold4 = new EditBox(font, 10, 160, ((width-40)/8), 20,Component.literal("e"));
        threshold4.setHint(Component.literal("Threshold 4"));
        threshold4.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that is required to pass before activating this stage")));

        EditBox threshold5 = new EditBox(font,10,180,((width-40)/8),20,Component.literal("e"));
        threshold5.setHint(Component.literal("Threshold 5"));
        threshold5.setTooltip(Tooltip.create(Component.literal("The amount of time (in ticks) that is required to pass before activating this stage")));


        EditBox inventory1 = new EditBox(font,(((width*2)/8)-(width)/8)+10,100,((width-40)/8)+5,20,Component.literal("e"));
        inventory1.setHint(Component.literal("Intensity 1"));
        inventory1.setTooltip(Tooltip.create(Component.literal("The intensity of inventory modification in the first stage")));

        EditBox inventory2 = new EditBox(font, (((width*2)/8)-(width)/8)+10, 120, ((width-40)/8)+5, 20,Component.literal("e"));
        inventory2.setHint(Component.literal("Intensity 2"));
        inventory2.setTooltip(Tooltip.create(Component.literal("The intensity of inventory modification in the second stage")));

        EditBox inventory3 = new EditBox(font, (((width*2)/8)-(width)/8)+10, 140, ((width-40)/8)+5, 20,Component.literal("e"));
        inventory3.setHint(Component.literal("Intensity 3"));
        inventory3.setTooltip(Tooltip.create(Component.literal("The intensity of inventory modification in the third stage")));

        EditBox inventory4 = new EditBox(font, (((width*2)/8)-(width)/8)+10, 160, ((width-40)/8)+5, 20,Component.literal("e"));
        inventory4.setHint(Component.literal("Intensity 4"));
        inventory4.setTooltip(Tooltip.create(Component.literal("The intensity of inventory modification in the fourth stage")));

        EditBox inventory5 = new EditBox(font, (((width*2)/8)-(width)/8)+10, 180, ((width-40)/8)+5, 20,Component.literal("e"));
        inventory5.setHint(Component.literal("Intensity 5"));
        inventory5.setTooltip(Tooltip.create(Component.literal("The intensity of inventory modification in the fifth stage")));



        Checkbox modifyHotbar = Checkbox.builder(Component.literal("Include Hotbar"), font).selected(Figs.includeHotbar).build();
        modifyHotbar.setX(120);
        modifyHotbar.setY(205);
        modifyHotbar.setHeight(15);
        modifyHotbar.setTooltip(Tooltip.create(Component.literal("Tells whether the mod is allowed to modify the hotbar of players in addition to their inventories")));
        

        Checkbox modifyInventory = Checkbox.builder(Component.literal("Modify Inventory"), font)
                .onValueChange((checkbox, value) -> {
                    if (value) {

                        modifyHotbar.active = true;
                        modifyHotbar.setAlpha(1F);
                        modifyHotbar.setTooltip(Tooltip.create(Component.literal("Tells whether the mod is allowed to modify the hotbar of players in addition to their inventories")));

                    }  else {
                        modifyHotbar.active = false;
                        modifyHotbar.setAlpha(0.5F);
                        modifyHotbar.setTooltip(Tooltip.create(Component.literal("Enable inventory modification to include hotbar")));
                    }
                })
                .selected(Figs.modifyInventory)
                .build();
        modifyInventory.setX(10);
        modifyInventory.setY(205);
        modifyInventory.setHeight(15);
        modifyInventory.setTooltip(Tooltip.create(Component.literal("Tells whether the mod is allowed to modify the inventories of players")));


        EditBox effects1 = new EditBox(font,(((width*3)/8)-(width)/8)+13,100,((width-40)/8)+5,20,Component.literal("e"));
        effects1.setHint(Component.literal("Effects 1"));
        effects1.setTooltip(Tooltip.create(Component.literal("The intenstiy of effects modification in the first stage")));

        EditBox effects2 = new EditBox(font, (((width*3)/8)-(width)/8)+13, 120, ((width-40)/8)+5, 20,Component.literal("e"));
        effects2.setHint(Component.literal("Effects 2"));
        effects2.setTooltip(Tooltip.create(Component.literal("The intensity of effects modification in the second stage")));

        EditBox effects3 = new EditBox(font, (((width*3)/8)-(width)/8)+13, 140, ((width-40)/8)+5, 20,Component.literal("e"));
        effects3.setHint(Component.literal("Effects 3"));
        effects3.setTooltip(Tooltip.create(Component.literal("The intensity of effects modification in the third stage")));

        EditBox effects4 = new EditBox(font, (((width*3)/8)-(width)/8)+13, 160, ((width-40)/8)+5, 20,Component.literal("e"));
        effects4.setHint(Component.literal("Effects 4"));
        effects4.setTooltip(Tooltip.create(Component.literal("The intensity of effects modification in the fourth stage")));

        EditBox effects5 = new EditBox(font, (((width*3)/8)-(width)/8)+13, 180, ((width-40)/8)+5, 20,Component.literal("e"));
        effects5.setHint(Component.literal("Effects 5"));
        effects5.setTooltip(Tooltip.create(Component.literal("The intensity of effects modification in the fifth stage")));


        Checkbox modifyeffects = Checkbox.builder(Component.literal("Modify effects"), font).selected(Figs.modifyEffects).build();
        modifyeffects.setX(220);
        modifyeffects.setY(205);
        modifyeffects.setHeight(15);
        modifyeffects.setTooltip(Tooltip.create(Component.literal("Tells whether the mod is allowed to modify the status effects of players. 0 = off, 1 = mild, 10 = insanity")));
        
        
        EditBox Message1 = new EditBox(font,(((width*4)/8)-(width)/8)+18,100, (int) (((width)/1.8)),20,Component.literal("e"));
        Message1.setHint(Component.literal("Message 1"));
        Message1.setTooltip(Tooltip.create(Component.literal("The message for the first stage")));

        EditBox Message2 = new EditBox(font, (((width*4)/8)-(width)/8)+18, 120, (int) (((width)/1.8)), 20,Component.literal("e"));
        Message2.setHint(Component.literal("Message 2"));
        Message2.setTooltip(Tooltip.create(Component.literal("The message for the second stage")));

        EditBox Message3 = new EditBox(font, (((width*4)/8)-(width)/8)+18, 140, (int) (((width)/1.8)), 20,Component.literal("e"));
        Message3.setHint(Component.literal("Message 3"));
        Message3.setTooltip(Tooltip.create(Component.literal("The message for the third stage")));

        EditBox Message4 = new EditBox(font, (((width*4)/8)-(width)/8)+18, 160, (int) (((width)/1.8)), 20,Component.literal("e"));
        Message4.setHint(Component.literal("Message 4"));
        Message4.setTooltip(Tooltip.create(Component.literal("The message for the fourth stage")));

        EditBox Message5 = new EditBox(font, (((width*4)/8)-(width)/8)+18, 180, (int) (((width)/1.8)), 20,Component.literal("e"));
        Message5.setHint(Component.literal("Message 5"));
        Message5.setTooltip(Tooltip.create(Component.literal("The message for the fifth stage")));

        Checkbox modifyMessage = Checkbox.builder(Component.literal("Send Message"), font).selected(Figs.enableStageMessage).build();
        modifyMessage.setX(320);
        modifyMessage.setY(205);
        modifyMessage.setHeight(15);
        modifyMessage.setTooltip(Tooltip.create(Component.literal("Tells whether the mod should alert players when they pass a threshold")));

        

        Message1.setMaxLength(64);
        Message2.setMaxLength(64);
        Message3.setMaxLength(64);
        Message4.setMaxLength(64);
        Message5.setMaxLength(64);

        Button apply = Button.builder(Component.literal("Apply Changes"), (btn) -> {
            this.onClose();
            ClientPlayNetworking.send(
                new FigPacket(
                    Integer.parseInt(interval.getValue()),
                    Integer.parseInt(intervalRandomness.getValue()),
                    modifyInventory.selected(),
                    Integer.parseInt(threshold1.getValue()),
                    Integer.parseInt(threshold2.getValue()),
                    Integer.parseInt(threshold3.getValue()),
                    Integer.parseInt(threshold4.getValue()),
                    Integer.parseInt(threshold5.getValue()),
                    modifyMessage.selected(),
                    Message1.getValue(),
                    Message2.getValue(),
                    Message3.getValue(),
                    Message4.getValue(),
                    Message5.getValue(),
                    Integer.parseInt(inventory1.getValue()),
                    Integer.parseInt(inventory2.getValue()),
                    Integer.parseInt(inventory3.getValue()),
                    Integer.parseInt(inventory4.getValue()),
                    Integer.parseInt(inventory5.getValue()),
                    modifyHotbar.selected(),
                    modifyeffects.selected(),
                    Integer.parseInt(effects1.getValue()),
                    Integer.parseInt(effects2.getValue()),
                    Integer.parseInt(effects3.getValue()),
                    Integer.parseInt(effects4.getValue()),
                    Integer.parseInt(effects5.getValue())
                )
            );
                    
                    
        }).bounds(width-130, 10, 120, 20).build();

        Button close = Button.builder(Component.literal("Discard"), (btn) -> {
            this.onClose();
        }).bounds(width-255, 10, 120, 20).build();
        {
            threshold1.setValue(String.valueOf(Figs.stageThreshold1));
            threshold2.setValue(String.valueOf(Figs.stageThreshold2));
            threshold3.setValue(String.valueOf(Figs.stageThreshold3));
            threshold4.setValue(String.valueOf(Figs.stageThreshold4));
            threshold5.setValue(String.valueOf(Figs.stageThreshold5));
            inventory1.setValue(String.valueOf(Figs.inventorySwapMultiplier1));
            inventory2.setValue(String.valueOf(Figs.inventorySwapMultiplier2));
            inventory3.setValue(String.valueOf(Figs.inventorySwapMultiplier3));
            inventory4.setValue(String.valueOf(Figs.inventorySwapMultiplier4));
            inventory5.setValue(String.valueOf(Figs.inventorySwapMultiplier5));
            effects1.setValue(String.valueOf(Figs.potency1));
            effects2.setValue(String.valueOf(Figs.potency2));
            effects3.setValue(String.valueOf(Figs.potency3));
            effects4.setValue(String.valueOf(Figs.potency4));
            effects5.setValue(String.valueOf(Figs.potency5));
            Message1.setValue(Figs.stageMessage1);
            Message2.setValue(Figs.stageMessage2);
            Message3.setValue(Figs.stageMessage3);
            Message4.setValue(Figs.stageMessage4);
            Message5.setValue(Figs.stageMessage5);
        }

        StringWidget title = new StringWidget(10,10,1000,15,Component.literal("SleepDeprivation - Fig menu"),font);
        {

            System.out.println(width);
            System.out.println(height);
            this.addRenderableWidget(close);
            this.addRenderableWidget(title);
            this.addRenderableWidget(interval);
            this.addRenderableWidget(intervalRandomness);
            this.addRenderableWidget(modifyInventory);
            this.addRenderableWidget(threshold1);
            this.addRenderableWidget(threshold2);
            this.addRenderableWidget(threshold3);
            this.addRenderableWidget(threshold4);
            this.addRenderableWidget(threshold5);
            this.addRenderableWidget(inventory1);
            this.addRenderableWidget(inventory2);
            this.addRenderableWidget(inventory3);
            this.addRenderableWidget(inventory4);
            this.addRenderableWidget(inventory5);
            this.addRenderableWidget(inventory5);
            this.addRenderableWidget(modifyHotbar);
            this.addRenderableWidget(effects1);
            this.addRenderableWidget(effects2);
            this.addRenderableWidget(effects3);
            this.addRenderableWidget(effects4);
            this.addRenderableWidget(effects5);
            this.addRenderableWidget(modifyeffects);
            this.addRenderableWidget(Message1);
            this.addRenderableWidget(Message2);
            this.addRenderableWidget(Message3);
            this.addRenderableWidget(Message4);
            this.addRenderableWidget(Message5);
            this.addRenderableWidget(modifyMessage);
            this.addRenderableWidget(apply);
            StringWidget threshold = new StringWidget(10,80,1000,15,Component.literal("Threshold"),font);
            this.addRenderableWidget(threshold);
            this.addRenderableWidget(new StringWidget((((width*4)/8)-(width)/8)+18,80,1000,15,Component.literal("Message"),font));
            this.addRenderableWidget(new StringWidget((((width*3)/8)-(width)/8)+13,80,1000,15,Component.literal("Effects"),font));
            this.addRenderableWidget(new StringWidget((((width*2)/8)-(width)/8)+10,80,1000,15,Component.literal("Inventory"),font));
            this.addRenderableWidget(new StringWidget(10,30,1000,15,Component.literal("Interval"),font));
            this.addRenderableWidget(new StringWidget((((width*2)/8)-(width)/8)+10,30,1000,15,Component.literal("Randomness"),font));
            this.addRenderableWidget(new StringWidget(1,height-14,1000,15,Component.literal("SleepDeprivation v2.1 by TheCreeper3326").withStyle(ChatFormatting.GRAY,ChatFormatting.ITALIC),font));

        }

    }

    public boolean shouldCloseOnEsc() {
        return true;
    }
}
