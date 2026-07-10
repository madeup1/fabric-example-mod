package net.apexclient.utils;

import imgui.ImFont;
import imgui.ImGui;
import imgui.ImGuiIO;

import java.io.IOException;
import java.io.InputStream;

public class FontUtils
{

    public static ImFont ROBOTO;
    private static byte[] ROBOTO_DATA;

    public static void init()
    {

    }

    public static byte[] getFontData(String path)
    {
        try (var stream = FontUtils.class
                .getClassLoader()
                .getResourceAsStream(path)) {

            if (stream == null)
                throw new RuntimeException("Font not found: " + path);

            return stream.readAllBytes();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}