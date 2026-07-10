package net.apexclient.modules;

import imgui.type.ImBoolean;
import net.apexclient.ui.elements.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module
{
    private final String name;
    private final ImBoolean toggle;
    private final Category category;
    private final String description;
    private final List<Element> elements = new ArrayList<>();

    public Module(String name, Category category, String description, boolean defaultEnabled)
    {
        this.name = name;
        this.toggle = new ImBoolean(defaultEnabled);
        this.category = category;
        this.description = description;
    }

    public Module(String name, Category category, String description)
    {
        this(name, category, description, false);
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void addElements(Element... elements)
    {
        this.elements.addAll(Arrays.asList(elements));
    }

    public boolean isEnabled()
    {
        return toggle.get();
    }

    public void setEnabled(boolean enabled)
    {
        toggle.set(enabled);

        if (isEnabled())
        {
            onEnable();
        }
        else
        {
            onDisable();
        }
    }

    public ImBoolean getImBoolean()
    {
        return toggle;
    }

    public String getDescription()
    {
        return description;
    }

    public Category getCategory()
    {
        return category;
    }

    public String getName()
    {
        return name;
    }
}
