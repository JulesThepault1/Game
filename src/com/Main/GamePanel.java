package com.Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import com.characters.Player;
import com.tile.TileManager;
import com.objects.SuperObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;

//Class écran du jeu
public class GamePanel extends JPanel implements Runnable {
    // Screen settings
    // taille par default de écran de jeu
    final int originalTitleSize = 16; //taille standard personage 16x16
    final int scale = 3; // 16x3 augment la taille
    public final int tileSize = originalTitleSize * scale; //48x48

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol; //game screen pixels 768
    public final int screenHeight = tileSize * maxScreenRow; //game screen pixels 768

    // world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    int FPS = 60;
    //
    TileManager tileM = new TileManager(this);

    // instance le keyhandler
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();


    public CollisionChecker cChecker = new CollisionChecker(this);
    //
    public AssetSetter aSetter = new AssetSetter(this);
    //
    // Creation de la boucle du temps du jeu
    Thread gameThread;
    // Instance la class player
    public Player player = new Player(this, keyH);
    // objects 10 slots
    public SuperObjects[] obj = new SuperObjects[10];

    //GamePanel constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // ajoute le keyhandler au game panel
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Game Loop of the game
     * Loop : Update -> rePaint -> Update -> rePaint -> ....
     */
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void setupGame(){
        aSetter.setObjects();
        //playMusic(0);
    }

    /**
     * Update method for the game Loop
     */
    public void update() {
        player.update();
    }

    /**
     * Draw method for the game loop
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //tile
        tileM.draw(g2);
        //object
        for (SuperObjects superObjects : obj) {
            if (superObjects != null) {
                superObjects.draw(g2, this);
            }
        }
        //player
        player.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic (){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();

    }
}