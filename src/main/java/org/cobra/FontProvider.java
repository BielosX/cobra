package org.cobra;

import java.awt.*;
import java.io.InputStream;
import lombok.SneakyThrows;

public class FontProvider {
  private Font titleFont;
  private Font menuFont;

  private InputStream getResource(String path) {
    return this.getClass().getClassLoader().getResourceAsStream(path);
  }

  public Font getTitleFont() {
    if (titleFont == null) {
      titleFont = getFontFromResource("ButterflyKids-Regular.ttf").deriveFont(Font.PLAIN, 100);
    }
    return titleFont;
  }

  public Font getMenuFont() {
    if (menuFont == null) {
      menuFont = getFontFromResource("RubikDistressed-Regular.ttf").deriveFont(Font.PLAIN, 30);
    }
    return menuFont;
  }

  @SneakyThrows
  private Font getFontFromResource(String path) {
    try (InputStream stream = getResource(path)) {
      assert stream != null;
      return Font.createFont(Font.TRUETYPE_FONT, stream);
    }
  }
}
