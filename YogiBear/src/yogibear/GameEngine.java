/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Shokhjakhon
 */
public class GameEngine extends JPanel {
    
    private final int FPS = 240;
    private final int CHAR_Y = 480;
    private final int CHAR_WIDTH = 25;
    private final int CHAR_HEIGHT = 25;
    private final double CHAR_MOVEMENT = 4;
    
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private Image background;
    private int levelNum = 0;
    private int score = 0;
    private int lives = 3;
    private Level level;
    private Database db;
    private Character player;
    private Timer newFrameTimer;
    
    public GameEngine() {
        super();
        background = new ImageIcon("data/images/background.jpg").getImage();
        db = new Database();
        restart();
        
        
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }
    
    public ArrayList<HighScore> getHighScores() {
        return db.getHighScores();
    }
    
    /**
     * This starts or restarts the game level
     */
    public void restart() {
        try {
            level = new Level("data/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image playerImage = new ImageIcon("data/images/yogi.png").getImage();
        player = new Character(390, CHAR_Y, CHAR_WIDTH, CHAR_HEIGHT, playerImage);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 825, 625, null);
        level.draw(grphcs);
        player.draw(grphcs);
    }
    
    class NewFrameListener implements ActionListener {

        /**
        * This method checks if any ranger collides with the player object or obstacles object and checks if the level is over.
        */
        @Override
        public void actionPerformed(ActionEvent ae) {
            for (Ranger ranger : level.rangersY) {
                ranger.moveY();
                for (Obstacle obstacle : level.obstacles) {
                    if (ranger.collides(obstacle)) {
                        ranger.invertVelY();
                    }
                }
                if (ranger.collides(player)) {
                    lives--;
                    player.setX(390);
                    player.setY(480);
                }
            }
            for (Ranger ranger : level.rangersX) {
                ranger.moveX();
                for (Obstacle obstacle : level.obstacles) {
                    if (ranger.collides(obstacle)) {
                        ranger.invertVelX();
                    }
                }
                if (ranger.collides(player)) {
                    lives--;
                    player.setX(390);
                    player.setY(480);
                }
            }
            if (lives == 0) {
                String name = JOptionPane.showInputDialog("Enter your name to save your score to table ");
                if (name != null || name == "") {
                    db.storeHighScore(name, score);
                }
                lives = 3;
                score = 0;
                restart();
            }
            if (level.isOver()) {
                levelNum = (levelNum+1) % 10;
                restart();
            }
            repaint();
        }

    }
    
    /**
     * This method checks what keys are pressed and applies changes to the velocity of the player and moves it
     * @param e pressed key
     */
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        switch (k) {
            case KeyEvent.VK_LEFT -> {
                player.setVelx(-CHAR_MOVEMENT);
                left = true;
            }
            case KeyEvent.VK_RIGHT -> {
                player.setVelx(CHAR_MOVEMENT);
                right = true;
            }
            case KeyEvent.VK_UP -> {
                player.setVely(-CHAR_MOVEMENT);
                up = true;
            }
            case KeyEvent.VK_DOWN -> {
                player.setVely(CHAR_MOVEMENT);
                down = true;
            }
        }
        if (left && up) {
            player.setVely(-CHAR_MOVEMENT+2);
            player.setVelx(-CHAR_MOVEMENT+2);
            player.moveX();
            player.moveY();
        }
        if (left && down) {
            player.setVely(CHAR_MOVEMENT-2);
            player.setVelx(-CHAR_MOVEMENT+2);
            player.moveX();
            player.moveY();
        } 
        if (right && up) {
            player.setVely(-CHAR_MOVEMENT+2);
            player.setVelx(CHAR_MOVEMENT-2);
            player.moveX();
            player.moveY();
        }
        if (right && down) {
            player.setVely(CHAR_MOVEMENT-2);
            player.setVelx(CHAR_MOVEMENT-2);
            player.moveX();
            player.moveY();
        }
        if (left) {
            player.moveX();
        }
        if (right) {
            player.moveX();
        }
        if (up) {
            player.moveY();
        }
        if (down) {
            player.moveY();
        }
        if (level.collides(player)) {
            score++;
        }
    }
    
    /**
     * This method checks what keys are released and applies changes to the velocity of the player and moves it
     * @param e released key
     */
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        
        switch (k) {
            case KeyEvent.VK_LEFT -> {
                player.setVelx(0);
                left = false;
            }
            case KeyEvent.VK_RIGHT -> {
                player.setVelx(0);
                right = false;
            }
            case KeyEvent.VK_UP -> {
                player.setVely(0);
                up = false;
            }
            case KeyEvent.VK_DOWN -> {
                player.setVely(0);
                down = false;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    
}
