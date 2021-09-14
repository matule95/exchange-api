package mz.co.checkmob.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import mz.co.checkmob.api.endpoint.domain.CreateEndpointCommand;
import mz.co.checkmob.api.endpoint.domain.Endpoint;
import mz.co.checkmob.api.endpoint.domain.UpdateEndpointCommand;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractTest {
  private final Faker faker = new Faker();

  protected final Faker faker() {
    return faker;
  }

  protected Endpoint anyEndpoint() {
    Endpoint command = new Endpoint();
    command.setId(ThreadLocalRandom.current().nextLong(10,100));
    command.setDataReader(faker.options().option(Map.of(faker.company().name(), faker.company().industry())));
    command.setUrl(faker.internet().url());
    command.setUpdatedAt(LocalDateTime.now());
    command.setCreatedAt(LocalDateTime.now());
    return command;
  }

  protected CreateEndpointCommand anyCreateEndpointCommand() {
    CreateEndpointCommand command = new CreateEndpointCommand();
    command.setDataReader(faker.options().option(Map.of(faker.company().name(), faker.company().industry())));
    command.setUrl(faker.internet().url());
    return command;
  }

  protected UpdateEndpointCommand anyUpdateEndpointCommand() {
    UpdateEndpointCommand command = new UpdateEndpointCommand();
    command.setId(ThreadLocalRandom.current().nextLong(10,100));
    command.setDataReader(faker.options().option(Map.of(faker.company().name(), faker.company().industry())));
    command.setUrl(faker.internet().url());
    return command;
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
