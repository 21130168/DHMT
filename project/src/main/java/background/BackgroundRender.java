package background;


import Fonts.CFont;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import shader.Batch;
import shader.Shader;

public class BackgroundRender {
    private long window;
    private int windowWidth = 600;
    private int windowHeight = 1000;

    private int textureID;
    private int imageWidth;
    private int imageHeight;

    private float offsetY = 0.0f;  // Biến điều khiển vị trí của ảnh theo trục Y

    CFont font;
    Shader fontShader;
    Shader sdfShader;
    Batch batch;

    boolean isGameOver = true;

    public void run() {
        init();
        loop();

        // Dọn dẹp tài nguyên
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    private void init() {
        // Khởi tạo GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Tạo cửa sổ
        window = GLFW.glfwCreateWindow(windowWidth, windowHeight, "LWJGL Texture Loader", 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        GLFW.glfwMakeContextCurrent(window);
        GLFW.glfwShowWindow(window);

        // Khởi tạo OpenGL
        GL.createCapabilities();

        // Đặt màu nền có alpha (trong suốt)
        GL11.glClearColor(1f, 1f, 1f, 1f);; // Màu trong suốt

        // Tải texture
        TextureData textureData = TextureLoad.loadTexture("./src/main/resources/images/background1.png");
        textureID = textureData.textureID;
        imageWidth = textureData.width;
        imageHeight = textureData.height;

        // Cấu hình viewport OpenGL để khớp kích thước cửa sổ
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, windowWidth, windowHeight, 0, -1, 1); // (0,0) ở góc trên trái
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        if(isGameOver) {
            System.out.println("hello");
            // Khởi tạo font và batch
            font = new CFont("./src/main/resources/fonts/Roboto-Regular.ttf", 64);
            fontShader = new Shader("./src/main/resources/shaders/fontShader.glsl");
            sdfShader = new Shader("./src/main/resources/shaders/sdfShader.glsl");
            batch = new Batch();
            batch.shader = fontShader;
            batch.sdfShader = sdfShader;
            batch.font = font;
            batch.initBatch();
        }
    }

    private void loop() {
        float scaleFactor = 1.2f;


        while (!GLFW.glfwWindowShouldClose(window)) {
            // Xóa màn hình
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Vẽ ảnh với kích thước thực
            renderImage(scaleFactor);
            if(isGameOver) {
                // Vẽ text "Game over" với nền trong suốt
                GL11.glEnable(GL11.GL_BLEND);  // Bật chế độ pha trộn
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);  // Chế độ pha trộn alpha (mặc định)
                // Vẽ text "Game over"
                batch.addText("Game over", 220, 400, 1.2f, 0x00FF00);
                batch.addText("Press esc to exit", 150, 310, 1.2f, 0x00FF00);
                batch.flushBatch();
            }
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }
    }

    private void renderImage(float scaleFactor) {

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f); // Màu ảnh gốc không thay đổi
        // Áp dụng tỷ lệ thu phóng vào kích thước ảnh
        int scaledWidth = (int) (imageWidth * scaleFactor);
        int scaledHeight = (int) (imageHeight * scaleFactor);

        // Vẽ các bản sao ảnh (một ảnh có thể được nhân đôi hoặc nhiều hơn tùy theo chiều cao cửa sổ)
        int numImages = (int) Math.ceil((float) windowHeight / scaledHeight) + 1;  // Tính số lượng ảnh cần vẽ để cuộn đầy màn hình

        for (int i = 0; i < numImages; i++) {
            // Tính toán offset cho mỗi ảnh để tạo hiệu ứng cuộn
            int yOffset = (int) (offsetY + i * scaledHeight);  // Di chuyển ảnh lên xuống theo offsetY

            // Tính toán tọa độ để căn giữa ảnh theo chiều dọc
            int x = (windowWidth - scaledWidth) / 2;
            int y = yOffset;

            // Vẽ hình chữ nhật với kích thước đã thu nhỏ theo tỷ lệ
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glTexCoord2f(0, 0); GL11.glVertex2f(x, y);                            // Bottom-left
            GL11.glTexCoord2f(1, 0); GL11.glVertex2f(x + scaledWidth, y);             // Bottom-right
            GL11.glTexCoord2f(1, 1); GL11.glVertex2f(x + scaledWidth, y + scaledHeight); // Top-right
            GL11.glTexCoord2f(0, 1); GL11.glVertex2f(x, y + scaledHeight);            // Top-left
            GL11.glEnd();
        }

        // Tắt chế độ hòa trộn alpha
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    // Hàm render text
    public void renderText(String text, int x, int y) {

    }

    public static void main(String[] args) {
        new BackgroundRender().run();
    }
}
