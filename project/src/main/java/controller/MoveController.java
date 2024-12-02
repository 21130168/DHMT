package controller;


import view.OpenGLRenderer;

import static org.lwjgl.glfw.GLFW.*;

public class MoveController {
    public void processMoveInput() {

        // Di chuyển sang phải (phím D) nếu chưa vượt qua ranh giới bên phải
        if (glfwGetKey(OpenGLRenderer.window, GLFW_KEY_D) == GLFW_PRESS) {
           OpenGLRenderer.playerJet.move2(2);
           OpenGLRenderer.playerHeathBar.move2(2, OpenGLRenderer.playerJet.getSpeed() );
        }
        // Di chuyển sang trái (phím A) nếu chưa vượt qua ranh giới bên trái
        else if (glfwGetKey(OpenGLRenderer.window, GLFW_KEY_A) == GLFW_PRESS) {

            OpenGLRenderer.playerJet.move2(1);
            OpenGLRenderer.playerHeathBar.move2(1, OpenGLRenderer.playerJet.getSpeed() );

        }
        // Di chuyển lên trên (phím W) nếu chưa vượt qua ranh giới trên
        else if (glfwGetKey(OpenGLRenderer.window, GLFW_KEY_W) == GLFW_PRESS) {
            OpenGLRenderer.playerJet.move2(4);
            OpenGLRenderer.playerHeathBar.move2(4, OpenGLRenderer.playerJet.getSpeed() );

        }
        // Di chuyển xuống dưới (phím S) nếu chưa vượt qua ranh giới dưới
        else if (glfwGetKey(OpenGLRenderer.window, GLFW_KEY_S) == GLFW_PRESS) {
            OpenGLRenderer.playerJet.move2(3);
            OpenGLRenderer.playerHeathBar.move2(3, OpenGLRenderer.playerJet.getSpeed() );

        }
    }
}
