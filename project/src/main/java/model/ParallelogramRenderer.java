package model;

import org.lwjgl.opengl.GL11;

public class ParallelogramRenderer {
    private float x;          // Tọa độ X của góc dưới trái
    private float y;          // Tọa độ Y của góc dưới trái
    private float width;      // Chiều rộng của hình bình hành
    private float height;     // Chiều cao của hình bình hành
    private float angle;      // Góc nghiêng của các cạnh bên (tính từ trục X)
    private float red, green, blue; // Màu của hình bình hành

    public ParallelogramRenderer(float x, float y, float width, float height, float angle, float red, float green, float blue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void drawParallelogram() {
        // Lưu trạng thái hiện tại
        GL11.glPushMatrix();

        // Di chuyển đến vị trí hình bình hành
        GL11.glTranslatef(x, y, 0);

        // Tính toán tọa độ các đỉnh của hình bình hành
        float tanAngle = (float) Math.tan(Math.toRadians(angle));

        // Vẽ hình bình hành
        GL11.glColor4f(red, green, blue, 1.0f); // Đặt màu cho hình bình hành
        GL11.glBegin(GL11.GL_QUADS);  // Vẽ hình chữ nhật (hình bình hành là dạng biến thể của hình chữ nhật)

        // Các đỉnh của hình bình hành
        GL11.glVertex2f(0, 0);  // Góc dưới trái
        GL11.glVertex2f(width, 0);  // Góc dưới phải
        GL11.glVertex2f(width - height * tanAngle, height);  // Góc trên phải
        GL11.glVertex2f(-height * tanAngle, height);  // Góc trên trái

        GL11.glEnd();

        // Khôi phục trạng thái
        GL11.glPopMatrix();
    }
}
