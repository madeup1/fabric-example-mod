package net.apexclient.ui.elements.impl;

import com.google.gson.JsonObject;
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

    @Override
    public void read(JsonObject object)
    {

    }

    @Override
    public void write(JsonObject object)
    {

    }
}
