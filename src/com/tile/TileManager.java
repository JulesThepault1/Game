package com.tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import com.Main.*;

import java.awt.Graphics2D;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        //number of tile
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        //call load map method
        loadMap("..\\res\\maps\\world01.txt");
    }

    public void getTileImage() {

        try {
            //grass
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\grass.png")));
            //wall
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\wall.png")));
            tile[1].collision = true;
            //water
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\water.png")));
            tile[2].collision = true;
            //earth
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\earth.png")));
            //tree
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\tree.png")));
            tile[4].collision = true;
            //sand
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("..\\res\\tiles\\sand.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Load map method
     * @param filePath of the map in res/maps package
     */
    public void loadMap(String filePath) {

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception ignored) {

        }
    }

    public void draw(Graphics2D g2) {

        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldcol][worldrow];

            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;
                worldrow++;
            }
        }
    }
}