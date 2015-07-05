import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Created by guasacaca on 03/07/15.
 */
public class ProcessImage {

    public static List<Color> getPixels(String path){
        BufferedImage img = null;
        List<Color> pixels = null;
        try {
            img = ImageIO.read(new File(path));
            pixels = convertTo2DUsingGetRGB(img);

        } catch (IOException e) {
        }
        return pixels;
    }

    private static List<Color> convertTo2DUsingGetRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] result = new int[width][height];
        List<Color> pixels = new ArrayList<Color>();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
               try {
                   result[i][j] = image.getRGB(i, j);
                   Color color = new Color(result[i][j]);
                   pixels.add(color);
               }catch (ArrayIndexOutOfBoundsException e){
                  System.out.println("OOBE");
               }
            }
        }
        return pixels;
    }
}