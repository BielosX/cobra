package org.cobra;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu {
  private static final String TITLE = "Cobra";
  private static final String START_GAME = "Start Game";
  private final BufferedImage image;
  private final FontProvider fontProvider;

  public MainMenu(int width, int height, FontProvider fontProvider) {
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    this.fontProvider = fontProvider;
  }

  public void draw(Graphics2D graphics) {
    Graphics2D imageGraphics = image.createGraphics();
    imageGraphics.setBackground(new Color(0, 0, 0, 0));
    imageGraphics.clearRect(0, 0, image.getWidth(), image.getHeight());
    imageGraphics.setFont(fontProvider.getTitleFont());
    imageGraphics.setColor(Color.WHITE);
    int width = image.getWidth();
    int stringWidth = imageGraphics.getFontMetrics().stringWidth(TITLE);
    imageGraphics.drawString(TITLE, (width - stringWidth) >> 1, 100);
    imageGraphics.setFont(fontProvider.getMenuFont());
    imageGraphics.setColor(Color.WHITE);
    stringWidth = imageGraphics.getFontMetrics().stringWidth(START_GAME);
    imageGraphics.drawString(START_GAME, (width - stringWidth) >> 1, 300);
    graphics.drawImage(image, 0, 0, null);
  }
}
