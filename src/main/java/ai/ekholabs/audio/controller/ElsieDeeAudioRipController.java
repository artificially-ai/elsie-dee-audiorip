package ai.ekholabs.audio.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import ai.ekholabs.audio.service.AudioRipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
public class ElsieDeeAudioRipController {

  private final AudioRipService audioRipService;

  @Autowired
  public ElsieDeeAudioRipController(final AudioRipService audioRipService) {
    this.audioRipService = audioRipService;
  }

  @PostMapping(path = "/ripAudio", produces = APPLICATION_OCTET_STREAM_VALUE, consumes = MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<byte[]> ripAudio(final @RequestParam(value = "video") MultipartFile videoFile) throws IOException {
    final byte[] bytes = videoFile.getBytes();

    final Path path = Files.createTempFile("video-file-rip", ".mp4");
    final File tempVideoFile = path.toFile();
    tempVideoFile.deleteOnExit();
    Files.write(path, bytes);

    return ResponseEntity.ok(audioRipService.ripAudio(tempVideoFile.getAbsolutePath()));
  }
}
