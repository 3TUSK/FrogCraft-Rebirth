package frogcraftrewrite.client.gui;

import org.lwjgl.opengl.GL11;

import frogcraftrewrite.common.gui.ContainerIndustrialDevice;
import frogcraftrewrite.common.lib.tile.TileFrogInductionalDevice;
import frogcraftrewrite.common.tile.TileInductionalCompressor;
import frogcraftrewrite.common.tile.TileInductionalEFurnace;
import frogcraftrewrite.common.tile.TileInductionalExtractor;
import frogcraftrewrite.common.tile.TileInductionalMacerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiIndustrialDevice extends GuiContainer{

	public TileFrogInductionalDevice tile;
	
	public GuiIndustrialDevice(InventoryPlayer invPlayer, TileFrogInductionalDevice tileEntity) {
		super(new ContainerIndustrialDevice(invPlayer, tileEntity));
		this.tile = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int what, int whhat) {
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96, 4210752);
		fontRendererObj.drawString("Inductional Device test", 4, 4, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		//Draw GUI start. Based on vanilla furnace code.
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation("frogcraft:textures/gui/Gui_IndustrialDevice.png"));//TODO redo the texture
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        //Draw major GUI end.
        //Draw string start, switching by meta: 0=E-Furnace, 1=Macerator, 2=Extractor, 3=Compressor
        int energyCurrent = 14*tile.energy/tile.energyMax;
        int processCurrent = tile.process*24/15;
        
        if (tile.energy>0)
        	this.drawTexturedModalRect(x + 81, y + 52+14-energyCurrent, 176, 31 - energyCurrent, 14, energyCurrent);
        
        if (tile instanceof TileInductionalEFurnace)
        	this.drawTexturedModalRect(x + 76, y + 25, 201, 0, 24, 16);
        if (tile instanceof TileInductionalCompressor)
        	this.drawTexturedModalRect(x + 76, y + 25, 201, 32, 24, 16);	
        if (tile instanceof TileInductionalMacerator)
        	this.drawTexturedModalRect(x + 76, y + 25, 201, 50, 24, 16);
        if (tile instanceof TileInductionalExtractor)      
        	this.drawTexturedModalRect(x + 76, y + 25, 201, 68, 24, 16);
        
        if (tile.process > 0){
        	if (tile instanceof TileInductionalEFurnace)
        		this.drawTexturedModalRect(x + 76, y + 25, 176, 0, processCurrent, 16);
        	if (tile instanceof TileInductionalCompressor)
        		this.drawTexturedModalRect(x + 76, y + 25, 176, 32, processCurrent, 16);
        	if (tile instanceof TileInductionalMacerator)
        		this.drawTexturedModalRect(x + 76, y + 25, 176, 50, processCurrent, 16);
        	if (tile instanceof TileInductionalExtractor)
        		this.drawTexturedModalRect(x + 76, y + 25, 176, 68, processCurrent, 16);                	
        }
	}

}
