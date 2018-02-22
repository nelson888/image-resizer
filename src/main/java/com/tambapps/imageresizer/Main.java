package com.tambapps.imageresizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * source: https://stackoverflow.com/questions/12620158/save-resized-image-java
 * WORKS ONLY FOR JPG FILES
 */
public class Main {

  public static void main(String[] args) {
    if (args.length < 3) {
      System.out.println("You must provide the new dimensions and files or directory");
      return;
    }
    final int w, h;
    try {
      w = Integer.parseInt(args[0]);
      h = Integer.parseInt(args[1]);
    } catch (NumberFormatException exception) {
      return;
    }

    for (int i=2; i< args.length; i++) {
      String path = args[i];
      File file = new File(path);
      if (!file.exists()) {
        System.out.println("The following path doesn't exist: " + path);
        continue;
      }
      if (file.isDirectory()) {
        resizePaths(file.listFiles(), w, h);
      } else {
        resizeImage(path, w, h);
      }
    }

  }

  private static void resizePaths(File[] files, int w, int h) {
    for (File file : files) {
      String path = file.getPath();
      if (!file.exists()) {
        System.out.println("The following path doesn't exist: " + path);
        continue;
      }
      resizeImage(path, w, h);
    }
  }

  private static void resizeImage(String path, int w, int h) {
    try {

      BufferedImage originalImage = ImageIO.read(new File(path));
      int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

      BufferedImage resizeImageJpg = resizedImage(originalImage, type, w, h);
      ImageIO.write(resizeImageJpg, "jpg", new File(path)); //change path where you want it saved
    } catch (IOException e) {
      System.out.println("Failed to resize image: " + path);
    }
  }
  private static BufferedImage resizedImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
    g.dispose();

    return resizedImage;
  }
}
