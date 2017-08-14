package ai.ekholabs.audio.service.componet.listener;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

public class MediaPlayerEventListener extends MediaPlayerEventAdapter {

  private static final Logger LOGGER = LoggerFactory.getLogger(MediaPlayerEventListener.class);

  private final CountDownLatch completionLatch;

  public MediaPlayerEventListener(final CountDownLatch completionLatch) {
    this.completionLatch = completionLatch;
  }

  @Override
  public void finished(final MediaPlayer mediaPlayer) {
    LOGGER.info("Rip completed successfully.");
    completionLatch.countDown();
  }

  @Override
  public void error(final MediaPlayer mediaPlayer) {
    LOGGER.error("Rip failed!");
    completionLatch.countDown();
  }
}
