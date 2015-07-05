import java.awt.*;
import java.util.List;

/**
 * Created by guasacaca on 04/07/15.
 */
public class LetsPaint {

    static int vol = 100;
    static int dur = 750;//in ms

    public static void main(String[] args) throws InterruptedException {
       //String pathToPic = "colorido.jpg";
       // String pathToPic = "Psy.jpeg";
        String pathToPic = "loop.jpg";
        if(args.length == 1){
            pathToPic = args[0];
        }
        System.out.println("Using file in: "+pathToPic);
        List<Color> pixels = ProcessImage.getPixels(pathToPic);
        System.out.println("Nr of pixels: "+pixels.size());
        for(Color pixel : pixels){
            ImageToMusic im = new ImageToMusic();
            int freq = im.fromImageToMusic(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
            System.out.println("RGB: ("+pixel.getRed()+", "+pixel.getGreen()+", "+pixel.getBlue()+"). Mapped to freq: "+freq);
            SoundGenerator.play(freq, vol, dur);
            Thread.sleep(dur);
        }
    }
}
