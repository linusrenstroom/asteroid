package main;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private Map<String, Clip> sounds = new HashMap<>();
    private SourceDataLine musicLine;
    private volatile boolean musicRunning = false;
    private float musicVolume = 0.0f; // Default "natural" volume

    public void loadSound(String name, String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) throw new Exception("File not found: " + path);

            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream baseAudio = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat baseFormat = baseAudio.getFormat();

            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(), false
            );

            AudioInputStream decodedAudio = AudioSystem.getAudioInputStream(targetFormat, baseAudio);
            DataLine.Info info = new DataLine.Info(Clip.class, targetFormat);
            Clip clip = (Clip) AudioSystem.getLine(info);

            clip.open(decodedAudio);
            sounds.put(name, clip);

        } catch (Exception e) {
            System.err.println("CRITICAL: Failed to load sound " + name);
            e.printStackTrace();
        }
    }

    public void playSound(String name) {
        Clip clip = sounds.get(name);
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void setMusicVolume(float volume) {
        this.musicVolume = volume;
        // If music is already playing, update it immediately
        synchronized (this) {
            if (musicLine != null && musicLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                FloatControl gainControl = (FloatControl) musicLine.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(volume);
            }
        }
    }

    public void playMusic(String path) {
        stopMusic();
        musicRunning = true;

        new Thread(() -> {
            try {
                while (musicRunning) {
                    InputStream is = getClass().getResourceAsStream(path);
                    if (is == null) break;

                    AudioInputStream baseAudio = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
                    AudioFormat baseFormat = baseAudio.getFormat();
                    AudioFormat targetFormat = new AudioFormat(
                            AudioFormat.Encoding.PCM_SIGNED,
                            baseFormat.getSampleRate(), 16,
                            baseFormat.getChannels(), baseFormat.getChannels() * 2,
                            baseFormat.getSampleRate(), false
                    );

                    AudioInputStream din = AudioSystem.getAudioInputStream(targetFormat, baseAudio);
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, targetFormat);

                    synchronized (this) {
                        musicLine = (SourceDataLine) AudioSystem.getLine(info);
                        musicLine.open(targetFormat);

                        // Apply volume before starting
                        if (musicLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                            FloatControl gainControl = (FloatControl) musicLine.getControl(FloatControl.Type.MASTER_GAIN);
                            gainControl.setValue(musicVolume);
                        }

                        musicLine.start();
                    }

                    byte[] buffer = new byte[4096];
                    int nBytesRead = 0;

                    while (musicRunning && (nBytesRead = din.read(buffer, 0, buffer.length)) != -1) {
                        musicLine.write(buffer, 0, nBytesRead);
                    }

                    musicLine.drain();
                    musicLine.stop();
                    musicLine.close();
                    din.close();
                }
            } catch (Exception e) {
                System.err.println("Music playback error: " + e.getMessage());
            }
        }).start();
    }

    public void stopMusic() {
        musicRunning = false;
        synchronized (this) {
            if (musicLine != null && musicLine.isOpen()) {
                musicLine.stop();
                musicLine.flush();
            }
        }
    }
}