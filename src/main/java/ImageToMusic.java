/**
 * Created by guasacaca on 04/07/15.
 */
public class ImageToMusic {

    static int minFreq = 50;
    static int maxFreq = 300;
    static int base = (maxFreq-minFreq)/3;

    public ImageToMusic(){

    }

    public static int fromImageToMusic(int red, int green, int blue){

        int redMod = red%base;
        int blueMod = blue%base;
        int greenMod = green%base;

        return redMod+blueMod+greenMod+50;
    }
}
