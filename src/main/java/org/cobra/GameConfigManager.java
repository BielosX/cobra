package org.cobra;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class GameConfigManager {
  private GameConfig gameConfig;
  private final File gameConfigFile = new File("config.json");
  private final ObjectMapper objectMapper = new ObjectMapper();

  public void createIfNotExist() throws IOException {
    if (!gameConfigFile.exists()) {
      this.gameConfig = GameConfig.defaults();
      try (OutputStream out = new BufferedOutputStream(new FileOutputStream(gameConfigFile))) {
        objectMapper.writeValue(out, gameConfig);
      }
    }
  }

  public GameConfig loadConfig() throws IOException {
    if (gameConfig == null) {
      try (InputStream in = new FileInputStream(gameConfigFile)) {
        gameConfig = objectMapper.readValue(in, GameConfig.class);
      }
    }
    return gameConfig;
  }

  public record WindowConfig(int width, int height, boolean fullscreen) {
    public static WindowConfig defaults() {
      return new WindowConfig(800, 600, false);
    }
  }

  public record GameConfig(WindowConfig windowConfig) {
    public static GameConfig defaults() {
      return new GameConfig(WindowConfig.defaults());
    }
  }
}
