package net.apexclient.ui.images;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import net.apexclient.utils.ImageUtils;
import net.minecraft.client.Minecraft;

import java.io.IOException;

public class ImImage
{
    private final long textureId;
    public ImImage(String path)
    {
        textureId = ImageUtils.loadTexture(path);
    }

    public void draw(int sizeX, int sizeY)
    {
        ImGui.image(textureId, sizeX, sizeY);
    }
}
