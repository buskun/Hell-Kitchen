package utility.loader;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

public class AudioLoader extends ResourceLoader<AudioClip> {
    public AudioLoader(Runnable onStartLoading, Runnable onLoaded) {
        super(onStartLoading, onLoaded, (File file) -> {
            try {
                return Applet.newAudioClip(file.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return null;
        }, new AudioClip() {
            @Override
            public void play() { }

            @Override
            public void loop() { }

            @Override
            public void stop() { }
        });
    }
}
