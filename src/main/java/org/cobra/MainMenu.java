package org.cobra;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu extends BufferedImage {
  private static final String TITLE = "Cobra";
  private static final String START_GAME = "Start Game";
  private static final String EXIT_GAME = "Exit";
  private static final Color GREEN = new Color(40, 185, 45);
  private final FontProvider fontProvider;

  public MainMenu(int width, int height, FontProvider fontProvider) {
    super(width, height, BufferedImage.TYPE_INT_ARGB);
    this.fontProvider = fontProvider;
  }

  public void update() {
    Graphics2D imageGraphics = this.createGraphics();
    imageGraphics.setBackground(new Color(0, 0, 0, 0));
    imageGraphics.clearRect(0, 0, this.getWidth(), this.getHeight());
    imageGraphics.setFont(fontProvider.getTitleFont());
    imageGraphics.setColor(GREEN);
    int width = this.getWidth();
    int stringWidth = imageGraphics.getFontMetrics().stringWidth(TITLE);
    imageGraphics.drawString(TITLE, (width - stringWidth) >> 1, 100);
    imageGraphics.setFont(fontProvider.getMenuFont());
    imageGraphics.setColor(GREEN);
    stringWidth = imageGraphics.getFontMetrics().stringWidth(START_GAME);
    imageGraphics.drawString(START_GAME, (width - stringWidth) >> 1, 300);
  }
}
