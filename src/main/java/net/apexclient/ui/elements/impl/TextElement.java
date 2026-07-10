package net.apexclient.ui.elements.impl;

import com.google.gson.JsonObject;
import imgui.ImGui;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class TextElement extends Element
{
    private final String text;
    public TextElement(String text, BoolPredicate predicate)
    {
        super("nameless", predicate);

        this.text = text;
    }

    public TextElement(String text)
    {
        this(text, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.text(text);
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
