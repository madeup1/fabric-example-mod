package net.apexclient.ui;

import com.mojang.blaze3d.systems.RenderSystem;
import imgui.*;
import imgui.flag.ImGuiChildFlags;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiStyleVar;
import imgui.flag.ImGuiWindowFlags;
import net.apexclient.ApexClient;
import net.apexclient.imgui.ImGuiImpl;
import net.apexclient.imgui.RenderInterface;
import imgui.type.ImBoolean;
import net.apexclient.modules.Category;
import net.apexclient.ui.elements.Element;
import net.apexclient.ui.elements.impl.*;
import net.apexclient.ui.elements.impl.slider.IntRangeElement;
import net.apexclient.ui.elements.impl.slider.IntSliderElement;
import net.apexclient.ui.images.ImImage;
import net.apexclient.ui.window.Window;
import net.apexclient.ui.window.impl.HomeWindow;
import net.apexclient.ui.window.impl.ModuleWindow;
import net.apexclient.ui.window.impl.TestWindow;
import net.apexclient.utils.FontUtils;
import net.apexclient.utils.ImUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.lwjgl.opengl.GL11;

import java.util.List;

public final class ClickGuiScreen extends Screen implements RenderInterface
{
    private static final int SIZE_X = 1280;
    private static final int SIZE_Y = 720;

    private static final int SIDE_MENU_WIDTH = 200;

    private static final ImImage LOGO = new ImImage("/assets/apexclient/icon.png");

    private static final List<Element> elements = List.of(
            new BooleanElement("Test Checkbox", true),
            new EnumElement<>("Branch", BranchType.BETA),
            new ButtonElement("Remove Client", () -> System.out.println("button pressed")),
            new TextElement("This is a description setting\ntest wrapped"),
            new IntElement("Int Element", 10),
            new FloatElement("Float Element", 1.3f),
            new DoubleElement("Double Element", 0.99d),
            new IntSliderElement("Int Slider Element", 5, 0, 10),
            new IntRangeElement("Int Range Element", 0, 10, 2, 8)
    );

    private static final List<SidebarButton> sidebarButtons = List.of(
            new SidebarButton("Home", () -> {setWindow(new HomeWindow());}),
            new SidebarButton("Macros", () -> {}),
            new SidebarButton("Farming", () -> {setWindow(new ModuleWindow(Category.Farming));}),
            new SidebarButton("Mining", () -> {}),
            new SidebarButton("Foraging", () -> {}),
            new SidebarButton("Dungeons", () -> {}),
            new SidebarButton("Kuudra", () -> {}),
            new SidebarButton("QOL", () -> {}),
            new SidebarButton("Misc", () -> {}),
            new SidebarButton("Config", () -> {}),
            new SidebarButton("Settings", () -> {setWindow(new TestWindow());})
    );

    private static final ImVec4 PRIMARY_COLOR = new ImVec4(1f, 0.07f, 0.09f, 1f);
    private static final ImVec4 SECONDARY_COLOR = new ImVec4(0.8f, 0.03f, 0.03f, 0.8f);

    private static Window window = new HomeWindow();

    public ClickGuiScreen()
    {
        super(Component.literal("clickui"));

        ImGuiStyle style = ImGui.getStyle();

        style.setWindowPadding(15, 15);
        style.setFrameRounding(6f);
        style.setWindowRounding(6f);
        style.setChildRounding(6f);

        style.setColor(ImGuiCol.TitleBgActive, 0.05f, 0.05f, 0.05f, 0.95f);
        style.setColor(ImGuiCol.TitleBg, 0.05f, 0.05f, 0.05f, 0.95f);
        style.setColor(ImGuiCol.Button, SECONDARY_COLOR.x, SECONDARY_COLOR.y, SECONDARY_COLOR.z, SECONDARY_COLOR.w);

        // checkbox
        ImUtils.setStyleColor(ImGuiCol.CheckMark, SECONDARY_COLOR);
    }

    @Override
    public void render(ImGuiIO io)
    {
        float centerX = (io.getDisplaySizeX() - SIZE_X) / 2;
        float centerY = (io.getDisplaySizeY() - SIZE_Y) / 2;

        io.setFontGlobalScale(1.3f);

        ImGui.setNextWindowPos(centerX, centerY);
        ImGui.setNextWindowSize(SIZE_X, SIZE_Y);

        final String title = "Apex Client v" + ApexClient.VERSION + " [" + window.getName() + "]";

        if (ImGui.begin(title,
                ImGuiWindowFlags.NoResize | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoCollapse | ImGuiWindowFlags.NoTitleBar)
        )
        {
            drawSideMenu();
            ImGui.sameLine();
            drawWindow(io);
        }

        ImGui.end();
    }

    private void drawWindow(ImGuiIO io)
    {
        final float width = ImGui.getContentRegionAvailX();
        final float height = ImGui.getContentRegionAvailY();

        if (ImGui.beginChild("window", new ImVec2(width, height)))
        {
            window.render(io);
            ImGui.endChild();
        }
    }

    private void drawSideMenu()
    {
        final float availY = ImGui.getContentRegionAvailY();
        if (ImGui.beginChild("side-menu", new ImVec2(SIDE_MENU_WIDTH, availY), ImGuiChildFlags.Border))
        {
            LOGO.draw(64, 64);
            ImGui.sameLine();
            ImGui.textColored(PRIMARY_COLOR, "\nApex Client\nv" + ApexClient.VERSION);

            ImUtils.space(10);

            float availX = ImGui.getContentRegionAvailX();
            ImVec2 buttonSize = new ImVec2(availX, 40);

            for (SidebarButton button : sidebarButtons)
            {
                if (ImGui.button(button.name(), buttonSize))
                    button.callback().run();
            }

            float height = ImGui.getWindowHeight();
            float itemHeight = buttonSize.y;

            ImGui.setCursorPosY(height - itemHeight - ImGui.getStyle().getWindowPaddingY());

            ImGui.button("Edit HUD", buttonSize);
        }

        ImGui.endChild();
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public static Window getWindow()
    {
        return window;
    }

    public static void setWindow(Window window)
    {
        ClickGuiScreen.window = window;
    }

    public enum BranchType
    {
        LEGACY,
        MODERN,
        BETA,
        ALPHA,
        SUPPORTER
    }
}
