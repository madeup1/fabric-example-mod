package net.apexclient.modules.impl.farming;

import net.apexclient.modules.Category;
import net.apexclient.modules.Module;
import net.apexclient.ui.elements.impl.BooleanElement;

public class AutoPests extends Module
{
    public BooleanElement disco = new BooleanElement("Use Disco", true);
    public AutoPests()
    {
        super("Auto Pests", Category.Farming, "Automatically kills pests for you on the Garden.");

        addElements(disco);
    }

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {

    }
}
