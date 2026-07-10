package net.apexclient.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleManager
{
    private final List<Module> moduleList = new ArrayList<>();

    public void addModule(Module... modules)
    {
        moduleList.addAll(Arrays.asList(modules));
    }

    public List<Module> getModules(Category category)
    {
        return moduleList.stream().filter(module -> module.getCategory() == category).toList();
    }
}
