package org.cobra;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game implements KeyListener {
  private final List<GameKeyEvent> keyEvents = new ArrayList<>();
  private float positionX = 0f;
  private float positionY = 0f;
  private float velocityX = 0f;
  private float velocityY = 0f;
  private final MainMenu mainMenu;
  private final GameArea gameArea;
  private final int windowWidth;
  private final int windowHeight;

  public Game(MainMenu mainMenu, GameArea gameArea, int windowWidth, int windowHeight) {
    this.mainMenu = mainMenu;
    this.gameArea = gameArea;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    keyEvents.add(new GameKeyEvent(e.getKeyCode(), KeyAction.PRESSED));
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keyEvents.add(new GameKeyEvent(e.getKeyCode(), KeyAction.RELEASED));
  }

  public void tick(float delta) {
    keyEvents.forEach(
        (event) -> {
          if (event.keyAction == KeyAction.PRESSED) {
            if (event.keyCode() == KeyEvent.VK_A) {
              velocityX = -30.0f;
            }
            if (event.keyCode() == KeyEvent.VK_D) {
              velocityX = 30.0f;
            }
            if (event.keyCode() == KeyEvent.VK_W) {
              velocityY = -30.0f;
            }
            if (event.keyCode() == KeyEvent.VK_S) {
              velocityY = 30.0f;
            }
          }
          if (event.keyAction == KeyAction.RELEASED) {
            if (event.keyCode() == KeyEvent.VK_A || event.keyCode() == KeyEvent.VK_D) {
              velocityX = 0.0f;
            }
            if (event.keyCode() == KeyEvent.VK_W || event.keyCode() == KeyEvent.VK_S) {
              velocityY = 0.0f;
            }
          }
        });
    keyEvents.clear();
    positionX += velocityX * delta;
    positionY += velocityY * delta;
  }

  public void draw(Graphics2D graphics) {
    // mainMenu.update();
    // graphics.drawImage(mainMenu, 0, 0, null);
    gameArea.update();
    int topPadding = (windowHeight - gameArea.getHeight()) >> 1;
    int leftPadding = (windowWidth - gameArea.getWidth()) >> 1;
    graphics.drawImage(gameArea, null, leftPadding, topPadding);
  }

  private enum KeyAction {
    PRESSED,
    RELEASED,
  }

  private record GameKeyEvent(int keyCode, KeyAction keyAction) {}
}
