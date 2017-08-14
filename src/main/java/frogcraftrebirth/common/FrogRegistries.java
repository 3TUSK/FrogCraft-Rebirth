package frogcraftrebirth.common;

import frogcraftrebirth.common.potion.PotionTiberium;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class FrogRegistries {

    @SubscribeEvent
    public static void regPotion(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(new PotionTiberium(0x66CCFF).setRegistryName("frogcraftrebirth:tiberium"));
    }
}
