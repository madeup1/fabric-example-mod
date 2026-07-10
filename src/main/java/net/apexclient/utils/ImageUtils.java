package net.apexclient.utils;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.systems.RenderSystem;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import java.io.InputStream;

public class ImageUtils {

    public static int loadTexture(String resourcePath) {
        // Ensure this runs safely on the client render thread
        if (!RenderSystem.isOnRenderThread()) {
            throw new IllegalStateException("Textures must be loaded on the main render thread!");
        }

        int textureId = -1;

        try (InputStream is = ImageUtils.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }

            // 1. Use NativeImage strictly as our file decoder to ensure cross-platform compatibility
            NativeImage nativeImage = NativeImage.read(is);

            // 2. Generate a standard OpenGL texture ID
            textureId = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

            // 3. Set pixel storage properties to match NativeImage's layout memory profile
            GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
            GL11.glPixelStorei(GL11.GL_UNPACK_ROW_LENGTH, 0);
            GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_PIXELS, 0);
            GL11.glPixelStorei(GL11.GL_UNPACK_SKIP_ROWS, 0);

            // 4. Set texture parameters (Clean pixel art configuration)
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            // 5. Upload via NativeImage's raw memory address pointer using standard OpenGL bindings
            // Minecraft's NativeImage uses an ABGR/RGBA memory layout that maps natively
            // to standard GL_RGBA components on modern systems.
            GL11.glTexImage2D(
                    GL11.GL_TEXTURE_2D,
                    0,
                    GL11.GL_RGBA,
                    nativeImage.getWidth(),
                    nativeImage.getHeight(),
                    0,
                    GL11.GL_RGBA,
                    GL11.GL_UNSIGNED_BYTE,
                    nativeImage.getPointer() // Streams direct memory bits safely to the GPU
            );

            // 6. Free the heap/native allocation to prevent long-term memory leaks
            nativeImage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return textureId;
    }
}