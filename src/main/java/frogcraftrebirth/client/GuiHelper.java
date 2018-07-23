package frogcraftrebirth.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.List;

public final class GuiHelper {
    public static void renderStringList(Minecraft minecraft, List<String> list, int x, int y, int color) {
        FontRenderer font = minecraft.fontRenderer;
        final int height = font.FONT_HEIGHT;
        for (int i = 0; i < list.size(); i++) {
            minecraft.fontRenderer.drawString(list.get(i), x, y + (i * height), color);
        }
    }
}
