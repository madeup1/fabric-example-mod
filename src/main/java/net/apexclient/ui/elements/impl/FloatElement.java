package net.apexclient.ui.elements.impl;

import com.google.gson.JsonObject;
import imgui.ImGui;
import imgui.type.ImFloat;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class FloatElement extends Element
{
    private final ImFloat value;

    public FloatElement(String name, float defaultValue, BoolPredicate predicate)
    {
        super(name, predicate);

        this.value = new ImFloat(defaultValue);
    }

    public FloatElement(String name, float defaultValue)
    {
        this(name, defaultValue, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.inputFloat(getName(), value);
    }

    public float getValue()
    {
        return value.get();
    }

    public void setValue(float value)
    {
        this.value.set(value);
    }

    @Override
    public void read(JsonObject object)
    {
        value.set(object.get("value").getAsFloat());
    }

    @Override
    public void write(JsonObject object)
    {
        object.addProperty("value", value.get());
    }
}
