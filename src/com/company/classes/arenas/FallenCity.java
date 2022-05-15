package com.company.classes.arenas;

import javax.swing.*;

import com.company.classes.ArenaBase;

public class FallenCity extends ArenaBase {
  public FallenCity() {
    int[][] sth = { { 2 * 40, 1 * 80 }, { 6 * 40, 1 * 80 }, { 2 * 40, 3 * 80 }, { 6 * 40, 3 * 80 } };
    this.wallLocation = sth;
    this.baseImage = new ImageIcon("17.png").getImage();
  }
}
