package ai.ekholabs.audio.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

import ai.ekholabs.audio.service.componet.listener.MediaPlayerEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.readAllBytes;

@Service
public class AudioRipService {

  private final static Logger LOGGER = LoggerFactory.getLogger(AudioRipService.class);

  private static final int LATCH_COUNT = 1;

  public byte[] ripAudio(final String filePath) throws IOException {
    final CountDownLatch countDownLatch = new CountDownLatch(LATCH_COUNT);
    final MediaPlayerEventListener mediaPlayerEventListener = new MediaPlayerEventListener(countDownLatch);

    final MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
    final MediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
    mediaPlayer.addMediaPlayerEventListener(mediaPlayerEventListener);

    final Path path = createTempFile("audio-file-rip-", ".wav");
    final File tempAudioFile = path.toFile();
    tempAudioFile.deleteOnExit();

    mediaPlayer.playMedia(filePath,
        "sout=#transcode{acodec=s16l,channels=2,ab=192,samplerate=44100,vcodec=dummy}:standard{dst=" + tempAudioFile
            .getAbsolutePath() + ",mux=wav,access=file}");

    try {
      // Wait here until finished or error
      countDownLatch.await();
    } catch(InterruptedException e) {
      LOGGER.error("Error occurred when converting file.");
      throw new IOException(e);
    }

    final byte[] audioBytes = readAllBytes(path.toAbsolutePath());
    LOGGER.info("File converted successfully. Audio file contains {} bytes.", audioBytes.length);
    return audioBytes;
  }
}
