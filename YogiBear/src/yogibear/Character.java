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
public class Character extends Sprite {
    
    protected double velx;
    protected double vely;
    
    public Character(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        velx = 1;
        vely = 1;
    }
    
    /**
     * This method moves the object along the x axis
     */
    public void moveX() {
        x += velx;
        if (x + width >= 825 - width/2 || x <= 0) {
            x -= velx;
        }
    }
    
    /**
     * This method moves the object along the y axis
     */
    public void moveY() {
        y += vely;
        if (y + height >= 595 - height || y <= 0) {
            y -= vely;
        }
    }
    
    /**
     * This method inverts the velocity along the x axis
     */
    public void invertVelX() {
        velx = -velx;
    }
    
    /**
     * This method inverts the velocity along the y axis
     */
    public void invertVelY() {
        vely = -vely;
    }

    public double getVelx() {
        return velx;
    }

    public void setVelx(double velx) {
        this.velx = velx;
    }

    public double getVely() {
        return vely;
    }

    public void setVely(double vely) {
        this.vely = vely;
    }
}
