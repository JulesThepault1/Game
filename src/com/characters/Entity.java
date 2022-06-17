package com.characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    // coordonner x et y de la map
    public int worldX, worldY;
    // vitesse du personage
    public int speed;
    // sprites du personage en function de sa direction
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    // direction du personage
    public String direction;
    // sprite counter pour alternate entre les sprites et faire une animation
    public int spritCounter = 0;
    public int spriteNum = 1;
    // hit-box du personages
    public Rectangle solidArea;

    public int solidAreaDefaultX,solidAreaDefaultY;
    // collision du personage
    public boolean collisionOn = false;
}
