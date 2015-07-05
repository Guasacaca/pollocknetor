/**
 * Created by guasacaca on 03/07/15.
 */

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.LineUnavailableException;

import javax.swing.JFrame;

public class SoundGenerator extends JFrame {

    static AudioFormat af;
    static SourceDataLine sdl;

    public static void generateTone(int hz,int msecs, int volume, boolean addHarmonic)
            throws LineUnavailableException {

        float frequency = 44100;
        byte[] buf;
        AudioFormat af;
        if (addHarmonic) {
            buf = new byte[2];
            af = new AudioFormat(frequency,8,2,true,false);
        } else {
            buf = new byte[1];
            af = new AudioFormat(frequency,8,1,true,false);
        }
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for(int i=0; i<msecs*frequency/1000; i++){
            double angle = i/(frequency/hz)*2.0*Math.PI;
            buf[0]=(byte)(Math.sin(angle)*volume);

            if(addHarmonic) {
                double angle2 = (i)/(frequency/hz)*2.0*Math.PI;
                buf[1]=(byte)(Math.sin(2*angle2)*volume*0.6);
                sdl.write(buf,0,2);
            } else {
                sdl.write(buf,0,1);
            }
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    public static void play(int freq, int vol, int dur){
        class ReproduceSound implements Runnable {
            int freq;
            int vol;
            int dur;
            public ReproduceSound(int freq, int vol, int dur){
                this.freq = freq;
                this.vol = vol;
                this.dur = dur;
            }
            public void run() {
                try{
                    generateTone(freq,//Freq (Hz)
                            dur,//Duration (ms)
                            (int) (vol * 1.28),//Volumen
                            false);

                }catch(LineUnavailableException lue){
                    System.out.println(lue);
                }

            }
        };
        Thread t = new Thread(new ReproduceSound(freq, vol, dur));
        t.start();
    }

    public static void main(String[] args){
       play(200, 100, 1000);
    }
}
