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

  @SneakyThrows
  public Font getTitleFont() {
    if (titleFont == null) {
      try (InputStream stream = getResource("ButterflyKids-Regular.ttf")) {
        assert stream != null;
        titleFont = Font.createFont(Font.TRUETYPE_FONT, stream);
        titleFont = titleFont.deriveFont(Font.PLAIN, 100);
      }
    }
    return titleFont;
  }

  @SneakyThrows
  public Font getMenuFont() {
    if (menuFont == null) {
      try (InputStream stream = getResource("RubikDistressed-Regular.ttf")) {
        assert stream != null;
        menuFont = Font.createFont(Font.TRUETYPE_FONT, stream);
        menuFont = menuFont.deriveFont(Font.PLAIN, 30);
      }
    }
    return menuFont;
  }
}
