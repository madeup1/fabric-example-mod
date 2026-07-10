package net.apexclient.modules;

import imgui.type.ImBoolean;

public abstract class Module
{
    private final String name;
    private final ImBoolean toggle;
    private final Category category;
    private final String description;

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
