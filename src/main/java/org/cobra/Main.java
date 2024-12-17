package org.cobra;

import org.cobra.GameConfigManager.GameConfig;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameConfigManager gameConfigManager = new GameConfigManager();
        gameConfigManager.createIfNotExist();
        GameConfig config = gameConfigManager.loadConfig();
        GameFrame frame = new GameFrame(config);
        frame.setVisible(true);
    }
}
