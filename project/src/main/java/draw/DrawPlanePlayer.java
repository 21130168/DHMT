package draw;

import static org.lwjgl.opengl.GL11.*;

public class DrawPlanePlayer {
    public  void drawPlanePlayer(float x, float y ) {
        drawPlaneBody(x,y);  // Vẽ phần thân
        drawPlaneHead(x, y);
        drawPilot(x, y);
        drawPlaneWings4(x, y);
        drawPlaneWings2(x,y);
        drawPlaneWings(x,y); // Vẽ cánh
        drawPlaneWings3(x, y);

    }
    private void drawPlaneHead(float xPoint, float yPoint) {
        int segments = 10; // Số đoạn của cung tròn
        float radiusX = 10f; // Bán kính chiều ngang (giữ nguyên)
        float radiusY = 15f; // Bán kính chiều dọc (giữ nguyên)
        float cx = xPoint; // Tọa độ x của tâm cung tròn
        float cy =  yPoint - 48; // Tọa độ y của tâm

        glBegin(GL_TRIANGLE_FAN);
        glColor3f(0.6f, 0.6f, 0.6f); // Màu xám cho phần đầu
        glVertex2f(cx, cy);  // Vẽ điểm trung tâm của cung tròn

        for (int i = 0; i <= segments; i++) {
            // Tính góc và điều chỉnh bán kính chiều dọc theo góc để làm phần tròn nhọn hơn
            float angle = (float) (Math.PI * (i / (float)segments)); // Tính góc theo đoạn

            // Điều chỉnh bán kính chiều dọc để làm cho phần tròn nhọn hơn ở trên
            float adjustedRadiusY = radiusY * (float) Math.pow(Math.sin(angle), 2);  // Giảm độ cong ở phần trên

            // Tính tọa độ x và y của các điểm vẽ phần tròn
            float x = (float) (cx + radiusX * Math.cos(angle)); // Tọa độ x
            float y = (float) (cy - adjustedRadiusY * Math.sin(angle)); // Tọa độ y thay đổi theo điều chỉnh

            // Vẽ điểm trên cung tròn
            glVertex2f(x, y);
        }
        glEnd();
    }




    private void  drawPlaneBody(float xPoint, float yPoint) {
        int segments = 30; // Số đoạn của cung tròn
        float bodyRadiusX = 10f; // Bán kính chiều ngang của phần thân giống phần đầu
        float bodyRadiusY = 60f; // Bán kính chiều dọc của phần thân giống phần đầu
        float cx = xPoint; // Tọa độ x của tâm hình elip
        float cy = yPoint; // Tọa độ y của phần thân (đưa phần thân gần đầu hơn)

        // Vẽ phần thân (elip với phần trên và dưới nhọn hơn)
        glBegin(GL_TRIANGLE_FAN);
        glColor3f(0.4f, 0.4f, 0.4f); // Màu xám cho phần thân
        glVertex2f(cx, cy);  // Vẽ điểm trung tâm của cung tròn

        // Vẽ hình elip với phần trên và dưới nhọn hơn
        for (int i = 0; i <= segments; i++) {
            // Tính góc cho các điểm trên elip
            float angle = (float) (2 * Math.PI * i / segments);

            // Tính tọa độ x và y
            float x = (float) (cx + bodyRadiusX * Math.cos(angle)); // Bán kính chiều ngang không thay đổi
            float y;

            // Điều chỉnh bán kính chiều dọc (bodyRadiusY) theo góc để làm phần trên và dưới nhọn hơn
            if (Math.abs(Math.cos(angle)) < 0.1) {  // Các điểm gần trục y (trên và dưới)
                y = (float) (cy + bodyRadiusY * Math.sin(angle) * 0f); // Giảm bán kính chiều dọc ở phần trên và dưới
            } else {
                y = (float) (cy + bodyRadiusY * Math.sin(angle)); // Bán kính bình thường cho các điểm khác
            }

            glVertex2f(x, y); // Vẽ điểm trên cung
        }
        glEnd();

    }

    // Vẽ phần thân máy bay (hình elip bình thường, giống phần đầu nhưng nhọn hơn ở trên và dưới)
    private void drawPilot(float xPoint, float yPoint) {
        int segments = 30; // Số đoạn của cung tròn
        float bodyRadiusX = 3f; // Bán kính chiều ngang của phần thân giống phần đầu
        float bodyRadiusY = 8f; // Bán kính chiều dọc của phần thân giống phần đầu
        float cx = xPoint ; // Tọa độ x của tâm hình elip
        float cy =yPoint-30; // Tọa độ y của phần thân (đưa phần thân gần đầu hơn)

        // Vẽ phần thân (elip với phần trên và dưới nhọn hơn)
        glBegin(GL_TRIANGLE_FAN);
        glColor3f(1f, 1f, 1f); // Màu xám cho phần thân
        glVertex2f(cx, cy);  // Vẽ điểm trung tâm của cung tròn

        // Vẽ hình elip với phần trên và dưới nhọn hơn
        for (int i = 0; i <= segments; i++) {
            // Tính góc cho các điểm trên elip
            float angle = (float) (2 * Math.PI * i / segments);

            // Tính tọa độ x và y
            float x = (float) (cx + bodyRadiusX * Math.cos(angle)); // Bán kính chiều ngang không thay đổi
            float y;

            // Điều chỉnh bán kính chiều dọc (bodyRadiusY) theo góc để làm phần trên và dưới nhọn hơn
            if (Math.abs(Math.cos(angle)) < 0.1) {  // Các điểm gần trục y (trên và dưới)
                y = (float) (cy + bodyRadiusY * Math.sin(angle) * 0f); // Giảm bán kính chiều dọc ở phần trên và dưới
            } else {
                y = (float) (cy + bodyRadiusY * Math.sin(angle)); // Bán kính bình thường cho các điểm khác
            }

            glVertex2f(x, y); // Vẽ điểm trên cung
        }
        glEnd();
    }

    // Vẽ cánh máy bay (hình tam giác)
    private void drawPlaneWings(float x, float y) { // 2 cánh bên ngoài
        float shiftXLeft = x+ 0.125f;  // Đẩy cánh trái sang phải một chút
        float shiftXRight = x + -0.124f; // Đẩy cánh phải sang trái một chút
        float shiftY =y + -0.2f;      // Dịch chuyển cả hai cánh xuống dưới một chút

        // Cánh trái - Vẽ phần bên trong
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); // Vẽ phần bên trong
        glBegin(GL_TRIANGLES);
        glColor3f(0.5f, 0.7f, 1.0f);
        glVertex2f(-90 + shiftXLeft, -40f + shiftY);
        glVertex2f(-10 + shiftXLeft, 25f + shiftY);
        glVertex2f(-10 + shiftXLeft, -10f + shiftY);

        glEnd();

        // Cánh trái - Vẽ viền
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); // Vẽ viền
        glBegin(GL_TRIANGLES);
        glColor3f(1.0f, 1.0f, 1.0f); // Màu trắng cho viền
        glVertex2f(-90 + shiftXLeft, -40f + shiftY);
        glVertex2f(-10 + shiftXLeft, 25f + shiftY);
        glVertex2f(-10 + shiftXLeft, -10f + shiftY);
        glEnd();

        // Cánh phải - Vẽ phần bên trong
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL); // Vẽ phần bên trong
        glBegin(GL_TRIANGLES);
        glColor3f(0.5f, 0.7f, 1.0f); // Màu xanh nước biển nhạt cho phần bên trong
        glVertex2f(90 + shiftXRight, -40f + shiftY);
        glVertex2f(10 + shiftXRight, 25f + shiftY);
        glVertex2f(10+ shiftXRight, -10f + shiftY);
        glEnd();
        // Cánh phải - Vẽ viền
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE); // Vẽ viền
        glBegin(GL_TRIANGLES);
        glColor3f(1.0f, 1.0f, 1.0f); // Màu trắng cho viền
        glVertex2f(90 + shiftXRight, -40f + shiftY);
        glVertex2f(10 + shiftXRight, 25f + shiftY);
        glVertex2f(10+ shiftXRight, -10f + shiftY);
        glEnd();

        // Đặt lại chế độ vẽ về mặc định (fill)
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }

    private void drawPlaneWings2(float x, float y) {//cánh trên
        float shiftXLeft =x+ 1f; // Đẩy cánh trái sang phải một chút
        float shiftXRight =x +  -1f; // Đẩy cánh phải sang trái một chút
        float shiftY = y + -0.05f; // Dịch chuyển cả hai cánh xuống dưới một chút

        // Cánh trái
        glBegin(GL_TRIANGLES);
        glColor3f(0.8f, 0.8f, 0.8f); // Màu sáng cho cánh
        glVertex2f(-55 + shiftXLeft, -20f + shiftY); // Dịch chuyển cánh trái xuống
        glVertex2f(-10 + shiftXLeft, 15f + shiftY); // Dịch chuyển cánh trái xuống
        glVertex2f(-10 + shiftXLeft, -35f + shiftY);  // Dịch chuyển cánh trái xuống
        glEnd();

        // Cánh phải
        glBegin(GL_TRIANGLES);
        glColor3f(0.8f, 0.8f, 0.8f); // Màu sáng cho cánh
        glVertex2f(55 + shiftXRight, -20f + shiftY); // Dịch chuyển cánh phải xuống
        glVertex2f(10 + shiftXRight, 15f + shiftY); // Dịch chuyển cánh phải xuống
        glVertex2f(10 + shiftXRight, -35f + shiftY);  // Dịch chuyển cánh phải xuống
        glEnd();
    }

    private void drawPlaneWings3(float x, float y) {// cánh giữa
        float shiftXLeft =x-1; // Đẩy cánh trái sang phải một chút
        float shiftXRight =x+ 1f; // Đẩy cánh phải sang trái một chút
        float shiftY =y+ -0.32f; // Dịch chuyển cả hai cánh xuống dưới một chút

        //Bên trái
        // Nửa dưới (màu xanh)
        glBegin(GL_QUADS);
        glColor3f(0.0f, 0.5f, 1.0f); // Màu xanh
        glVertex2f(-20f+ shiftXLeft, 40f+ shiftY); // Đỉnh dưới bên trái
        glVertex2f(-8f+ shiftXLeft, 40f+ shiftY);  // Đỉnh dưới bên phải
        glVertex2f(-8f+ shiftXLeft, 70f+ shiftY);   // Đỉnh giữa bên phải
        glVertex2f(-20f+ shiftXLeft, 65f+ shiftY);  // Đỉnh giữa bên trái
        glEnd();

// Nửa trên (màu trắng)
        glBegin(GL_QUADS);
        glColor3f(0.8f, 0.8f, 0.8f); // Màu sáng cho cánh
        glVertex2f(-20f+ shiftXLeft, -15f+ shiftY);  // Đỉnh giữa bên trái
        glVertex2f(-8f+ shiftXLeft, -15f+ shiftY);   // Đỉnh giữa bên phải
        glVertex2f(-8f+ shiftXLeft, 40f+ shiftY);   // Đỉnh trên bên phải
        glVertex2f(-20f+ shiftXLeft, 40f+ shiftY);  // Đỉnh trên bên trái
        glEnd();

        //bên phải
        // Nửa dưới (màu xanh)
        glBegin(GL_QUADS);
        glColor3f(0.0f, 0.5f, 1.0f); // Màu xanh
        glVertex2f(20f+ shiftXRight, 40f+ shiftY); // Đỉnh dưới bên trái
        glVertex2f(8f+ shiftXRight, 40f+ shiftY);  // Đỉnh dưới bên phải
        glVertex2f(8f+ shiftXRight, 70f+ shiftY);   // Đỉnh giữa bên phải
        glVertex2f(20f+ shiftXRight, 65f+ shiftY);  // Đỉnh giữa bên trái
        glEnd();

// Nửa trên (màu trắng)
        glBegin(GL_QUADS);
        glColor3f(0.8f, 0.8f, 0.8f); // Màu sáng cho cánh
        glVertex2f(20f+ shiftXRight, -15f+ shiftY);  // Đỉnh giữa bên trái
        glVertex2f(8f+ shiftXRight, -15f+ shiftY);   // Đỉnh giữa bên phải
        glVertex2f(8f+ shiftXRight, 40f+ shiftY);   // Đỉnh trên bên phải
        glVertex2f(20f+ shiftXRight, 40f+ shiftY);  // Đỉnh trên bên trái
        glEnd();

    }
    //cánh dưới
    // Vẽ cánh máy bay (hình tam giác)
    private void drawPlaneWings4(float x, float y) {//2 cánh bên ngoài
        float shiftXLeft =x + 0.115f; // Đẩy cánh trái sang phải một chút
        float shiftXRight =x + -0.115f; // Đẩy cánh phải sang trái một chút
        float shiftY =y + -0.3f; // Dịch chuyển cả hai cánh xuống dưới một chút

        // Cánh trái
        glBegin(GL_TRIANGLES);
        glColor3f(0.4f, 0.4f, 0.4f); // Màu xám cho phần thân
        glVertex2f(-50f + shiftXLeft, 50f + shiftY); // Dịch chuyển cánh trái sang phải
        glVertex2f(-20f + shiftXLeft, 45f + shiftY); // Dịch chuyển cánh trái sang phải
        glVertex2f(-20f + shiftXLeft, 30f + shiftY);  // Dịch chuyển cánh trái sang phải
        glEnd();

        // Cánh phải
        glBegin(GL_TRIANGLES);
        glColor3f(0.4f, 0.4f, 0.4f); // Màu xám cho phần thân
        glVertex2f(50f + shiftXRight, 50f + shiftY); // Dịch chuyển cánh trái sang phải
        glVertex2f(20f + shiftXRight, 45f + shiftY); // Dịch chuyển cánh trái sang phải
        glVertex2f(20f + shiftXRight, 30f + shiftY);  // Dịch chuyển cánh trái sang phải
        glEnd();
    }

}
