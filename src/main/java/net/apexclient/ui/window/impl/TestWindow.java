package net.apexclient.ui.window.impl;

import imgui.ImGuiIO;
import net.apexclient.ui.ClickGuiScreen;
import net.apexclient.ui.elements.Element;
import net.apexclient.ui.elements.impl.*;
import net.apexclient.ui.elements.impl.slider.IntRangeElement;
import net.apexclient.ui.elements.impl.slider.IntSliderElement;
import net.apexclient.ui.window.Window;

import java.util.List;

public class TestWindow implements Window
{
    private static final List<Element> elements = List.of(
            new BooleanElement("Test Checkbox", true),
            new EnumElement<>("Branch", ClickGuiScreen.BranchType.BETA),
            new ButtonElement("Remove Client", () -> System.out.println("button pressed")),
            new TextElement("This is a description setting\ntest wrapped"),
            new IntElement("Int Element", 10),
            new FloatElement("Float Element", 1.3f),
            new DoubleElement("Double Element", 0.99d),
            new IntSliderElement("Int Slider Element", 5, 0, 10),
            new IntRangeElement("Int Range Element", 0, 10, 2, 8)
    );

    @Override
    public void render(ImGuiIO io)
    {
        elements.forEach(Element::draw);
    }

    @Override
    public String getName()
    {
        return "";
    }
}
