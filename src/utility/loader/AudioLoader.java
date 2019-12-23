package utility.loader;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioLoader extends ResourceLoader<Clip> {
    public AudioLoader(Runnable onStartLoading, Runnable onLoaded) {
        super(onStartLoading, onLoaded, (File file) -> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file.getAbsoluteFile()));
                return clip;
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }

            return null;
        }, new Clip() {
            @Override
            public Line.Info getLineInfo() { return null; }

            @Override
            public void open() throws LineUnavailableException { }

            @Override
            public void close() { }

            @Override
            public boolean isOpen() { return false; }

            @Override
            public Control[] getControls() { return new Control[0]; }

            @Override
            public boolean isControlSupported(Control.Type control) { return false; }

            @Override
            public Control getControl(Control.Type control) { return null; }

            @Override
            public void addLineListener(LineListener listener) { }

            @Override
            public void removeLineListener(LineListener listener) { }

            @Override
            public void open(AudioFormat format, byte[] data, int offset, int bufferSize) throws LineUnavailableException { }

            @Override
            public void open(AudioInputStream stream) throws LineUnavailableException, IOException { }

            @Override
            public int getFrameLength() { return 0; }

            @Override
            public long getMicrosecondLength() { return 0; }

            @Override
            public void setFramePosition(int frames) { }

            @Override
            public void setMicrosecondPosition(long microseconds) { }

            @Override
            public void setLoopPoints(int start, int end) { }

            @Override
            public void loop(int count) { }

            @Override
            public void drain() { }

            @Override
            public void flush() { }

            @Override
            public void start() { }

            @Override
            public void stop() { }

            @Override
            public boolean isRunning() { return false; }

            @Override
            public boolean isActive() { return false; }

            @Override
            public AudioFormat getFormat() { return null; }

            @Override
            public int getBufferSize() { return 0; }

            @Override
            public int available() { return 0; }

            @Override
            public int getFramePosition() { return 0; }

            @Override
            public long getLongFramePosition() { return 0; }

            @Override
            public long getMicrosecondPosition() { return 0; }

            @Override
            public float getLevel() { return 0; }
        });
    }
}
