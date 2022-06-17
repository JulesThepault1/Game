package com.company;

import com.Main.GamePanel;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        // Creation de la fenetre de jeu
        JFrame window = new JFrame();
        // Arrete le jeu lorsque la fenêtre est fermer
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Titre du jeu
        window.setTitle("2D Game");
        // ajoute le Panel a la fenêtre
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        //
        // location de la fenêtre
        window.setLocationRelativeTo(null);
        // Rendre la fenêtre visible
        window.setVisible(true);
        //
        gamePanel.setupGame();
        // boucle du jeu
        gamePanel.startGameThread();

    }
}

