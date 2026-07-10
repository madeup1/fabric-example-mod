package net.apexclient.ui.elements.impl;

import imgui.ImGui;
import imgui.type.ImInt;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class IntElement extends Element
{
    private final ImInt value;
    public IntElement(String name, int defaultValue, BoolPredicate predicate)
    {
        super(name, predicate);

        this.value = new ImInt(defaultValue);
    }

    public IntElement(String name, int defaultValue)
    {
        this(name, defaultValue, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.inputInt(getName(), value);
    }

    public int getValue()
    {
        return value.get();
    }

    public void setValue(int value)
    {
        this.value.set(value);
    }
}
