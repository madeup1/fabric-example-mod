package net.apexclient.utils;

import imgui.ImGui;
import imgui.ImVec4;

public class ImUtils
{
    public static void space(int times)
    {
        for (int i = 0; i < times; i++)
            ImGui.spacing();
    }

    public static void wrappedText(String text, int width)
    {
        ImGui.pushTextWrapPos(ImGui.getCursorPosX() + width);
        ImGui.text(text);
        ImGui.popTextWrapPos();
    }

    public static void setStyleColor(int style, ImVec4 color)
    {
        ImGui.getStyle().setColor(style, color.x, color.y, color.z, color.w);
    }
}
