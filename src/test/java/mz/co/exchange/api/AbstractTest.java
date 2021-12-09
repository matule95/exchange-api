package mz.co.exchange.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.net.URI;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractTest {
  private final Faker faker = new Faker();

  protected final Faker faker() {
      return faker;
  }

  @SneakyThrows
  protected <T> MockHttpServletRequestBuilder request(HttpMethod method, String uri, T content) {
    ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    String requestBody = objectMapper.writeValueAsString(content);
    return MockMvcRequestBuilders.request(method.name(), new URI(uri))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody);
  }
}