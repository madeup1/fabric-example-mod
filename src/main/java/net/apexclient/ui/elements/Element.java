package net.apexclient.ui.elements;

public abstract class Element
{
    private final String name;
    private final BoolPredicate predicate;

    public Element(String name)
    {
        this(name, () -> true);
    }

    public Element(String name, BoolPredicate predicate)
    {
        this.name = name;
        this.predicate = predicate;
    }

    public boolean isVisible()
    {
        return predicate.valid();
    }

    public void draw()
    {
        if (isVisible())
            render();
    }

    public String getName()
    {
        return name;
    }

    public abstract void render();
}
