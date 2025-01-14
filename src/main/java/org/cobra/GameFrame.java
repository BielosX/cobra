package org.cobra;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import org.cobra.GameConfigManager.GameConfig;
import org.cobra.GameConfigManager.WindowConfig;

public class GameFrame extends Frame {
  private static final int BUFFER_TYPE = BufferedImage.TYPE_INT_RGB;

  private final BufferedImage secondBuffer;
  private final Game game;
  private long time = 0;

  public GameFrame(GameConfig gameConfig) {
    WindowConfig windowConfig = gameConfig.windowConfig();
    setSize(windowConfig.width(), windowConfig.height());
    if (windowConfig.fullscreen()) {
      setExtendedState(Frame.MAXIMIZED_BOTH);
      setUndecorated(true);
    }
    game = new Game(new MainMenu(windowConfig.width(), windowConfig.height(), new FontProvider()));
    addWindowListener(
        new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e) {
            System.exit(0);
          }
        });
    setResizable(false);
    addKeyListener(game);
    secondBuffer = new BufferedImage(windowConfig.width(), windowConfig.height(), BUFFER_TYPE);
  }

  private void secondBufferClearColor(Color color) {
    Graphics2D g2d = secondBuffer.createGraphics();
    g2d.setColor(color);
    g2d.clearRect(0, 0, secondBuffer.getWidth(), secondBuffer.getHeight());
  }

  @Override
  public void paint(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    if (time > 10000000) {
      time = time % 10000000;
      game.tick(0.1f);
    }
    Instant beforeRendering = Instant.now();
    secondBufferClearColor(Color.BLACK);
    Graphics2D bufferGraphics = secondBuffer.createGraphics();
    game.draw(bufferGraphics);
    graphics2d.drawImage(secondBuffer, null, 0, 0);
    time += Duration.between(beforeRendering, Instant.now()).getNano();
    repaint();
  }

  @Override
  public void update(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setBackground(Color.BLACK);
    paint(graphics2d);
  }
}
