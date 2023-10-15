/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Shokhjakhon
 */
public class Level {
    
    private final int OBSTACLE_WIDTH = 40;
    private final int OBSTACLE_HEIGHT = 40;
    ArrayList<Basket> baskets;
    ArrayList<Obstacle> obstacles;
    ArrayList<Ranger> rangersY;
    ArrayList<Ranger> rangersX;
    
    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }
    
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        baskets = new ArrayList<>();
        obstacles = new ArrayList<>();
        rangersY = new ArrayList<>();
        rangersX = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char blockType : line.toCharArray()) {
                if (java.lang.Character.isDigit(blockType)) {
                    if (java.lang.Character.getNumericValue(blockType) == 1) {
                        Image image = new ImageIcon("data/images/donut.png").getImage();
                        baskets.add(new Basket(x * OBSTACLE_WIDTH + 5, y * OBSTACLE_HEIGHT + 5, OBSTACLE_WIDTH-10, OBSTACLE_HEIGHT-10, image));
                    }
                    if (java.lang.Character.getNumericValue(blockType) == 2) {
                        Image image = new ImageIcon("data/images/brick09.png").getImage();
                        obstacles.add(new Obstacle(x * OBSTACLE_WIDTH, y * OBSTACLE_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, image));
                    }
                    if (java.lang.Character.getNumericValue(blockType) == 3) {
                        Image image = new ImageIcon("data/images/ranger.png").getImage();
                        rangersY.add(new Ranger(x * OBSTACLE_WIDTH, y * OBSTACLE_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, image));
                    }
                    if (java.lang.Character.getNumericValue(blockType) == 4) {
                        Image image = new ImageIcon("data/images/ranger.png").getImage();
                        rangersX.add(new Ranger(x * OBSTACLE_WIDTH, y * OBSTACLE_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT, image));
                    }
                }
                x++;
            }
            y++;
        }
    }
    
    /**
     * This method checks if the player collides with other objects
     * @param player Player object that may collide with other objects
     * @return Boolean return true if player collides with the Basket, otherwise - false
     */
    public boolean collides(Character player) {
        Basket collidedWith = null;
        for (Basket basket : baskets) {
            if (player.collides(basket)) {
                collidedWith = basket;
                break;
            }
        }
       
        for (Obstacle obstacle : obstacles) {
            if (player.collides(obstacle)) {
                if (player.getX() >= obstacle.getX() + obstacle.width/2) {
                    player.setX((int) (player.getX()-(player.getVelx()-3)));
                } else if (player.getX() + player.width/2 <= obstacle.getX()) {
                    player.setX((int) (player.getX()-(player.getVelx()+3)));
                }
                
                if (player.getY() >= obstacle.getY() + obstacle.height/2) {
                    player.setY((int) (player.getY()-(player.getVely()-3)));
                } else if (player.getY() + obstacle.height/2 <= obstacle.getY()) {
                    player.setY((int) (player.getY()-(player.getVely()+3)));
                } 
            }
        }
        
        if (collidedWith != null) {
            baskets.remove(collidedWith);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * This method checks if baskets array is empty
     * @return Boolean returns true if baskets array is empty
     */
    public boolean isOver() {
        return baskets.isEmpty();
    }

    public void draw(Graphics g) {
        for (Basket basket : baskets) {
            basket.draw(g);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (Ranger ranger : rangersY) {
            ranger.draw(g);
        }
        for (Ranger ranger : rangersX) {
            ranger.draw(g);
        }
    }
}
