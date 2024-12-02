package model;

import org.lwjgl.opengl.GL11;
import view.OpenGLRenderer;

public class Bullet {
    private float x,y;
    private float width,height;
    private float speed;
    private float rotation;
    public Bullet(float x, float y, float width,float height,float rotation,float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.speed = speed;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }



    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public void update(int i) {
        if(i == 1){
            y += speed;
        }else if(i == 2){
            y -= speed;
        }
    }
    public void update2(int i){
        if(i == 1){
            y -= speed;
        }else if(i == 2){
            y += speed;
        }
    }
    public boolean isOutOfBounds() {
        return y > 1.0f; // Giới hạn trên màn hình
    }
    public boolean isOutOfBounds2() {
        return y > OpenGLRenderer.windowHeight; // Giới hạn trên màn hình
    }

    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getRotation() {
        return rotation;
    }
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void drawBullet() {
        // Lưu trạng thái hiện tại
        GL11.glPushMatrix();

        // Di chuyển đến tâm viên đạn, xoay, sau đó trả lại vị trí ban đầu
        GL11.glTranslatef(x + width / 2, y + height / 2, 0); // Di chuyển đến tâm
        GL11.glRotatef(rotation, 0, 0, 1);                  // Xoay quanh trục Z
        GL11.glTranslatef(-(x + width / 2), -(y + height / 2), 0); // Di chuyển ngược lại

        // Vẽ thân viên đạn (hình chữ nhật)
        // Vẽ thân viên đạn
        drawBody();

        // Vẽ đầu viên đạn (cung tròn)
        drawPointedHead(x, y + height * 0.7f, width, height * 0.3f);

        // Khôi phục trạng thái
        GL11.glPopMatrix();
    }
    // Vẽ thân viên đạn (hình chữ thang)
    private void drawBody() {
        float bodyHeight = height * 0.7f;  // Thân viên đạn chiếm 70% chiều cao

        // Tính chiều rộng phía dưới (giảm 50%)
        float bottomWidth = width * 0.5f;

        GL11.glEnable(GL11.GL_BLEND); // Bật chế độ hòa trộn alpha
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // Cài đặt chế độ hòa trộn

        GL11.glColor4f(1.0f, 0.0f, 0.0f, 1.0f); // Màu viên đạn
        GL11.glBegin(GL11.GL_QUADS);

        // Top-left (giữ nguyên chiều rộng trên)
        GL11.glVertex2f(x, y + bodyHeight);
        // Top-right (giữ nguyên chiều rộng trên)
        GL11.glVertex2f(x + width, y + bodyHeight);
        // Bottom-right (giảm chiều rộng phía dưới)
        GL11.glVertex2f(x + (width - bottomWidth) / 2 + bottomWidth, y);
        // Bottom-left (giảm chiều rộng phía dưới)
        GL11.glVertex2f(x + (width - bottomWidth) / 2, y);

        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND); // Tắt chế độ hòa trộn alpha
    }

    // Vẽ đầu viên đạn (tam giác)
    private void drawPointedHead(float startX, float startY, float width, float height) {
        float headHeightMultiplier = 3.5f; // Tăng chiều cao đầu đạn lên 1.5 lần
        float adjustedHeight = height * headHeightMultiplier;
        // Tạo đầu nhọn (tam giác)
        GL11.glEnable(GL11.GL_BLEND); // Bật chế độ hòa trộn alpha
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // Cài đặt chế độ hòa trộn

        GL11.glColor4f(1.0f, 0.0f, 0.0f, 2.0f); // Màu viên đạn
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(startX, startY);                    // Góc trái
        GL11.glVertex2f(startX + width, startY);            // Góc phải
        GL11.glVertex2f(startX + width / 2, startY + adjustedHeight); // Đỉnh nhọn
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND); // Tắt chế độ hòa trộn alpha
    }

}
