package model;

import view.OpenGLRenderer;

public class HealthBar {
    private float x, y;  // Vị trí bắt đầu của thanh máu
  //  private int maxHealth = 5; // Tổng số cục máu (5 cục)
    private int currentHealth; // Số lượng cục máu hiện tại
    private float width = 15; // Chiều rộng của mỗi cục máu
    private float height = 10; // Chiều cao của mỗi cục máu

    ParallelogramRenderer parallelogramRenderer;

    public HealthBar(float x, float y, int maxHealth) {
        this.x = x;
        this.y = y;
        this.currentHealth = maxHealth; // Mặc định bắt đầu với đầy đủ máu
    }

    public void drawHealthBar() {
        float currentX = x;
        for (int i = 0; i < currentHealth; i++) {
            drawBloodCell(currentX, y);  // Vẽ mỗi cục máu
            currentX += width +4;  // Cộng thêm khoảng cách giữa các cục máu
        }
    }

    private void drawBloodCell(float x, float y) {
        parallelogramRenderer = new ParallelogramRenderer(x, y, width, height, 20, 0.0f, 1.0f, 0.0f);

        parallelogramRenderer.drawParallelogram();
//        GL11.glVertex2f(x, y);  // Góc dưới trái
//        GL11.glVertex2f(x + width, y);  // Góc dưới phải
//        GL11.glVertex2f(x + width, y + height);  // Góc trên phải
//        GL11.glVertex2f(x, y + height);  // Góc trên trái
//        GL11.glEnd();
    }

    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;  // Giảm đi một cục máu
        }
    }

    public void move2(int direction, float speed) {
        // direction 1:A, 2:D, 3:S, 4:W
        if (direction == 1) {
            if (x-200> 0) {
                x -= speed;
            }
        } else if (direction == 2) {
            if (x+20< OpenGLRenderer.windowWidth) {
                x += speed;
            }
        } else if (direction == 3) {
            if (y+80 <OpenGLRenderer.windowHeight) {
                y += speed;
            }
        } else {
            if (y-80 > 0f) {
                y -= speed;
            }
        }
    }
    public boolean isDead() {
        return currentHealth <= 0;  // Kiểm tra nếu thanh máu đã hết
    }
}
