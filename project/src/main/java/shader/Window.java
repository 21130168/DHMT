package shader;

import Fonts.CFont;
import Fonts.CharInfo;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.glClear;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private long window;
    private CFont font;

    public Window() {
        init();
        font = new CFont("./src/main/resources/fonts/Roboto-Regular.ttf", 64);
    }

    private void init() {
        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        window = glfwCreateWindow(600, 1000, "Font Rendering", NULL, NULL);
        if (window == NULL) {
            System.out.println("Could not create window.");
            glfwTerminate();
            return;
        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        // Initialize gl functions for windows using GLAD
        GL.createCapabilities();
    }

    public void run() {

        Shader fontShader = new Shader("./src/main/resources/shaders/fontShader.glsl");
        Shader sdfShader = new Shader("./src/main/resources/shaders/sdfShader.glsl");
        Batch batch = new Batch();
        batch.shader = fontShader;
        batch.sdfShader = sdfShader;
        batch.font = font;
        batch.initBatch();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        CharInfo oneQuad = new CharInfo(0, 0, 1, 1);
        oneQuad.calculateTextureCoordinates(1, 1);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT);
            glClearColor(0.1f, 0.09f, 0.1f, 1);

            batch.addText("Game over", 220, 400, 1.2f, 0x00FF00);
            batch.addText("Press esc to exit", 150, 310, 1.2f, 0x00FF00);

            //batch.addCharacter(0, 0, 620.0f, oneQuad, 0xFF4500);
            batch.flushBatch();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args) {
        Window window = new Window();
        window.run();
    }
}
