package com.company;

import com.company.classes.AttackType;
import com.company.classes.CharacterClass;
import com.company.classes.MonsterBase;
import com.company.classes.monsters.MonstersFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;

public class GameField extends JPanel {
    private Team team;
    private CharacterClass[] players;
    private ArrayList<MonsterBase> monsters = new ArrayList<>();

    public GameField(Team team) {
        this.team = team;
        this.players = team.getTeamMembers();

        setFocusable(true);
        addKeyListener(new FieldKeyListener());

        Timer timer = new Timer();
        MonstersFactory monstersFactory = new MonstersFactory(this, monsters);
        timer.schedule(monstersFactory, 0, 2000);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CharacterClass player : players) {
            if (player.getHealthPoints() > 0) {
                g.drawImage(player.getImage(), player.getX(), player.getY(), this);
                g.drawString("" + player.getHealthPoints(), player.getX(), player.getY() + 12);
                g.drawString("∞", player.getX(), player.getY() + 26);
            } else {
            }
        }
        for (MonsterBase monster : monsters) {
            if (monster.getHealthPoints() > 0) {
                g.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
                g.drawString("" + monster.getHealthPoints(), monster.getX(), monster.getY() + 12);
                g.drawString("∞", monster.getX(), monster.getY() + 26);
            } else {
            }
        }
    }

    public class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            for (CharacterClass player : players) {
                if (key == player.leftKey) {
                    player.left();
                }
                if (key == player.rightKey) {
                    player.right();
                }
                if (key == player.upKey) {
                    player.up();
                }
                if (key == player.downKey) {
                    player.down();
                }
                if (key == player.leftAttackKey) {
                    player.setAttackLeftImage();
                    if (player.getX() >= Constants.CHARACTER_IMG_WIDTH) {
                        if (player.getAttackType() == AttackType.MAGICAL_RANGED
                                || player.getAttackType() == AttackType.PHYSICAL_RANGED) {
                            int neighbourId = CharacterClass.occupiedCells[player.getX() - Constants.CHARACTER_IMG_WIDTH
                                    - 40][player.getY()];
                            // System.out.println(neighbourId);
                            if (neighbourId > 0) {
                                player.attack(players[neighbourId - 1]);
                            }
                        }
                        int neighbourId = CharacterClass.occupiedCells[player.getX()
                                - Constants.CHARACTER_IMG_WIDTH][player.getY()];
                        // System.out.println(neighbourId);
                        if (neighbourId > 0) {
                            player.attack(players[neighbourId - 1]);
                        }
                    }

                    // timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200);
                }
                if (key == player.rightAttackKey) {
                    player.setAttackRightImage();
                    if (player.getX() < Constants.MAX_RIGHT_POSITION) {
                        if (player.getAttackType() == AttackType.MAGICAL_RANGED
                                || player.getAttackType() == AttackType.PHYSICAL_RANGED) {
                            int neighbourId = CharacterClass.occupiedCells[player.getX() + Constants.CHARACTER_IMG_WIDTH
                                    + 40][player.getY()];
                            // System.out.println(neighbourId);
                            if (neighbourId > 0) {
                                player.attack(players[neighbourId - 1]);
                            }
                        }

                        int neighbourId = CharacterClass.occupiedCells[player.getX()
                                + Constants.CHARACTER_IMG_WIDTH][player.getY()];
                        // System.out.println(neighbourId);
                        if (neighbourId > 0) {
                            player.attack(players[neighbourId - 1]);
                        }
                    }
                    // timer
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    player.setBaseImage();
                                    repaint();
                                }
                            }, 200);
                }
            }
            repaint();
        }
    }
}
