package net.apexclient.ui.elements.impl.slider;

import com.google.gson.JsonObject;
import imgui.ImGui;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class IntSliderElement extends Element
{
    private final int[] mutable = new int[1];
    private final int min, max;

    public IntSliderElement(String name, int value, int min, int max, BoolPredicate predicate)
    {
        super(name, predicate);

        mutable[0] = value;
        this.min = min;
        this.max = max;
    }

    public IntSliderElement(String name, int value, int min, int max)
    {
        this(name, value, min, max, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.dragInt(getName(), mutable, ((float) 0.5 / (max - min)), min, max);
    }

    public int getValue()
    {
        return mutable[0];
    }

    public void setValue(int value)
    {
        mutable[0] = value;
    }

    @Override
    public void read(JsonObject object)
    {
        mutable[0] = object.get("value").getAsInt();
    }

    @Override
    public void write(JsonObject object)
    {
        object.addProperty("value", mutable[0]);
    }
}
