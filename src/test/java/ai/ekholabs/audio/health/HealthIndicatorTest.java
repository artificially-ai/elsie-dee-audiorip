package ai.ekholabs.audio.health;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HealthIndicatorTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testStatus200() throws Exception {
    mvc.perform(get("/health"))
        .andExpect(status().isOk());
  }

  @Test
  public void testClientError() throws Exception {
    mvc.perform(get("/check"))
        .andExpect(status().is4xxClientError());
  }
}