package com.characters;

import com.Main.GamePanel;
import com.Main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Player extends Entity {
    // game panel
    GamePanel gp;
    // key handler
    KeyHandler keyH;

    // coordonner de la camera
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    // constructor de player
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // camera
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        // hit-box du personage
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * method valeur par default du player
     */
    public void setDefaultValues() {
        // coordonner du spawn, vitesse et direction
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    // method qui va cherchez les sprites du personage
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\player\\right2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *     method update
     */

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed){

            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else {
                direction = "right";
            }
            // la collision par default est false
            collisionOn = false;
            gp.cChecker.checkTitle(this);
            // Check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            // traitement de la collision
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            // alternate sprite counter
            spritCounter++;
            if (spritCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spritCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if(i!=999){
            String objectName = gp.obj[i].name;
            switch (objectName) {
                case "key" -> {
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("key: " + hasKey);
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("key: " + hasKey);
                }
                case "Boots" -> {
                    speed += 1;
                    gp.obj[i]=null;
                }
            }
        }
    }

    // method draw des sprites du player
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        // draw du player
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
