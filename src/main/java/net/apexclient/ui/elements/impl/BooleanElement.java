package net.apexclient.ui.elements.impl;

import imgui.ImGui;
import imgui.type.ImBoolean;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class BooleanElement extends Element
{
    private final ImBoolean value;

    public BooleanElement(String name, boolean value, BoolPredicate predicate)
    {
        super(name, predicate);

        this.value = new ImBoolean(value);
    }

    public BooleanElement(String name, boolean value)
    {
        this(name, value, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.checkbox(getName(), value);
    }

    public boolean isEnabled()
    {
        return value.get();
    }

    public void setEnabled(boolean value)
    {
        this.value.set(value);
    }
}
