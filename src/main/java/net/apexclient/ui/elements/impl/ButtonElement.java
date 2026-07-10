package net.apexclient.ui.elements.impl;

import imgui.ImGui;
import net.apexclient.imgui.ImGuiImpl;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class ButtonElement extends Element
{
    private final Runnable runnable;
    public ButtonElement(String name, Runnable runnable, BoolPredicate predicate)
    {
        super(name, predicate);

        this.runnable = runnable;
    }

    public ButtonElement(String name, Runnable runnable)
    {
        this(name, runnable, () -> true);
    }

    @Override
    public void render()
    {
        if (ImGui.button(getName()))
        {
            runnable.run();
        }
    }
}
