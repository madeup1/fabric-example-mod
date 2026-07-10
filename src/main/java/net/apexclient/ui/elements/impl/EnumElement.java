package net.apexclient.ui.elements.impl;

import com.google.gson.JsonObject;
import imgui.ImGui;
import imgui.type.ImInt;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class EnumElement<E extends Enum<E>> extends Element
{
    private final E[] values;
    private final String[] names;
    private final ImInt index;
    private final E defaultValue;

    public EnumElement(String name, E defaultValue, BoolPredicate predicate)
    {
        super(name, predicate);

        this.values = defaultValue.getDeclaringClass().getEnumConstants();
        names = new String[values.length];

        for (int i = 0; i < values.length; i++)
            names[i] = values[i].toString();

        index = new ImInt(findIndex(defaultValue));
        this.defaultValue = defaultValue;
    }

    public EnumElement(String name, E defaultValue)
    {
        this(name, defaultValue, () -> true);
    }

    private int findIndex(E value)
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] == value)
                return i;
        }

        return findIndex(defaultValue);
    }

    @Override
    public void render()
    {
        if (ImGui.combo(getName(), index, names))
        {

        }
    }

    public E getValue()
    {
        return values[index.get()];
    }

    public void setValue(E value)
    {
        index.set(findIndex(value));
    }

    @Override
    public void read(JsonObject object)
    {
        int index = findIndex(defaultValue);
        for (int i = 0; i < values.length; i++)
        {
            if (values[i].name().equals(object.get("value").getAsString()))
                index = i;
        }

        this.index.set(index);
    }

    @Override
    public void write(JsonObject object)
    {
        object.addProperty("value", values[index.get()].name());
    }
}
