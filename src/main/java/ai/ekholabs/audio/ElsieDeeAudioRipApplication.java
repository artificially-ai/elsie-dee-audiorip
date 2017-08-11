package ai.ekholabs.audio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ElsieDeeAudioRipApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElsieDeeAudioRipApplication.class, args);
  }
}
