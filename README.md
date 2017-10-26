## FrogCraft: Rebirth
A Minecraft Mod, IC2 Addon, with theme of chemistry and fun things.

### Table of content:  
 1. Intro of FrogCraft: Rebirth
 2. What's changed in this new port?
 3. Other posts
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
 * Advanced Blast Furnace
 * Mobile Power Station
 * New ores
 * Decay Battery (Creative Only for now)
 * Ammonia Coolant
 * Easter Eggs and more!
 
### What's changed in this version?
 * New textures powered by huangziye812 and dunge!
 * Thermal Cracker is renamed to **Pyrolyzer**. "Thermal Cracker" sounds like Chinglish to me. 
 * Cells are removed. Use IC2 Universal Fluid Cell instead.
 * Crushed ore dust, purified ore dust, and dust of small pile!
 * Full sulfuric acid production line!
 * Advanced Blast Furnace! Used for dealing with your titanium (IV) oxide and aluminium (III) oxide.
 * Railgun and Academy Windmill are removed. The reason of FrogCraft being named so is believed as a reference to anime [_A Certain Scientific Railgun_](https://en.wikipedia.org/wiki/A_Certain_Scientific_Railgun), and the evidences are the Railgun and Academy Windmill. Right now I consider [AcademyCraft][link_ACMOD] as the spiritual successor of FrogCraft's anime elements.
   * As a replacement of Railgun, a whole new item named *Portable Ion Cannon* is added. This is a rough reference to [Command & Conquer 3: Tiberium War](https://en.wikipedia.org/wiki/Command_%26_Conquer_3:_Tiberium_Wars) plots.
 * Marble and Basalt are removed. They used to be a substitution to RedPower's Marble and Basalt.
   * There is still plan about adding something like Portland Cement, but there is no action on that.
 * Ruby, Sapphire and Green Sapphire are substituted with [Tiberium](https://en.wikipedia.org/wiki/Tiberium). Reason is same as of Marble and Basalt.
 * Four industrial machines are separated out into [a new mod called "Industrial Machine"](https://github.com/3TUSK/IndustrialMachine). 
 * **This mod now requires Java 8 to run**. If you plan to use FrogCraft: Rebirth on dedicated server, please be aware of your Java version.

### Other posts
I have created several pages on different site for different users:
* For IC2 Forum: http://forum.industrial-craft.net/index.php?page=Thread&threadID=12584
  * Note: I forget to update it for a while.
* For Cursefroge: https://minecraft.curseforge.com/projects/frogcraft-rebirth
* For mcbbs: http://www.mcbbs.net/thread-615493-1-1.html

### For Potential Contributors - Deployment

This assumes that you have Gradle installed. If not, try find a wrapper script.

````bash
gradle setupDecompWorkspace
````

Append `eclipse`, `idea` or other sorts when possible.

### Future plan: [_Laboratorium Chemiae_](https://github.com/FrogCraft-Rebirth/LaboratoriumChemiae)
*Laboratorium Chemiae* (previously code-named FrogCraft 2: Chemia) is the spiritual successor of both FrogCraft & FrogCraft: Rebirth, which highly focuses on chemical engineering, aiming to establish an IRL-ish system about chemicals synthetic and usage.
There is no real progress of FrogCraft2, nor road map, but once FrogCraft: Rebirth is stable, I will start to work on that.

*FrogCraft 2: Chemia* will follow [Cult of Kitteh][link_CultOfKitteh].  
Yes, NO GUI. It sounds like insane, but it *will* be.

### License
All code of FrogCraft: Rebirth is licensed under [the MIT License](./LICENSE_FrogCraft_Rebirth).  
All textures are All Rights Reserved, as some are permitted to use only in FrogCraft: Rebirth.

This indicates that you may include this mod in your modpack and thus may be redistributed in a modpack.

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
