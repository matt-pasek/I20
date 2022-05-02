package com.company.classes;

import com.company.Constants;
import com.company.GameField;
import com.company.classes.monsters.MonstersFactory;

import javax.swing.*;
import java.awt.*;

public class MonsterBase {

        private int x,y,health;
        public Image image;
        public GameField gameField;



        public MonsterBase(int x, int y){
            this.x = x;
            this.y = y;
            this.health = 200;
            String baseImage = Constants.IMG_FOLDER + "/monster/1.png";
            this.image = new ImageIcon(baseImage).getImage();
        }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealthPoints() {
        return health;
    }
}

