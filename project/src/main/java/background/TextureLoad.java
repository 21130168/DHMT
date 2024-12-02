package background;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class TextureLoad {
    public static TextureData loadTexture(String filePath) {
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = STBImage.stbi_load(filePath, width, height, channels, 4); // RGBA
        if (image == null) {
            throw new RuntimeException("Failed to load texture: " + STBImage.stbi_failure_reason());
        }

        int textureID = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width.get(0), height.get(0),
                0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        STBImage.stbi_image_free(image);

        return new TextureData(textureID, width.get(0), height.get(0));
    }
}
