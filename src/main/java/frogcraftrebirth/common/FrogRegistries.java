package frogcraftrebirth.common;

import frogcraftrebirth.api.FrogAPI;
import frogcraftrebirth.api.FrogRegistees;
import frogcraftrebirth.common.block.*;
import frogcraftrebirth.common.item.*;
import frogcraftrebirth.common.lib.item.ItemFrogBlock;
import frogcraftrebirth.common.potion.PotionTiberium;
import frogcraftrebirth.common.registry.RegFluid;
import frogcraftrebirth.common.tile.*;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = FrogAPI.MODID)
public class FrogRegistries {

    @SubscribeEvent
    public static void regBlock(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(new BlockHSU(), new BlockMPS(), new BlockCondenseTower(), new BlockGenerator(),
                new BlockMachine(), new BlockFrogOre(), new BlockTiberium());
        GameRegistry.registerTileEntity(TileMobilePowerStation.class, "frogcraft_mobile_power_station");
        GameRegistry.registerTileEntity(TileHSU.class, "frogcraft_hybrid_storage_unit");
        GameRegistry.registerTileEntity(TileHSUUltra.class, "frogcraft_ultra_hybrid_storage_unit");
        GameRegistry.registerTileEntity(TileAirPump.class, "frogcraft_air_pump");
        GameRegistry.registerTileEntity(TileCondenseTower.class, "frogcraft_condense_tower_core");
        GameRegistry.registerTileEntity(TileCondenseTowerStructure.class, "frogcraft_condense_tower_cylinder");
        GameRegistry.registerTileEntity(TileFluidOutputHatch.class, "frogcraft_condense_tower_outlet");
        GameRegistry.registerTileEntity(TileCombustionFurnace.class, "frogcraft_combustion_furnace");
        GameRegistry.registerTileEntity(TilePyrolyzer.class, "frogcraft_thermal_cracker");
        GameRegistry.registerTileEntity(TileAdvChemReactor.class, "frogcraft_advanced_chemical_reactor");
        GameRegistry.registerTileEntity(TileLiquefier.class, "frogcraft_liquefier");

        // Call this to ensure their blocks are registered at proper time
        // TODO pass the injected dependency, i.e. the master block registry
        RegFluid.init();
    }

    @SubscribeEvent
    public static void regItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                new ItemAmmoniaCoolant("60K", 6000).setRegistryName("ammonia_coolant_60k"),
                new ItemAmmoniaCoolant("180K", 18000).setRegistryName("ammonia_coolant_180k"),
                new ItemAmmoniaCoolant("360K", 36000).setRegistryName("ammonia_coolant_360k"),
                new ItemResources("Item_Ingots", "K", "P", "NaturalGasHydrate", "Briquette", "CoalCokeShattered") {
                    @Override
                    public boolean onEntityItemUpdate(EntityItem entityItem) {
                        if (entityItem.getItem().isEmpty()) {
                            return false;
                        }
                        if (!entityItem.getEntityWorld().isRemote && entityItem.getItem().getMetadata() == 0) {
                            if (entityItem.getEntityWorld().getBlockState(entityItem.getPosition()).getBlock() == FrogRegistees.NITRIC_ACID) {
                                //TODO set up explosion and grant advancement to players
                                entityItem.setDead();
                                return false;
                            }
                        }
                        return true;
                    }

                    @Override
                    public int getItemBurnTime(ItemStack stack) {
                        switch (stack.getMetadata()) {
                            case 3: return 18000;
                            case 4: return 1600;
                            default: return 0;
                        }
                    }
                }.setRegistryName("ingot"),
                new ItemResources("Item_Dusts", "Al2O3", "CaF2", "CaO", "CaOH2", "Carnallite", "CaSiO3", "Dewalquite", "Fluorapatite", "KCl", "Magnalium", "MgBr2", "NH4NO3", "TiO2", "Urea", "V2O5").setRegistryName("dust"),
                new ItemResources("crushedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("crushed"),
                new ItemResources("purifiedOre", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("purified"),
                new ItemResources("smallDust", "Carnallite", "Dewalquite", "Fluorapatite").setRegistryName("small_pile_dust"),
                new ItemResources("reactionModule", "Heating", "Electrolyze", "Ammonia", "V2O5").setRegistryName("catalyst_module"),
                new ItemIonCannon(1000000).setRegistryName("ion_cannon"),
                new ItemResources(false, "ionCannonFrame").setMaxStackSize(1).setRegistryName("ion_cannon_frame"),
                new ItemDecayBattery("U").setRegistryName("uranium_decay_battery"),
                new ItemDecayBattery("Th").setRegistryName("thorium_decay_battery"),
                new ItemDecayBattery("Pu").setRegistryName("plutoium_decay_battery"),
                new ItemJinkela().setRegistryName("jinkela"),
                new ItemTiberium().setRegistryName("tiberium"),
                new ItemFluidArmor(12000).setRegistryName("fluid_armor"),
                new ItemFrogBlock(FrogRegistees.CONDENSE_TOWER, aStack -> BlockCondenseTower.Part.values()[aStack.getMetadata() & 0b11].getName()).setRegistryName(FrogRegistees.CONDENSE_TOWER.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.ORE, aStack -> BlockFrogOre.Type.values()[aStack.getMetadata()].getName()).setRegistryName(FrogRegistees.ORE.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.GENERATOR, aStack -> "combustionFurnace").setRegistryName(FrogRegistees.GENERATOR.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.HSU, aStack -> BlockHSU.Level.values()[aStack.getMetadata() % 2].getName()).setRegistryName(FrogRegistees.HSU.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.MACHINE, aStack -> BlockMachine.Type.values()[aStack.getMetadata() & 0b11].getName()).setRegistryName(FrogRegistees.MACHINE.getRegistryName()),
                new ItemFrogBlock(FrogRegistees.TIBERIUM, aStack -> BlockTiberium.Color.values()[aStack.getMetadata()].getName()).setRegistryName(FrogRegistees.TIBERIUM.getRegistryName()),
                new ItemMPS((BlockMPS) FrogRegistees.MPS).setRegistryName(FrogRegistees.MPS.getRegistryName())
        );
    }

    @SubscribeEvent
    public static void regPotion(RegistryEvent.Register<Potion> event) {
        event.getRegistry().register(new PotionTiberium(0x66CCFF).setRegistryName("frogcraftrebirth:tiberium"));
    }
}
