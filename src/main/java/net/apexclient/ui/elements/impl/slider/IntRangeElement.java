package net.apexclient.ui.elements.impl.slider;

import imgui.ImGui;
import imgui.flag.ImGuiSliderFlags;
import net.apexclient.ui.elements.BoolPredicate;
import net.apexclient.ui.elements.Element;

public class IntRangeElement extends Element
{
    private final int[] min, max;
    private final int minimum, maximum;

    public IntRangeElement(String name, int minimum, int maximum, int defaultMin, int defaultMax, BoolPredicate predicate)
    {
        super(name, predicate);

        this.min = new int[]{defaultMin};
        this.max = new int[]{defaultMax};

        this.minimum = minimum;
        this.maximum = maximum;
    }

    public IntRangeElement(String name, int minimum, int maximum, int defaultMin, int defaultMax)
    {
        this(name, minimum, maximum, defaultMin, defaultMax, () -> true);
    }

    @Override
    public void render()
    {
        ImGui.dragIntRange2(getName(), min, max, (0.4f / (maximum - minimum)), minimum, maximum);
    }

    public int getMinimum()
    {
        return minimum;
    }

    public int getMaximum()
    {
        return maximum;
    }

    public int getCurrentMinimum()
    {
        return min[0];
    }

    public void setCurrentMinimum(int value)
    {
        min[0] = value;
    }

    public int getCurrentMaximum()
    {
        return max[0];
    }

    public void setCurrentMaximum(int value)
    {
        max[0] = value;
    }
}
