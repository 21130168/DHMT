package model;

import view.OpenGLRenderer;

import java.util.ArrayList;

public class Jet {
    private float x, y;
    private float height, width;
    private float speed;
    private int health;
    private ArrayList<Bullet> bullets;
    private long lastBulletTime ;
    private  boolean top,bottom,left,right;
    private ArrayList<OpenGLRenderer.Particle> particles;

    public Jet(float x, float y, float height, float width, float speed,int health, long lastBulletTime, boolean top, boolean bottom,boolean left, boolean right) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.speed = speed;
        bullets = new ArrayList<Bullet>();
        particles = new ArrayList<OpenGLRenderer.Particle>();
        this.health = health;
        this.lastBulletTime = lastBulletTime;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
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

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }
    public float getSpeed() {
        return speed;
    }
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void move1(int direction) {
        // direction 1:A, 2:D, 3:S, 4:W
        if (direction == 1) {
            if (x-0.3 > -1.0f) {
                x -= speed;
            }
        } else if (direction == 2) {
            if (x+0.3 < 1.0f) {
                x += speed;
            }
        } else if (direction == 3) {
            if (y-0.15 > -1.0f) {
                y -= speed;
            }
        } else {
            if (y+0.15 < 1.0f) {
                y += speed;
            }
        }
    }
    public void move2(int direction) {
        // direction 1:A, 2:D, 3:S, 4:W
        if (direction == 1) {
            if (x-100> 0) {
                x -= speed;
            }
        } else if (direction == 2) {
            if (x+100 < OpenGLRenderer.windowWidth) {
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

//    public void autoMove(int i){
//        // 1: right, 2: left
//        if(x<0.4 && right){
//            x+=speed;
//            if(x>= 0.1) setRight(false);
//        }
//        if(y<0.9  && top){
//            y+=speed;
//            if(y>=0.7f) setTop(false);
//        }
//        if (y>0.9 && bottom){
//            y-=speed;
//            if(y<=0.7f) setTop(false);
//        }
//        if(x>1 && left){
//            x-=speed;
//            if(x<= 0.2) setRight(false);
//        }
//        else{
//            if(i==1){
//                if (TopDownPlaneDrawing.movingRight) {
//                    x += speed;
//                    if (x >= 0.9f) TopDownPlaneDrawing.movingRight = false; // Đổi hướng
//                } else {
//                    x -= speed;
//                    if (x <= -0.9f) TopDownPlaneDrawing.movingRight= true; // Đổi hướng
//                }
//            }else if (i == 2) {
//                if (TopDownPlaneDrawing.movingLeft) {
//                    x -= speed; // Di chuyển sang trái
//                    if (x <= -0.9f) TopDownPlaneDrawing.movingLeft = false; // Đổi hướng sang phải
//                } else {
//                    x += speed; // Di chuyển sang phải
//                    if (x >= 0.9f) TopDownPlaneDrawing.movingLeft = true; // Đổi hướng sang trái
//                }
//            }
//        }
//
//    }

    public void autoMove2(int i){
        // 1: right, 2: left
        if(x<800 && right){
            x+=speed;
            if(x>= 400) setRight(false);
        }
        if(y>100  && top){
            y-=speed;
            if(y==100f) setTop(false);
        }
        if (y<500 && bottom){
            y+=speed;
            if(y>=200f) setBottom(false);
        }
        if(x>800 && left){
            x-=speed;
            if(x<= 400) setLeft(false);
        }
        else{
            if(i==1){
                if (OpenGLRenderer.movingRight) {
                    x += speed;
                    if (x >= 580f) OpenGLRenderer.movingRight = false; // Đổi hướng
                } else {
                    x -= speed;
                    if (x <= 20f) OpenGLRenderer.movingRight= true; // Đổi hướng
                }
            }else if (i == 2) {
                if (OpenGLRenderer.movingLeft) {
                    x -= speed; // Di chuyển sang trái
                    if (x <= 20f) OpenGLRenderer.movingLeft = false; // Đổi hướng sang phải
                } else {
                    x += speed; // Di chuyển sang phải
                    if (x >= 580f) OpenGLRenderer.movingLeft = true; // Đổi hướng sang trái
                }
            }
        }

    }
    public long getLastBulletTime() {
        return lastBulletTime;
    }

    public void setLastBulletTime(long lastBulletTime) {
        this.lastBulletTime = lastBulletTime;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }
    public boolean isLeft() {
        return left;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public boolean isRight() {
        return right;
    }
    public void setRight(boolean right) {
        this.right = right;
    }

    public ArrayList<OpenGLRenderer.Particle> getParticles() {
        return particles;
    }

    public void setParticles(ArrayList<OpenGLRenderer.Particle> particles) {
        this.particles = particles;
    }

}
