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
import mz.co.checkmob.api.authorization.domain.Authorization;
import mz.co.checkmob.api.authorization.domain.AuthorizationCommand;
import mz.co.checkmob.api.authorization.domain.AuthorizationType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractTest {
  private final Faker faker = new Faker();

  protected final Faker faker() {
      return faker;
  }
  protected Authorization anyAuthorization(){
      Authorization authorization = new Authorization();
      authorization.setId(ThreadLocalRandom.current().nextLong(10,100));
      authorization.setType(faker.options().option(AuthorizationType.values()));
      authorization.setAuthJson(Map.of(faker.internet().slug(), faker.internet().url()));
      authorization.setCreatedAt(LocalDateTime.now());
      authorization.setUpdatedAt(LocalDateTime.now());
      return authorization;
  }
  protected AuthorizationCommand anyAuthorizationCommand(){
      AuthorizationCommand command = new AuthorizationCommand();
      command.setType(faker.options().option(AuthorizationType.values()));
      command.setAuthJson(Map.of(faker.internet().slug(), faker.internet().url()));
      return command;
  }
  protected Endpoint anyEndpoint() {
    Endpoint command = new Endpoint();
    command.setId(ThreadLocalRandom.current().nextLong(10,100));
    command.setDataReader(faker.options().option(Map.of(faker.internet().slug(), faker.company().name())));
    command.setUrl(faker.internet().url());
    command.setUpdatedAt(LocalDateTime.now());
    command.setCreatedAt(LocalDateTime.now());
    return command;
  }

  protected CreateEndpointCommand anyCreateEndpointCommand() {
    CreateEndpointCommand command = new CreateEndpointCommand();
    command.setDataReader(faker.options().option(Map.of(faker.internet().slug(), faker.company().name())));
    command.setUrl(faker.internet().url());
    return command;
  }

  protected UpdateEndpointCommand anyUpdateEndpointCommand() {
    UpdateEndpointCommand command = new UpdateEndpointCommand();
    command.setId(ThreadLocalRandom.current().nextLong(10,100));
    command.setDataReader(faker.options().option(Map.of(faker.internet().slug(), faker.company().name())));
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