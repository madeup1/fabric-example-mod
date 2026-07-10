package net.apexclient.ui.elements.impl;

import imgui.ImGui;
import imgui.type.ImDouble;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class DoubleElement extends Element
{
    private final ImDouble value;

    public DoubleElement(String name, double value, BoolPredicate predicate)
    {
        super(name, predicate);

        this.value = new ImDouble(value);
    }

    public DoubleElement(String name, double value)
    {
        this(name, value, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.inputDouble(getName(), value);
    }

    public double getValue()
    {
        return value.get();
    }

    public void setValue(double value)
    {
        this.value.set(value);
    }
}
