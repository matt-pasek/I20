package com.company.classes.monsters;

import com.company.Constants;
import com.company.GameField;
import com.company.classes.CharacterClass;
import com.company.classes.MonsterBase;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class MonstersFactory extends TimerTask{
    public GameField gameField;
    private ArrayList<MonsterBase> monsters;
    public MonstersFactory(GameField gameField, ArrayList<MonsterBase> monsters) {
    this.gameField = gameField;
    this.monsters = monsters;
    }

    /*public MonstersFactory() {

    }*/

    @Override
    public void run(){
        if(this.monsters.size() < 3) {
            Random rnd = new Random();
            int x, y;
            do {
                x = rnd.nextInt(8) * Constants.CHARACTER_IMG_WIDTH;
                y = rnd.nextInt(5) * Constants.CHARACTER_IMG_HEIGHT;
            }while (CharacterClass.occupiedCells[x][y] > 0);
            CharacterClass.occupiedCells[x][y] = 10;
            MonsterBase monster = new MonsterBase(x, y);
            monsters.add(monster);
            gameField.repaint();
        }
    }
}
