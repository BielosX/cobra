package org.cobra;

import java.io.IOException;
import org.cobra.GameConfigManager.GameConfig;

public class Main {
  public static void main(String[] args) throws IOException {
    GameConfigManager gameConfigManager = new GameConfigManager();
    gameConfigManager.createIfNotExist();
    GameConfig config = gameConfigManager.loadConfig();
    GameFrame frame = new GameFrame(config);
    frame.setVisible(true);
  }
}
