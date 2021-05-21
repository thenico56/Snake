package snake.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import snake.scenes.Window;

public class Sprite {
    protected int width, height;
    protected int x, y;
    protected int spriteX, spriteY;
    protected Image spriteImage;
    protected static int LEFT = 0;
    protected static int RIGHT = 1;
    protected static int DOWN = 2;
    protected static int UP = 3;

    public Sprite(int width, int height, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public Sprite() {

    }

    //This funtion 
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawObj(GraphicsContext gc) {
        gc.drawImage(spriteImage, x, y, width, height);
    }

    public void move(int movement, GraphicsContext gc, int spriteX, int spriteY) {
        int newX = x;
        int newY = y;
        if (movement == LEFT) {
            newX -= Math.min(1, x);
            this.spriteX = spriteX;
            this.spriteY = spriteY;
        }
        else if (movement == RIGHT) {
            newX += Math.min(1, Window.WIDTH - width - x);
            this.spriteX = spriteX;
            this.spriteY = spriteY;
        }
        else if (movement == DOWN) {
            newY += Math.min(1, Window.HEIGHT - height - y);
            this.spriteX = spriteX;
            this.spriteY = spriteY;
        }
        else if (movement == UP) {
            newY -= Math.min(1, y-165);
            this.spriteX = spriteX;
            this.spriteY = spriteY;
        }
        moveTo(newX, newY);
        drawSpriteObj(gc);
    }

    public void drawSpriteObj(GraphicsContext gc) {
        gc.drawImage(spriteImage, spriteX, spriteY, width, height, x, y, width, height);
    }

    public boolean collision(Sprite s) {
        return this.x > s.x &&
                this.x < s.x + s.width/2 &&
                this.y > s.y &&
                this.y < s.y + s.height/2;
    }

    public boolean collisionMap() {
        return this.x == Window.WIDTH -115 || this.x == 5 || this.y == Window.HEIGHT -120 || this.y == 170;
    }
}
