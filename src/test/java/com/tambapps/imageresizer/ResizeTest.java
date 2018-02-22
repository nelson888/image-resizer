package com.tambapps.imageresizer;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ResizeTest {

  private final File folder = new File(getClass().getClassLoader().getResource("air_jordans_data").getFile());
  private final String path1 = getClass().getClassLoader().getResource("pic_009.jpg").getFile();

  private void resize(final int W, final int H) {
    Main.main(new String[]{String.valueOf(W), String.valueOf(H),
        path1, folder.getAbsolutePath()});
  }

  @Test
  public void testResize() throws IOException {
    resize(288, 198);
    final int W = 275, H = 180;
    resize(W, H);

    assertGoodSize(new File(path1), W, H);

    for (File file : folder.listFiles()) {
      assertGoodSize(file, W, H);
    }
  }

  private void assertGoodSize(File file, int W, int H) throws IOException {
    BufferedImage image = ImageIO.read(file);
    assertEquals(W, image.getWidth());
    assertEquals(H, image.getHeight());
  }
}
