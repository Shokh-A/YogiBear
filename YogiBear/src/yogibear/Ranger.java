/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yogibear;

import java.awt.Image;

/**
 *
 * @author Shokhjakhon
 */
public class Ranger extends Character {

    public Ranger(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
    
    /**
     * This method moves the object and inverts the velocity along the x axis
     * if object gets to the borders of the gameArea
     */
    @Override
    public void moveX() {
        x += velx;
        if (x + width >= 825 || x <= 0) {
            invertVelX();
        }
    }
    
    /**
     * This method moves the object and inverts the velocity along the y axis
     * if object gets to the borders of the gameArea
     */
    @Override
    public void moveY() {
        y += vely;
        if (y + height >= 605 - height|| y <= 0) {
            invertVelY();
        }
    }
}
