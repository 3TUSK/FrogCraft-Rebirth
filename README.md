## FrogCraft: Rebirth
A Minecraft Mod, IC2 Addon, with theme of chemistry and fun things.

### Table of content:  
 1. Intro of FrogCraft: Rebirth
 2. What's changed in this new port?
 3. Notes for potential contributors
 3. Future Plan
 4. License
 5. Credits
 
### Introduction
FrogCraft: Rebirth is spiritual successor of [FrogCraft][link_FrogCraft_original] (and also the [ported one][link_FrogCraft_ported]), an Addon-Mod for IndustrialCraft2/GregTech, with theme of chemical industry, featured with following:
 * Advanced Chemical Reactor
 * Pyrolyzer
 * Production line of Liquid Air
 * Massive EU storage unit with limited functionality
 * Combustion Furnace - regular generator plus functionality of collecting byproduct
 * Mobile Power Station
 * New ores
 * Decay Battery (Creative Only for now)
 * Ammonia Coolant
 * Eastern Eggs and more!
 
### What's changed in this version?
 * Due to GregTech is still in 1.7.10, compatibilities for GregTech, including Pnuematic Compressor, are disabled for now. 
 * New textures powered by huangziye812!
 * Thermal Cracker is renamed to **Pyrolyzer**. "Thermal Cracker" sounds like Chinglish to me. 
 * Cells are removed. Use IC2 Universal Fluid Cell instead.
 * Crushed ore dust, purified ore dust, and dust of small pile! 
 * Railgun and Academy Windmill are removed. The reason of FrogCraft being named so is believed as a reference to anime [_A Certain Scientific Railgun_](https://en.wikipedia.org/wiki/A_Certain_Scientific_Railgun), and the evidences are the Railgun and Academy Windmill. Right now I consider [AcademyCraft][link_ACMOD] as the spiritual successor of FrogCraft's anime elements.
 * As a replacement of Railgun, a whole new item named *Portable Ion Cannon* is added. This is a rough reference to [Command & Conquer 3: Tiberium War](https://en.wikipedia.org/wiki/Command_%26_Conquer_3:_Tiberium_Wars) plots.
 * Marble and Basalt are removed. They used to be a substitution to RedPower's Marble and Basalt, but now I suggest to use other equivalents from other mods, e.g. [Chisel](https://minecraft.curseforge.com/projects/chisel).
 * Ruby, Sapphire and Green Sapphire are substituted with [Tiberium](https://en.wikipedia.org/wiki/Tiberium). Reason is same as of Marble and Basalt.
 * **This mod now requires Java 8 to run**. If you plan to use FrogCraft: Rebirth on dedicated server, please be aware of your Java version.

### Notes for potential contributors
If you're trying to run FrogCraft: Rebirth in development environment but get crash like this:
````
java.lang.NoSuchMethodError: net.minecraft.world.biome.BiomeProvider.getBiomeGenerator(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/world/biome/Biome;)Lnet/minecraft/world/biome/Biome;
````

You may want to add the following arguments into your JVM arguments:
````
-Dfml.coreMods.load=frogcraftrebirth.common.asm.FrogASMPlugin
````
This should only happen in development environment, not in regular game.

### Future plan: _FrogCraft 2: Chemia_
*FrogCraft 2: Chemia* (aka Laboratorium Chemiae) is a mod highly concentrating on chemical industry, aiming to establish a whole system about chemicals synthetic and usage.  
There is no real progress of FrogCraft2, but once FrogCraft: Rebirth is stable, I will start to work on that.

*FrogCraft 2: Chemia* will follow [Cult of Kitteh][link_CultOfKitteh].  
Yes, NO GUI. It sounds like insane, but it *will* be.

### License
All code of FrogCraft: Rebirth is licensed under [the MIT License](./LICENSE_FrogCraft_Rebirth).  
All textures are All Rights Reserved, as some are permitted to use only in FrogCraft: Rebirth. 

### Credits
The progress of FrogCraft: Rebirth will not come true without the following folks' support:  
 * Credits to [ueyudiud](https://github.com/ueyudiud) for his generous consultant.  
 * Credits to dunge for his design idea and several texture.  
 * Credits to [Glease](https://github.com/Glease) for his consultant.
 * Credits to [huangziye812](http://tieba.baidu.com/home/main?un=huangziye812) for his new textures.

[link_FrogCraft_original]: http://forum.industrial-craft.net/index.php?page=Thread&threadID=9458
[link_FrogCraft_ported]: http://forum.industrial-craft.net/index.php?page=Thread&threadID=10447
[link_ACMOD]: https://github.com/LambdaInnovation/AcademyCraft
[link_CultOfKitteh]: http://asie.pl/kitteh/