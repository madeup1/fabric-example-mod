package net.apexclient.ui.window.impl;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.ImVec2;
import imgui.ImVec4;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiMouseButton;
import imgui.flag.ImGuiStyleVar;
import net.apexclient.ApexClient;
import net.apexclient.modules.Category;
import net.apexclient.modules.Module;
import net.apexclient.ui.window.Window;
import net.apexclient.utils.ImUtils;

import java.util.ArrayList;
import java.util.List;

public class ModuleWindow implements Window
{
    private final List<Module> modules;
    public ModuleWindow(Category category)
    {
        modules = ApexClient.moduleManager.getModules(category);

        System.out.println("size: " + modules.size());
    }

    @Override
    public void render(ImGuiIO io)
    {
        float width = ImGui.getContentRegionAvailX();
        float spacing = ImGui.getStyle().getItemSpacingX();

        float buttonWidth = (width - spacing * 2) / 3.0f;
        ImVec2 size = new ImVec2(buttonWidth, 100f);

        for (int i = 0; i < modules.size(); i++)
        {
            if (i % 3 != 0)
                ImGui.sameLine();

            drawButton(modules.get(i), size);
        }
    }

    private void drawButton(Module module, ImVec2 size)
    {
        if (ImGui.beginChild("text-module", size, ImGuiChildFlags.Border))
        {
            boolean rightClicked = ImGui.isWindowHovered()
                    && ImGui.isMouseClicked(ImGuiMouseButton.Right);

            ImGui.text(module.getName());
            ImUtils.wrappedText(module.getDescription(), 250);

            float checkboxWidth = ImGui.getFrameHeight();
            float rightPadding = ImGui.getStyle().getWindowPaddingX();

            ImGui.sameLine();
            ImGui.setCursorPosX(
                    ImGui.getWindowWidth() - checkboxWidth - rightPadding
            );

            ImGui.pushStyleColor(ImGuiCol.FrameBg, new ImVec4(0f, 0f, 0f, 0f));
            ImGui.pushStyleVar(ImGuiStyleVar.FrameBorderSize, 1.0f);
            ImGui.checkbox("##toggle", module.getImBoolean());
            ImGui.popStyleColor();
            ImGui.popStyleVar();

            if (rightClicked)
            {
                System.out.println("Click!");
            }
        }

        ImGui.endChild();
    }

    @Override
    public String getName()
    {
        return "a module window";
    }
}
