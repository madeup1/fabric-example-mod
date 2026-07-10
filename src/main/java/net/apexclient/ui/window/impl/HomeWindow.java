package net.apexclient.ui.window.impl;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImVec2;
import imgui.ImVec4;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiMouseButton;
import imgui.flag.ImGuiStyleVar;
import net.apexclient.ui.window.Window;
import net.apexclient.utils.ImUtils;

public class HomeWindow implements Window
{
    private static final String HOME_TEXT = """
              Welcome to Apex Client! This client is still in heavy development.
              Features are subject to change and are still being fine tuned.
              Please join the Discord and support the client by advertising!
              Report bugs and make suggestions in the Discord as well.
            """;

    private static final int STATUS_TAB_WIDTH = 300;

    @Override
    public void render(ImGuiIO io)
    {
        float width = ImGui.getContentRegionAvailX();
        if (ImGui.beginChild("welcome-info", new ImVec2(width, 200)))
        {
            ImGui.text(HOME_TEXT);
        }
        ImGui.endChild();
    }

    @Override
    public String getName()
    {
        return "Home";
    }
}
