package net.apexclient.ui.window;

import imgui.ImGui;
import imgui.ImGuiIO;

public interface Window
{
    int SIZE_X = 1280;
    int SIZE_Y = 720;

    void render(ImGuiIO io);
    String getName();
}
