package org.cobra;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameArea extends BufferedImage {
  private static final Color FIRST_COLOR = new Color(65, 215, 245);
  private static final Color SECOND_COLOR = new Color(25, 195, 115);
  private final int tileBorderLength;
  private final int tilesInRow;

  private GameArea(int length, int tilesInRow) {
    super(length, length, BufferedImage.TYPE_INT_RGB);
    this.tileBorderLength = length / tilesInRow;
    this.tilesInRow = tilesInRow;
  }

  public static GameArea newGameArea(int bufferWidth, int bufferHeight, int tilesInRow) {
    int length = Math.min(bufferHeight, bufferWidth);
    return new GameArea(length, tilesInRow);
  }

  public void update() {
    Graphics2D imageGraphics = this.createGraphics();
    imageGraphics.setBackground(Color.BLACK);
    imageGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
    for (int row = 0; row < tilesInRow; row++) {
      for (int col = 0; col < tilesInRow; col++) {
        if ((row + col) % 2 == 0) {
          imageGraphics.setColor(FIRST_COLOR);
        } else {
          imageGraphics.setColor(SECOND_COLOR);
        }
        imageGraphics.fillRect(
            col * tileBorderLength, row * tileBorderLength, tileBorderLength, tileBorderLength);
      }
    }
  }
}
