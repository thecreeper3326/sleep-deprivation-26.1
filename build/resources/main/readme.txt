
       Guide to configuring SleepDeprivation

  quick "game tick" reference:
  1 second = 20 ticks
  1 minute = 1200 ticks
  1 in-game day = 24000 ticks

  "interval" integer : Number of ticks between events
  "intervalRandomness" integer : Amount of deviation from interval (in ticks)
  "stageThreshold1-5" integer : Amount of ticks that must pass without resting in order to execute the specified number of events

  INVENTORY
  "modifyInventory" boolean : Allows the inventory to get mixed up occasionally
  "inventorySwapMultiplier1-5" integer : Amount of inventory events that will occur in the respective stage
  "includeHotbar" boolean : Whether the mod should also target hotbar slots

  STATUS EFFECTS
  "modifyEffects" boolean : Allows players to receive negative status effects that get more potent the longer they go without rest
  "potency1-5" integer 0-10 : Assigns level of potency for each stage with 0 being off, 1 being tame and 10 being overwhelming
